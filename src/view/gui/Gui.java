package view.gui;

import java.io.File;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.sData;
import model.data.Level;
import model.data.SokoData;
import model.data.User;
import view.Viewer;

public class Gui extends Observable implements Viewer {

	@FXML
	MazeDisplayer mazeDisplayer;
	@FXML
	Label stepsid;
	@FXML
	Label tiLabel;

	private Timer timefx;
	private long time;
	private long exeTime;
	private int step;
	private Media startMp3 = new Media(new File("./res./music./Smurf_Song.mp3").toURI().toString());
	private Media winMp3 = new Media(new File("./res./music./win_song.mp3").toURI().toString());
	private MediaPlayer player = new MediaPlayer(startMp3);
	private MediaPlayer finished = new MediaPlayer(winMp3);
	private boolean onplay;
	private SimpleLongProperty exTime;
	private Optional<String> result;
	private StringProperty sTime;
	private TableView<sData> table;
	private ObservableList<sData> data;
	private boolean ShowSolMode=false;
	public boolean isShowSolMode() {
		return ShowSolMode;
	}


	public void setShowSolMode(boolean showSolMode) {
		ShowSolMode = showSolMode;
	}

	final HBox hb = new HBox();

	public void solvelevel() {
		this.setShowSolMode(true);
		LinkedList<String> params = new LinkedList<String>();
		params.add("solve");
		params.add(mazeDisplayer.getLevel().getDifficulty());
		setChanged();
		notifyObservers(params);
		
		player.pause();
		finished.stop();
	}
	

	public void getScores() {
		LinkedList<String> params = new LinkedList<String>();
		params.add("getData");
		setChanged();
		notifyObservers(params);
	}

	public void setTable() {

		TableColumn userCol = new TableColumn("User");
		userCol.setCellValueFactory(new PropertyValueFactory<sData, String>("user_name"));
		/*
		 * TableColumn levelCol = new TableColumn("Level");
		 * levelCol.setCellValueFactory(new PropertyValueFactory<sData,
		 * String>("level_name"));
		 */
		TableColumn stepsCol = new TableColumn("Steps");
		stepsCol.setCellValueFactory(new PropertyValueFactory<sData, Integer>("steps"));
		TableColumn timeCol = new TableColumn("Time");
		timeCol.setCellValueFactory(new PropertyValueFactory<sData, Integer>("timeEx"));
		TableColumn scoreCol = new TableColumn("Score");
		scoreCol.setCellValueFactory(new PropertyValueFactory<sData, Integer>("score"));
		// levelCol,
		table.getColumns().setAll(userCol, stepsCol, timeCol, scoreCol);

		final TextField searcher = new TextField();
		searcher.setPromptText("Search");
		// addFirstName.setMaxWidth(firstNameCol.getPrefWidth());

		final Button seButton = new Button("Search");
		seButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (sData sData : data) {
					if (searcher.getText().startsWith(sData.getUser_name())) {
						LinkedList<sData> newData = new LinkedList<sData>();
						for (sData sData1 : data) {
							if (!searcher.getText().startsWith(sData1.getLevel_name()))
								newData.add(sData1);
							data.clear();
							data.addAll(newData);
							break;
						}
					}
				}
				searcher.clear();
			}
		});
		hb.getChildren().addAll(searcher, seButton);
		hb.setSpacing(2);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table, hb);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Stage myDialog = new Stage();
				myDialog.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(new Group());
				// Scene myDialogScene = new Scene(
				// VBoxBuilder.create().children(table).alignment(Pos.CENTER).padding(new
				// Insets(10)).build());
				myDialog.setScene(scene);
				((Group) scene.getRoot()).getChildren().addAll(vbox);
				myDialog.show();
			}
		});

	}

	@Override
	public void setData(LinkedList<SokoData> dataList) {
		LinkedList<sData> sList = new LinkedList<sData>();
		for (SokoData sokoData : dataList) {
			sData sdata = new sData(sokoData.getUser_name(), sokoData.getLevel_name(), sokoData.getSteps(),
					sokoData.getTimeEx(), sokoData.getScore());
			sList.add(sdata);
		}
		data = FXCollections.observableList(sList);
		table.setItems(data);
	}

	public Gui() {
		sTime = new SimpleStringProperty();
		sTime.set("0");
		mazeDisplayer = new MazeDisplayer();
		step = 0;
		onplay = false;
		timefx = new Timer();

		exTime = new SimpleLongProperty(0);
		table = new TableView<sData>();
		// tiLabel= new Label();
	}

	public void bindTimer(StringProperty timer) {

		tiLabel.textProperty().bind(timer);

	}

	private void startTimer() {
		sTime.set("Hi");
		/*
		 * Platform.runLater(new Runnable(){
		 * 
		 * @Override public void run() { timefx.scheduleAtFixedRate(new
		 * TimerTask() {
		 * 
		 * @Override public void run() {
		 * 
		 * 
		 * int sec= Integer.parseInt(sTime.get()); sec++; sTime.set(""+ sec);
		 * 
		 * // exTime.add(cTime - time);
		 * System.out.println(String.valueOf(sTime.get()));
		 * 
		 * 
		 * 
		 * }}, 0, 1000);
		 * 
		 * }});
		 */

	}

	public void start() {

		if (mazeDisplayer.getLevel() == null) {
			return;
		}
		startTimer();

		this.step = 0;
		onplay = true;
		String command = "Display";
		LinkedList<String> params = new LinkedList<String>();
		params.add(command);
		params.add("level");
		this.setChanged();
		this.notifyObservers(params);

		mazeDisplayer.requestFocus();

		mazeDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override

			public void handle(KeyEvent event) {

				String direction = "";

				if (event.getCode() == KeyCode.UP) {
					direction = "up";

				} else if (event.getCode() == KeyCode.DOWN) {
					direction = "down";

				} else if (event.getCode() == KeyCode.LEFT) {

					direction = "left";

				} else if (event.getCode() == KeyCode.RIGHT) {
					direction = "right";

				}

				String command = "move";
				LinkedList<String> params = new LinkedList<String>();
				params.add(command);
				params.add(direction);

				setChanged();
				notifyObservers(params);
				step++;
				stepsid.setText((new Integer(step)).toString());

			}
		});

	}

	public void open() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("open maze");
		chooser.setInitialDirectory(new File("./res./levels"));
		File ch = chooser.showOpenDialog(null);
		if (ch != null) {
			LinkedList<String> params = new LinkedList<String>();
			params.add("load");
			params.add(ch.getAbsolutePath());
			setChanged();
			notifyObservers(params);
		}
		finished.stop();
		player.play();

	}

	public void save() {

		if (mazeDisplayer.getLevel() != null) {
			FileChooser cs = new FileChooser();
			cs.setTitle("save maze");
			cs.setInitialDirectory(new File("./res./save"));
			File c = cs.showSaveDialog(null);
			if (c != null) {
				LinkedList<String> params = new LinkedList<String>();
				params.add("save");
				params.add(c.getAbsolutePath());
				setChanged();
				notifyObservers(params);
			}

		}

	}

	@Override
	public void print(Level level) {
			this.mazeDisplayer.setLevel(level);
		
	}

	public void musicstop() {
		player.pause();
		finished.stop();
	}

	@Override
	public void stop() {
		player.stop();
		finished.stop();
		Platform.exit();

	}

	public void close() {
		LinkedList<String> params = new LinkedList<String>();
		params.add("Exit");
		setChanged();
		notifyObservers(params);

	}

	@Override
	public void printMessage(String message) {
		setChanged();
		mazeDisplayer.printMessage(message);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Congratulations!!!");
				dialog.setHeaderText("You're the winner.");
				dialog.setContentText("Please enter your name:");

				// Traditional way to get the response value.
				result = dialog.showAndWait();
				/*
				 * if (result.isPresent()) { System.out.println("Your name: " +
				 * result.get()); }
				 */
				setChanged();
				LinkedList<Object> params = new LinkedList<Object>();
				params.add("windata");
				notifyObservers(params);
			}
		});
		player.stop();
		finished.play();
	}

	@Override
	public String toString() {

		return "gui";
	}

	@Override
	public boolean onPlay() {

		return onplay;
	}

	public LinkedList<Object> getData() {
		//System.out.println("1");

		LinkedList<Object> params = new LinkedList<Object>();
		params.add(result.get());
		params.add(mazeDisplayer.getLevel().getName());
		params.add(step);
		params.add((int) exeTime);
		params.add(1000 - step - (int) exeTime);
		//System.out.println(params.toString());
		return params;
	}

}
