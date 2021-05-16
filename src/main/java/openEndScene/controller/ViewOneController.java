package openEndScene.controller;

import javafx.event.Event;
import javafx.stage.Stage;
import openEndScene.Main;
import openEndScene.model.SceneName;

public class ViewOneController {

	private Stage stage;

	/** Must inject a stage */
	public ViewOneController(Stage stage) {
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
