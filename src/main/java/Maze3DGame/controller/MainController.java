package Maze3DGame.controller;

import Maze3DGame.Main;
import Maze3DGame.model.SceneName;
import Maze3DGame.view.maze3D.Maze3D;
import java.io.File;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class MainController {
	private Stage stage;

	/** Inject the stage from {@link Main} */
	public MainController(Stage stage) {
		this.stage = stage;
	}

		/** Display the first scene */
	public void handleOnPressButtonGo(MouseEvent event, int height, int width) {
	  Map<SceneName, Scene> scenes = Main.getScenes();
	  scenes.put(SceneName.MAZE, new Maze3D().createMazeScene(height, width, stage));
	  stage.setScene(Main.getScenes().get(SceneName.MAZE));
	  MusicController.c.play();
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
