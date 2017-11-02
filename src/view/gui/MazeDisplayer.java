package view.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.data.Level;

public class MazeDisplayer extends Canvas {

	private Level level;
	private StringProperty wallfilename;
	private StringProperty boxfilename;
	private StringProperty targatefilename;
	private StringProperty boxontargatefilename;
	private StringProperty playerfilename;
	private StringProperty playerontargatefilename;
	private StringProperty floorfilename;


	public MazeDisplayer() {

		wallfilename = new SimpleStringProperty();
		boxfilename = new SimpleStringProperty();
		targatefilename=new SimpleStringProperty();;
		boxontargatefilename=new SimpleStringProperty();;
		playerfilename=new SimpleStringProperty();
		playerontargatefilename=new SimpleStringProperty();
		floorfilename=new SimpleStringProperty();
	}

	public void redrow() {

		int i, j;
		if (level == null)
			return;

		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		if (level != null) {
			double W = getWidth();
			double H = getHeight();
			double w = W / level.getConstant_element().get(0).size();
			double h = H / level.getConstant_element().size();
			try {
				Image wall = new Image(new FileInputStream("./res./element./wall.jpg"));
				Image box = new Image(new FileInputStream("./res./element./box.jpg"));
				Image floor = new Image(new FileInputStream("./res./element./floor.jpg"));
				Image player = new Image(new FileInputStream("./res./element./player.jpg"));
				Image boxontarget = new Image(new FileInputStream("./res./element./boxontargate.jpg"));
				Image targate = new Image(new FileInputStream("./res./element./targate.jpg"));
				Image playerontargate = new Image(new FileInputStream("./res./element./player.jpg"));

				for (i = 0; i < level.getConstant_element().size(); i++) {
					for (j = 0; j < level.getConstant_element().get(0).size(); j++) {

						switch (level.getConstant_element().get(i).get(j).getType()) {

						case "Floor": {
							if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
								gc.drawImage(box, j * w, i * h, w, h);
								break;
							} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
								gc.drawImage(floor, j * w, i * h, w, h);
								break;
							} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
								gc.drawImage(player, j * w, i * h, w, h);
								break;
							}
						}
						case "Wall":
							gc.drawImage(wall, j * w, i * h, w, h);
							break;

						case "Target": {
							if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
								gc.drawImage(boxontarget, j * w, i * h, w, h);// Box
								// on
								// target
								break;
							} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
								gc.drawImage(playerontargate, j * w, i * h, w, h);
								;// player on target.
								break;
							} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
								gc.drawImage(targate, j * w, i * h, w, h);
								break;
							}
						}
						case "Start Point": {
							if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
								gc.drawImage(box, j * w, i * h, w, h);
								break;
							} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
								gc.drawImage(floor, j * w, i * h, w, h);
								break;
							} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
								gc.drawImage(player, j * w, i * h, w, h);
								break;
							}

						}
						default:
							break;
						}

					}

				}
			} catch (FileNotFoundException e) {

				System.out.println("eror on maze elemnt image");
			}

		}

		this.setFocused(true);
	}

	public void setLevel(Level level) {
		this.level = level;
		this.setFocusTraversable(true);
		this.setFocused(true);
		redrow();
	}

	public Level getLevel() {
		return level;
	}

	public void printMessage(String message) {
		switch (message) {
		case "win":
			Image win;
			try {
				win = new Image(new FileInputStream("./res./screen./win.jpg"));
				this.getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
				this.getGraphicsContext2D().drawImage(win, 0, 0);
			} catch (FileNotFoundException e) {

				System.out.println("eror on load win image");
			}
			break;

		default:
			break;
		}
	}

	public String getWallfilename() {
		return wallfilename.get();
	}

	public void setWallfilename(String wallfilename) {
		this.wallfilename.set(wallfilename);
		;
	}

	public String getBoxfilename() {
		return boxfilename.get();
	}

	public void setBoxfilename(String boxfilename) {
		this.boxfilename.set(boxfilename);
	}

	public String getTargatefilename() {
		return targatefilename.get();
	}

	public void setTargatefilename(String tragatefilename) {
		this.targatefilename.set(tragatefilename);
	}

	public String getBoxontargatefilename() {
		return boxontargatefilename.get();
	}

	public void setBoxontargatefilename(String boxontargatefilename) {
		this.boxontargatefilename.set(boxontargatefilename);
	}

	public String getPlayerfilename() {
		return playerfilename.get();
	}

	public void setPlayerfilename(String playerfilename) {
		this.boxfilename.set(playerfilename);
	}

	public String getPlayerontargatefilename() {
		return playerontargatefilename.get();
	}

	public void setPlayerontargatefilename(String playerontargatefilename) {
		this.playerontargatefilename.set(playerontargatefilename);
	}

	public String getFloorfilename() {
		return floorfilename.get();
	}

	public void setFloorfilename(String floorfilename) {
		this.floorfilename.set(floorfilename);
	}
}
