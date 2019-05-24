package application.model;

/**
 * Dinosaur Class
 * Representation of the Dinosaur object used in the Zone and Park class.
 * Holds information about
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 * 
 */

public class Dinosaur {
	
	/* Class Variable Declarations*/
	private String name;
	private String type;
	private boolean isVegetarian;
	
	/**Constructor
	 * Instantiates Dinosaur object with given input
	 *@param - name of Dinosaur, type of Dinosaur, Dinosaur diet
	 */
	public Dinosaur(String nameDino, String typeDino, boolean isVegetarianDino){
		this.name = nameDino;
		this.type = typeDino;
		this.isVegetarian = isVegetarianDino;
	}
	
	/** Class toString method override 
	 * Override for toString method
	 *
	 * @return returns information about the Dinosaur
	 */
	public String toString() {
		if(isVegetarian)
			return name + " - " + type + " " + "(Herbivore)" + "\n";
		else
			return name + " - " + type + " " + "(Carnivore)" + "\n";
	}
	
	/* Getters and Setters */
	/**
	 * getName method - returns name of dinosaur
	 * @return name of the Dinosaur object
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * setName method- sets name of the dinosaur
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getType - returns type of Dinosaur
	 * @return type of Dinosaur
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * setType - sets type of Dinosaur
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * getDiet method - returns diet of dinosaur
	 * @return isVegetarian boolean of Dinosaur
	 */
	public boolean getDiet() {
		return this.isVegetarian;
	}
	/**
	 * setDiet method - modifies diet of dinosaur
	 * @param isVegetarian
	 */
	public void setDiet(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}
}
