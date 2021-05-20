package Maze3DGame.view;

import Maze3DGame.controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(0, 30, 0, 30));

		Label label = new Label("Height");
		label.setFont(new Font(25));
		gridPane.add(label, 0, 0);
		gridPane.add(height, 1, 0);

		Label label2 = new Label("Width");
		label2.setFont(new Font(25));
		gridPane.add(label2, 0, 1);
		gridPane.add(width, 1, 1);

		Label label6 = new Label("Goal");
		label6.setFont(new Font(25));
		gridPane.add(label6, 0, 4);
		gridPane.add(new Rectangle(20, 20, Color.PINK), 1, 4);

		Label label7 = new Label("You");
		label7.setFont(new Font(25));
		gridPane.add(label7, 0, 5);
		gridPane.add(new Rectangle(20, 20, Color.BLUE), 1, 5);

		Label label8 = new Label("Enemy");
		label8.setFont(new Font(25));
		gridPane.add(label8, 0, 6);
		gridPane.add(new Rectangle(20, 20, Color.RED), 1, 6);

		Text text = new Text();
		text.setText("You have to get goal. If you fail... \nAnyway you have to get your goal back. \nWhen you are in this maze, you gonna run away from enemies.\nI don't care if you die. But...");
		gridPane.add(text, 0, 7, 1, 2);

		Button goButton = new Button("Go");
		goButton.setOnMousePressed(e -> controller.handleOnPressButtonGo(e, Integer.valueOf(height.getText()), Integer.valueOf(width.getText())));
		ButtonBar bbar = new ButtonBar();
		bbar.setPadding(new Insets(10));
		bbar.getButtons().add(goButton);
    gridPane.add(bbar, 1, 8);

		Button endingButton = new Button("Ending");
		endingButton.setOnMousePressed(e -> controller.handleOnPressButtonEnding(e));
		ButtonBar bbarEnding = new ButtonBar();
		bbarEnding.setPadding(new Insets(10));
		bbarEnding.getButtons().add(endingButton);
		gridPane.add(bbarEnding, 1, 9);

		Scene scene = new Scene(gridPane, 800, 600);

		return scene;
	}
};