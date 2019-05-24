package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.Dinosaur;
import application.model.Park;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ZoneController.java
 * Responsible for communicating between Model and Zone.fxml
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class ZoneController implements EventHandler<ActionEvent>, Initializable{
	/*
	 * Class Variables and FXML Variables
	 */
	public static String buttonChoice;
	static Park thePark = new Park("thePark");
	int numDinos;  //Integer representation of the number of dinosaurs
	static boolean loadedFirstTime = false;
	
	@FXML
	ImageView background = new ImageView();
	@FXML
	Label zoneLabel = new Label();
	@FXML
	Label addSuccess = new Label();
	@FXML
	Label relocateSuccess = new Label();
	
	@FXML
	ListView<String> listOfDinos = new ListView<String>();
	ObservableList<String> dinoStringList = FXCollections.observableArrayList();	//List to add elements to ListView
	
	@FXML
	Label numberOfDinos = new Label();
	@FXML
	Label riskLabel = new Label();
	
	@FXML
	TextField newDinoNameField = new TextField();
	@FXML
	TextField newDinoTypeField = new TextField();
	
	@FXML
	TextField newZoneCodeField = new TextField();
	@FXML
	TextField relocateNameField = new TextField();
	
	ToggleGroup group = new ToggleGroup();
	@FXML
    RadioButton yesButton = new RadioButton("Yes");
	@FXML
    RadioButton noButton = new RadioButton("No");
	
	@Override
	public void handle(ActionEvent event) {		//Back Button
		//Load the Main View 
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		//Called when Zone.fxml is loaded
	
		background.setOpacity(0.35);  //Set Opacity of background image
		
		yesButton.setToggleGroup(group);	//Assign Radio Buttons to Group
	    yesButton.setSelected(true);
	    noButton.setToggleGroup(group);
	    
	    if(!loadedFirstTime) {		//Check if it is the first time the view has been loaded
	    	try {		//Load Data into the Park Object's HashMap
	    		thePark.loadZones("data/zones.csv");
	    	}catch( IOException ex ) {
	    		ex.printStackTrace();
	    	}
	    	loadedFirstTime = true;
	    }
	    
		populateScreen(buttonChoice, thePark);		//Calls Method to Populate the Screen with data
		
		switch(buttonChoice) {		//Set Zone Label and Image based on button choice
			case "B":
				zoneLabel.setText("Brachiosaurus Zone");
				break;
			case "D":
				zoneLabel.setText("Dilophosaurus Zone");
				break;
			case "G":
				zoneLabel.setText("Gallimimus Zone");
				break;
			case "R":
				zoneLabel.setText("Raptor Zone");
				break;
			case "TY":
				zoneLabel.setText("T-Rex Zone");
				break;
			case "TR":
				zoneLabel.setText("Triceratops Zone");
				break;
			case "X":
				zoneLabel.setText("Reserve Zone");
				break;
			default:
				zoneLabel.setText("NULL");
		}
		
	}
	/**
	 * addButton method - method to handle the addDino button
	 * @param event
	 */
	public void addButton( ActionEvent event ){
		Dinosaur newDino;
		String name, type;
		boolean isVegetarian = false;
		
		name = newDinoNameField.getText();
		type = newDinoTypeField.getText();
		
		if(yesButton.isSelected()) {
			isVegetarian = false;
		}else {
			isVegetarian = true;
		}
		
		newDino = new Dinosaur(name, type, isVegetarian);		//Instantiate and add new Dino
		thePark.addDino(thePark,buttonChoice, newDino);
		
		
		int newNumberOfDinos = Integer.valueOf(numberOfDinos.getText()) + 1;
		numberOfDinos.setText(String.valueOf(newNumberOfDinos));
		listOfDinos.getItems().add(newDino.toString());
		addSuccess.setVisible(true);
		
		Park.save(thePark);
	}
	/**
	 * relocateButton - button to handle relocating the Dinos
	 * @param event
	 */
	public void relocateButton( ActionEvent event ) {
		String newZoneCode = newZoneCodeField.getText();	//Get Input
		newZoneCode = newZoneCode.trim();
		String dinoNameToMove = relocateNameField.getText();
		dinoNameToMove = dinoNameToMove.trim();
				
		int newNumberOfDinos = Integer.valueOf(numberOfDinos.getText()) - 1;	//Change UI Values
		numberOfDinos.setText(String.valueOf(newNumberOfDinos));
		String dinoToRemove = thePark.getFullDinoString(dinoNameToMove, thePark.getZones().get(thePark.getZoneByCode(buttonChoice)));
		if(dinoToRemove != null) {
			listOfDinos.getItems().remove(dinoToRemove);
			relocateSuccess.setVisible(true);
			
			thePark.relocate(thePark, buttonChoice, newZoneCode, dinoNameToMove);	//Relocate on backend
		}else {
			relocateSuccess.setText("That dino isn't here!");
			relocateSuccess.setVisible(true);
		}
		
		Park.save(thePark);	//Save Changes to File
	}
	
	/**
	 * populateScreen Method - populates screen with data
	 * @param buttonChoice, which zone to view
	 * @param thePark - Park object filled with juicy data
	 */
	public void populateScreen(String buttonChoice, Park thePark) {
		ArrayList<Dinosaur> tempDinoArrayList = thePark.getZones().get(thePark.getZoneByCode(buttonChoice));
		for(Dinosaur dino: tempDinoArrayList) {		//Add each element to string list
			dinoStringList.add(dino.toString());
		}
		File bFile = new File("data/dino_images/" + buttonChoice + ".jpg");	//File to get Image for background
		Image bImage = new Image(bFile.toURI().toString());		//Image from File
		background.setImage(bImage);	//Set Background Image
		numberOfDinos.setText(Integer.toString(tempDinoArrayList.size()));		//Set the number of dinos in current zone
		riskLabel.setText(thePark.getZoneByCode(buttonChoice).getRiskLevel());	//Set the risk Level for the label
		for(String dino : dinoStringList) {
			listOfDinos.getItems().add(dino);
		}
	}
	/**
	 * viewDinos - switch views to Dinosaur.fxml
	 * @param event
	 */
	public void viewDinos( ActionEvent event ) {
		DinosaurController.dinoList = thePark.getDinoListAtZone(thePark.getZoneByCode(buttonChoice));	//set the dino controller's dino list
		//Load the Main View 
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Dinosaur.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
