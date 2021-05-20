package Maze3DGame;

import Maze3DGame.model.SceneName;
import Maze3DGame.view.BadEnding;
import Maze3DGame.view.Ending;
import Maze3DGame.view.Opening;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	/** Holds the various scenes to switch between */
	private static Map<SceneName, Scene> scenes = new HashMap<>();

	@Override
	public void start(Stage stage) {

		// Create and store all scenes up front

    scenes.put(SceneName.OPENING, new Opening(stage).getScene());
    scenes.put(SceneName.ENDING, new Ending(stage).getScene());
    scenes.put(SceneName.BAD_ENDING, new BadEnding(stage).getScene());

		stage.setScene(scenes.get(SceneName.OPENING));
		stage.setTitle("Maze3D");
		stage.show();
	}


	public static Map<SceneName, Scene> getScenes() {
		return scenes;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
