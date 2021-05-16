package multiScene.view;

import javafx.stage.Stage;
import multiScene.controller.ViewOneController;

public class ViewOne extends ViewBase {
	/** Must inject a stage */
	public ViewOne(Stage stage) {
		super(stage, "This is scene 1", e -> new ViewOneController(stage).handleMousePress(e));
	}
}
