package openEndScene.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import openEndScene.controller.MainController;

public class Opening implements ViewMaker {

	private Stage stage;
	private TextField height = new TextField();
	private TextField width = new TextField();

	public Opening(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {

		// Inject stage from Main into controller
		MainController controller = new MainController(stage);

		GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(0, 10, 0, 10));

		Label label = new Label("Height");
		label.setFont(new Font(25));
		gridPane.add(label, 0, 0);
		gridPane.add(height, 1, 0);

		Label label2 = new Label("Width");
		label2.setFont(new Font(25));
		gridPane.add(label2, 0, 1);
		gridPane.add(width, 1, 1);

		Button goButton = new Button("Go");
		goButton.setOnMousePressed(e -> controller.handleOnPressButtonGo(e, Integer.valueOf(height.getText()), Integer.valueOf(width.getText())));
		ButtonBar bbar = new ButtonBar();
		bbar.setPadding(new Insets(10));
		bbar.getButtons().add(goButton);
		gridPane.add(bbar, 1, 2);

		Button endingButton = new Button("Ending");
		endingButton.setOnMousePressed(e -> controller.handleOnPressButtonEnding(e));
		ButtonBar bbarEnding = new ButtonBar();
		bbarEnding.setPadding(new Insets(10));
		bbarEnding.getButtons().add(endingButton);
		gridPane.add(bbarEnding, 1, 3);

		Scene scene = new Scene(gridPane, 500, 500);

		return scene;
	}
}