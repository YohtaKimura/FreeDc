package multiScene;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import multiScene.model.SceneName;
import multiScene.view.MainView;
import multiScene.view.ViewOne;

public class Main extends Application {

	/** Holds the various scenes to switch between */
	private static Map<SceneName, Scene> scenes = new HashMap<>();

	@Override
	public void start(Stage stage) {

		// Create and store all scenes up front

    scenes.put(SceneName.MAIN, new MainView(stage).getScene());
    scenes.put(SceneName.SCENE1, new ViewOne(stage).getScene());
/*		scenes.put(SceneName.SCENE2, new ViewTwo(stage).getScene());
		scenes.put(SceneName.SCENE3, new ViewThree(stage).getScene());
*/

		// Start with the main scene
		stage.setScene(scenes.get(SceneName.MAIN));
		stage.setTitle("Multi-Scene Demo");
		stage.show();
	}


	public static Map<SceneName, Scene> getScenes() {
		return scenes;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
