package multiScene.view;

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
import multiScene.controller.MainController;

public class MainView implements ViewMaker {

	private Stage stage;
	private TextField height = new TextField();
	private TextField width = new TextField();

	public MainView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {

		// Inject stage from Main into controller
		MainController controller = new MainController(stage);

		// Switch between scenes
		Button button1 = new Button("Scene 1");
		button1.setOnMousePressed(e -> controller.handleOnPressButton1(e));
/*		Button button2 = new Button("Scene 2");
		button2.setOnMousePressed(e -> controller.handleOnPressButton2(e));
		Button button3 = new Button("Scene 3");
		button3.setOnMousePressed(e -> controller.handleOnPressButton3(e));
 */

		// Build scene
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(button1);
// 	vbox.getChildren().addAll(button1, button2, button3);

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
		Scene scene = new Scene(gridPane, 500, 500);

		return scene;
	}

}