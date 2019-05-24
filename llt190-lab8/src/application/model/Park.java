package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Park Class
 * Template for the Park object
 * Holds information about the name of the park, the name of the zones, and dinosaurs of each zone
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class Park {
	
	/* Class Variables */
	private String name;
	private HashMap<Zone, ArrayList<Dinosaur>> zones;		//Key is Zone, Value is ArrayList of Dinos
	
	/** Constructor Method 
	 * @param - name of the Park, size of the Park's Zone array
	 * Output- None, Instantiates a Park Object
	 */
	public Park(String name){
		this.name = name;
		this.zones = new HashMap<Zone, ArrayList<Dinosaur>>();
	}
	
	/** Class toString method override 
	 * Override for toString method
	 * Input - none
	 * @return returns information about the name of the park, the zones in the park as well as the dinosaurs within the zones
	 */
	public String toString() {
		String zoneInfo = "";
		for(Zone key : this.zones.keySet()) {
			zoneInfo = zoneInfo + key.getName() + "\n" + this.zones.get(key).toString() + "\n";
		}
		return "Welcome to " + name + "\n" + "--------------------" + "\n" + zoneInfo;
	}
	/**
	 * loadZones method
	 * loads zone information from csv file into new elements of HashMap with null values
	 * @param string fileName
	 * @throws IOException
	 */
	public void loadZones(String fileName) throws IOException {
		ArrayList<Dinosaur> tempDinoList;
		try {
			File zoneFile = new File(fileName);
			Scanner zoneScanner = new Scanner(zoneFile);
			while(zoneScanner.hasNext()) {
				String zoneInfo = zoneScanner.next();
				String[] zoneStringArray = zoneInfo.split(",");
				Zone zone = new Zone(zoneStringArray[0], zoneStringArray[2], zoneStringArray[1] + " risk");
				tempDinoList = loadDinosaurs("data/dinos.csv", zoneStringArray[2]);
				this.zones.put(zone, tempDinoList);
			}
			zoneScanner.close();
		}catch(FileNotFoundException ex) {
			System.out.print(ex);
			ex.printStackTrace();
		}
	}
	/**
	 * loadDinosaurs method
	 * loads Dinosaur information from csv file into Dinosaur objects within Dinosaurs ArrayList
	 * @param fileName, name of file with Dinosaur information
	 * @param zoneCode, zoneCode to create ArrayList of Dinos off of
	 * @return ArrayList of Dinos matching that code
	 * @throws IOException
	 */
	public ArrayList<Dinosaur> loadDinosaurs(String fileName, String zoneCode) throws IOException{
		ArrayList<Dinosaur> tempDinoList = new ArrayList<Dinosaur>();
		try {
			File dinoFile = new File(fileName);
			Scanner dinoScanner = new Scanner(dinoFile);
			Dinosaur dino = null;
			while(dinoScanner.hasNext()) {
				String dinoInfo = dinoScanner.nextLine();
				dinoInfo.replaceAll("\\s", "");
				String[] dinoStringArray = dinoInfo.split(",");	//index: 0 is name, 1 is type, 2 is diet, 3 is zone code (not in constructor)
				if(dinoStringArray[3].equals(zoneCode)) {	//if current Dino in file belongs in zone, has matching zoneCode
					dino = new Dinosaur(dinoStringArray[0], dinoStringArray[1], dietStringToBoolean(dinoStringArray[2]));
					tempDinoList.add(dino);
				}
			}
			dinoScanner.close();
		}catch(FileNotFoundException ex) {
			System.out.print(ex);
			ex.printStackTrace();
		}
		return tempDinoList;
	}
	/**
	 * addDino method - adds dino to current zone
	 * @param parkMap - current park object's hashmap
	 * @param zoneCode - current zone code
	 * @param newDino - Dinosaur to add
	 */
	public void addDino(Park park, String zoneCode, Dinosaur newDino){
		ArrayList<Dinosaur> newList = park.getZones().get(getZoneByCode(zoneCode));
		newList.add(newDino);
		park.setDinoListAtZone(getZoneByCode(zoneCode), newList);
	}
	/**
	 * relocate method
	 * moves Dinosaur object from one zone to another
	 */
	public void relocate(Park park, String zoneCode, String newZoneCode, String dinoToMove) {
		//Remove Dino from existing zone
		Dinosaur removedDino = null;
		for(Zone key : park.zones.keySet()) {		//find dino with that name in the hashmap
			ArrayList<Dinosaur> thisKeyList = (ArrayList<Dinosaur>) park.getDinoListAtZone(key).clone();
			for(Dinosaur dino : thisKeyList) {
				if(dino.getName().equals(dinoToMove)) {
					removedDino = dino;
					park.zones.get(key).remove(dino);
				}
			}
		}
		//Add Dino to new Zone	
		addDino(park, newZoneCode, removedDino);
	}
	
	/**
	 * save method
	 * writes new dino information into the csv file 
	 */
	public static void save(Park thePark) {
		String stringToWrite;
		try {
			File dinoFile = new File("data/dinos.csv");
			FileWriter printer = new FileWriter(dinoFile);
			//Iterate through each Zone, writing all dinosaurs with their respective zoneCode data
			for(Zone key : thePark.zones.keySet()) {
				for(Dinosaur dino : thePark.zones.get(key)) {
					stringToWrite = dino.getName()+","+dino.getType()+","+String.valueOf(dino.getDiet())+","+key.getZoneCode()+"\n";
					printer.write(stringToWrite);
				}
			}
			printer.close();
		}catch(FileNotFoundException ex) {
			System.out.print(ex);
			ex.printStackTrace();
		}catch( IOException ex ) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * dietStringToBoolean method.  Takes string of "true" or "false", returns boolean equivalent
	 * @param diet
	 * @return diet converted to boolean
	 */
	public boolean dietStringToBoolean(String diet) {
		if(diet.equals("true")) {
			return true;
		}else {
			return false;
		}
	}
	
	/* Getters and Setters*/
	
	/**
	 * getName method - get's the park's name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setName method - sets the Park's name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getZoneByCode method, returns Zone based on it's name
	 * @param zoneCode, string name of zone
	 * @return zone by that name
	 */
	public Zone getZoneByCode(String zoneCode) {
		for( Zone key : this.zones.keySet()) {
			if(key.getZoneCode().equals(zoneCode)) {
				return key;
			}
		}
		return null;
	}
	/**
	 * getDinoListAtZone method - accesses and returns Dino ArrayList at given key
	 * @param key
	 * @return List of Dinosaurs in that zone
	 */
	public ArrayList<Dinosaur> getDinoListAtZone(Zone key) {
		if(this.zones.containsKey(key)) {
			return this.zones.get(key);
		}else{
			return null;
		}
	}
	public int getNumberOfDinosaursInZone(Zone zone, HashMap<Zone, ArrayList<Dinosaur>> zones) {
		return zones.get(zone).size();
	}
	/**
	 * setDinoListAtZone - replaces Dinos at "key" with dinoList
	 * @param key - zone to set dinos
	 * @param dinoList - ArrayList of dinos to put at that key
	 */
	public void setDinoListAtZone(Zone key, ArrayList<Dinosaur> dinoList) {
		this.zones.put(key, dinoList);
	}
	/**
	 * getZones - getter for Zones HashMap
	 * @return HashMap with Zone, DinoArrayList : key, value pair
	 */
	public HashMap<Zone, ArrayList<Dinosaur>> getZones() {
		return zones;
	}
	/**
	 * setZones - setter for Zones HashMap
	 * @param HashMap with Zone, DinoArrayList : key, value pair
	 */
	public void setZones(HashMap<Zone, ArrayList<Dinosaur>> zones) {
		this.zones = zones;
	}
	/**
	 * getFullDinoString method - returns entire String representation of a dinosaur
	 * @param name - name of dino
	 * @param dinoList - list that dino is in
	 * @return full string of dino info
	 */
	public String getFullDinoString(String name, ArrayList<Dinosaur> dinoList) {
		String fullString = null;
		for(Dinosaur dino : dinoList) {
			if(dino.getName().equals(name)) {
				//System.out.print("success");
				fullString = dino.toString();
			}else {
				//System.out.print("succ");
				continue;
			}
		}
		return fullString;
	}
	public void addZone(Park park, Zone zone) {
		ArrayList<Dinosaur> newList = new ArrayList<Dinosaur>();
		park.zones.put(zone, newList);
	}
}