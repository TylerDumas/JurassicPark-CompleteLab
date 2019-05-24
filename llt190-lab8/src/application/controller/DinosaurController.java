package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.Dinosaur;
import application.model.DinosaurTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * DinosaurController.java
 * Responsible for communicating between Model and Dinosaur.fxml
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class DinosaurController implements EventHandler<ActionEvent>, Initializable{

	/*Class Variables */
	@FXML
	ImageView background = new ImageView();
	@FXML
	Label zoneLabel = new Label();
	@FXML
	Label dinoName = new Label();
	@FXML
	Label dinoType = new Label();
	@FXML
	ImageView dinoImage = new ImageView();
	
	private DinosaurTask task;
	
	public static ArrayList<Dinosaur> dinoList;
	
	
	@Override
	public void handle(ActionEvent event) {	//HOME button
		task.cancel();
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
	public void initialize(URL location, ResourceBundle resources) {
		task = new DinosaurTask(dinoList, dinoName, dinoType, dinoImage);
		
		File bFile = new File("data/dino_images/" + ZoneController.buttonChoice + ".jpg");	//File to get Image for background
		Image bImage = new Image(bFile.toURI().toString());		//Image from File
		background.setImage(bImage);	//Set Background Image
		background.setOpacity(0.35);  //Set Opacity of background image
		
		switch(ZoneController.buttonChoice) {		//Set Zone Label and Image based on button choice
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
		new Thread(task).start();
		
	}

}
