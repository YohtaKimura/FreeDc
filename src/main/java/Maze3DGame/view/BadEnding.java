package Maze3DGame.view;

import Maze3DGame.controller.EndingController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import multiScene.view.ViewMaker;

public class BadEnding implements ViewMaker {
	private Stage stage;
  private EndingController endingController;

	public BadEnding(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}
		this.endingController = new EndingController(stage);
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		Label label = new Label("You are killed");
		label.setFont(new Font(32));
		root.setCenter(label);

		Button backButton = new Button("Back");
		backButton.setOnMousePressed((e) -> {
			endingController.handleMousePress(e);
		});
		Button closeButton = new Button("Close");
		closeButton.setOnMousePressed(e -> stage.close());

		ButtonBar bbar = new ButtonBar();
		bbar.setPadding(new Insets(10, 0, 0, 10));
		bbar.getButtons().addAll(backButton, closeButton);
		root.setBottom(bbar);

		return new Scene(root);
	}
}
