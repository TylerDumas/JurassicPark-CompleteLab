package application.controller;

import java.io.File;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * MainController.java
 * Responsible for communicating between User and Main.fxml
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class MainController implements EventHandler<ActionEvent>{
	/*
	 * Class Variables and FXML Variables
	 */
	
	String zoneCode;	//Value is determined by which button is pressed by user
	@FXML
	ImageView background = new ImageView();	//to set background
	@FXML
	ImageView foreground = new ImageView();
	
	/**
	 * bButtonHandle - zone specific button
	 * @param event
	 */
	public void bButtonHandle( ActionEvent event ) {		//B Button
		zoneCode = "B";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource("../view/Zone.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * dButtonHandle - zone specific button
	 * @param event
	 */
	public void dButtonHandle( ActionEvent event ) {		//D Button
		zoneCode = "D";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource( "../view/Zone.fxml" ));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * gButtonHandle - zone specific button
	 * @param event
	 */
	public void gButtonHandle( ActionEvent event ) {	//G Button
		zoneCode = "G";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource( "../view/Zone.fxml" ));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * rButtonHandle - zone specific button
	 * @param event
	 */
	public void rButtonHandle( ActionEvent event ) {		//R Button
		zoneCode = "R";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource( "../view/Zone.fxml" ));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * trButtonHandle - zone specific button
	 * @param event
	 */
	public void trButtonHandle( ActionEvent event ) {		//TR Button
		zoneCode = "TR";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource( "../view/Zone.fxml" ));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * tyButtonHandle - zone specific button
	 * @param event
	 */
	public void tyButtonHandle( ActionEvent event ) {		//TY Button
		zoneCode = "TY";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource( "../view/Zone.fxml" ));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * xButtonHandle - zone specific button
	 * @param event
	 */
	public void xButtonHandle( ActionEvent event ) {		//X Button
		zoneCode = "X";
		ZoneController.buttonChoice = zoneCode;
		try {	// Redirect user to next view - Zone.fxml
			Parent root = FXMLLoader.load(getClass().getResource( "../view/Zone.fxml" ));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void initialize() {
		File backgroundFile = new File("data/dino_images/main.jpg");
		Image backgroundImage = new Image(backgroundFile.toURI().toString());
		background.setImage(backgroundImage);
		background.setCache(true);
		File foregroundFile = new File("data/dino_images/logo.png");
		Image foregroundImage = new Image(foregroundFile.toURI().toString());
		foreground.setImage(foregroundImage);
		foreground.setCache(true);
	}
	@Override
	public void handle( ActionEvent event ) {		//Quit Button
		System.exit(0);
	}
	

}
