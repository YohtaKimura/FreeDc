package multiScene.controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import multiScene.Main;
import multiScene.model.SceneName;

public class MainController {
	private Stage stage;

	/** Inject the stage from {@link Main} */
	public MainController(Stage stage) {
		this.stage = stage;
	}

	/** Display the first scene */
	public void handleOnPressButton1(MouseEvent event) {
		stage.setScene(Main.getScenes().get(SceneName.SCENE1));
	}

		/** Display the first scene */
	public void handleOnPressButtonGo(MouseEvent event, int height, int width) {
	  Scene s = Main.getScenes().get(SceneName.SCENE1);
    BorderPane borderPane = (BorderPane) s.getRoot();
    borderPane.setLeft(new Label(String.valueOf(height)));
		stage.setScene(Main.getScenes().get(SceneName.SCENE1));
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
