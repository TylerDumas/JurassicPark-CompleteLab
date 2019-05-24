package application.model;

/**
 * Zone Class
 * Template for the Zone object
 * Holds information about the name and dinosaurs of each zone
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */


public class Zone {
	
	/* Class Variable Declarations*/
	private String name;
	private String zoneCode;
	private String riskLevel;
	
	/* Constructor Method 
	 * Input - name of the Zone, the Zone's zone code, and the Zone's risk level
	 * Output- None, Instantiates a Zone Object
	 */
	public Zone(String nameZone, String zoneCode, String riskLevel){
		this.name = nameZone;
		this.zoneCode = zoneCode;
		this.riskLevel = riskLevel;
	}
	
	/** Class toString method override 
	 *  Override for Object toString method
	 * @return returns information about the name of the zone
	 */
	public String toString() {
		return zoneCode + ":" + name + " Zone "+ "(" + riskLevel + ")";
	}
	
	/*
	 *  Getters and Setters 
	 */
	
	/**
	 * getName method - returns name of the Zone
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * setName method - sets the name of the Zone
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getZoneCode method - gets the zone code of the Zone
	 * @return zoneCode
	 */
	public String getZoneCode() {
		return this.zoneCode;
	}
	/**
	 * setZoneCode - sets the zoneCode of this object to the one provided in argument
	 * @param zoneCode
	 */
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	/**
	 * getRiskLevel method - gets the risk level of this Zone object
	 * @return riskLevel
	 */
	public String getRiskLevel() {
		return this.riskLevel;
	}
	/**
	 * setRiskLevel method - sets the risk level to the one provided
	 * @param riskLevel
	 */
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
}