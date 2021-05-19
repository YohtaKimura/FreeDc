package Maze3DGame.view;

import Maze3DGame.controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    gridPane.setHgap(30);
    gridPane.setVgap(30);
    gridPane.setPadding(new Insets(0, 30, 0, 30));

		Label label = new Label("Height");
		label.setFont(new Font(25));
		gridPane.add(label, 0, 0);
		gridPane.add(height, 1, 0);

		Label label2 = new Label("Width");
		label2.setFont(new Font(25));
		gridPane.add(label2, 0, 1);
		gridPane.add(width, 1, 1);

		Label label3 = new Label("Explanation");
		label3.setFont(new Font(25));
		gridPane.add(label3, 0, 2);
		gridPane.add(new Text("You have to save lover"), 1, 2);

		Label label4 = new Label("You");
		label4.setFont(new Font(25));
		gridPane.add(label4, 0, 3);
		gridPane.add(new Text("You"), 1, 3);

		Label label5 = new Label("Enemy");
		label5.setFont(new Font(25));
		gridPane.add(label5, 0, 4);
		gridPane.add(new Text("Enemy"), 1, 4);

		Label label6 = new Label("Goal");
		label6.setFont(new Font(25));
		gridPane.add(label6, 0, 5);
		gridPane.add(new Text("Goal"), 1, 5);

		Button goButton = new Button("Go");
		goButton.setOnMousePressed(e -> controller.handleOnPressButtonGo(e, Integer.valueOf(height.getText()), Integer.valueOf(width.getText())));
		ButtonBar bbar = new ButtonBar();
		bbar.setPadding(new Insets(10));
		bbar.getButtons().add(goButton);
    gridPane.add(bbar, 1, 6);

		Button endingButton = new Button("Ending");
		endingButton.setOnMousePressed(e -> controller.handleOnPressButtonEnding(e));
		ButtonBar bbarEnding = new ButtonBar();
		bbarEnding.setPadding(new Insets(10));
		bbarEnding.getButtons().add(endingButton);
		gridPane.add(bbarEnding, 1, 7);

		Scene scene = new Scene(gridPane, 500, 500);

		return scene;
	}
};