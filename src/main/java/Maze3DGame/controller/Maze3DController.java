package Maze3DGame.controller;

import Maze3DGame.Main;
import Maze3DGame.model.SceneName;
import javafx.event.Event;
import javafx.stage.Stage;

public class Maze3DController {
  private Stage stage;
	public Maze3DController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}

		this.stage = stage;
	}

	/** Display main scene when the "back" button is clicked */
	public void handleCollisionWith(Event event) {
		stage.setScene(Main.getScenes().get(SceneName.ENDING));
	}

	public void handleCollisionWith() {
		stage.setScene(Main.getScenes().get(SceneName.ENDING));
	}

}
