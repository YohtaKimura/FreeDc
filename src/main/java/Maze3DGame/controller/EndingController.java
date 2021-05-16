package Maze3DGame.controller;

import Maze3DGame.Main;
import Maze3DGame.model.SceneName;
import javafx.event.Event;
import javafx.stage.Stage;

public class EndingController {

	private Stage stage;

	/** Must inject a stage */
	public EndingController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}

		this.stage = stage;
	}

	/** Display main scene when the "back" button is clicked */
	public void handleMousePress(Event event) {
		stage.setScene(Main.getScenes().get(SceneName.OPENING));
	}
}
