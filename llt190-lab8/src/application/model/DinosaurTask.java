package application.model;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * DinosaurTask.java
 * Responsible for populating slideshow 
 * 
 * @author Jackson Dumas (llt190)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class DinosaurTask extends Task<Void>{
	
	/*Class Variables */
	
	public ArrayList<Dinosaur> dinos;
	
	String dinoName = null;
	String dinoType = null;
	Image dinoImage = null;
	
	public ImageView dinoView = new ImageView();
	public Label nameLabel = new Label(), typeLabel = new Label();
	File imageFile;
	
	/**
	 * Constructor for DinosaurTask
	 * @param dinoList - list of dinosaurs to display
	 * @param nameLabel - label to populate with name
	 * @param typeLabel - label to populate with tpye
	 * @param view - image to display
	 */
	public DinosaurTask(ArrayList<Dinosaur> dinoList, Label nameLabel, Label typeLabel, ImageView view) {
		this.dinos = dinoList;
		this.nameLabel = nameLabel;
		this.typeLabel = typeLabel;
		this.dinoView = view;
	}
	
	@Override
	protected Void call() throws Exception {
		Dinosaur temp;
		int i = 0;
		//Update the labels
		while(!isCancelled()) {
			temp = dinos.get(i);
			imageFile = new File("data/dino_images/images/" +  temp.getName().toLowerCase() + ".jpg");
			dinoImage = new Image(imageFile.toURI().toString());
			setDinoText(dinos.get(i).getName(), dinos.get(i).getType());
			Platform.runLater(new Runnable() {
				 @Override
				 public void run() {
					nameLabel.setText(dinoName);
					typeLabel.setText(dinoType);
					setDinoImage(dinoImage);
				 }
			});
			Thread.sleep(10000);
			i++;
			if(i == dinos.size()) {
				i = 0;
			}
		}
		return null;
	}
	/**
	 * setDinoImage - sets the current image of the slideshow
	 * @param image
	 */
	public void setDinoImage(Image image) {
		if(image.getHeight() == 0) {
			imageFile = new File("data/dino_images/none.jpg");
			image = new Image(imageFile.toURI().toString());
			dinoView.setImage(image);
			dinoView.setCache(true);
			return;
		}
		dinoView.setImage(image);
		dinoView.setCache(true);
	}
	
	/**
	 * setDinoText - sets the labels at the bottom of the slide show
	 * @param newName - name to set
	 * @param newType - new type
	 */
	public void setDinoText(String newName, String newType) {
		dinoName = newName;
		dinoType = newType;
	}
}
