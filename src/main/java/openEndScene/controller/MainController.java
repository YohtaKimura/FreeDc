package openEndScene.controller;

import Maze3DView.Maze3D;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import openEndScene.Main;
import openEndScene.model.SceneName;

public class MainController {
	private Stage stage;

	/** Inject the stage from {@link Main} */
	public MainController(Stage stage) {
		this.stage = stage;
	}

		/** Display the first scene */
	public void handleOnPressButtonGo(MouseEvent event, int height, int width) {
	  Map<SceneName, Scene> scenes = Main.getScenes();
	  scenes.put(SceneName.MAZE, new Maze3D().createMazeScene(height, width));
	  stage.setScene(Main.getScenes().get(SceneName.MAZE));
	}

	public void handleOnPressButtonEnding(MouseEvent event) {
	  stage.setScene(Main.getScenes().get(SceneName.ENDING));
	}

	/*
	public void handleOnPressButton2(MouseEvent event) {
		stage.setScene(Main.getScenes().get(SceneName.SCENE2));
	}

	public void handleOnPressButton3(MouseEvent event) {
		stage.setScene(Main.getScenes().get(SceneName.SCENE3));
	}
	*/
}
