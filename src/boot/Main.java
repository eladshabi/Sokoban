package boot;

import java.util.List;

import controller.MyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;
import view.gui.Gui;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxl = new FXMLLoader(getClass().getResource("/boot/MainWindow.fxml"));
			BorderPane root = (BorderPane) fxl.load();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			Parameters pram = getParameters();
			List<String> s = pram.getRaw();
			if (s.isEmpty()) {
				s = null;
			}
			Gui mwc = fxl.getController(); // view
			MyModel m = new MyModel(); // model
			MyController c = new MyController(m, mwc, s);
			m.addObserver(c);
			mwc.addObserver(c);

			mwc.start();
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
