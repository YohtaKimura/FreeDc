package Wall;

import Maze3D.MazeWall;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class MainApp extends Application {

  private Group root = new Group();

  private SimpleCube origin = new SimpleCube(Color.SPRINGGREEN);
  private Me me = new Me(Color.BLUE, new Point3D(0, 0, 0));
  private ThreadLocalRandom random = ThreadLocalRandom.current();
  private Wall wall = new Wall(Color.GRAY, new Point3D(3, 0, 3));

  private double t = 0;
  private AnimationTimer timer;

  private Scene createScene() {

    origin.setTranslateX(0);
    origin.setTranslateY(0);
    origin.setTranslateZ(0);

    root.getChildren().addAll(origin, me, wall);
    Scene scene = new Scene(root, 1280, 720, true);
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.getTransforms().addAll(new Translate(0, -30, -30), new Rotate(-45, Rotate.X_AXIS));
    scene.setCamera(camera);

    timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        t += 0.016;
        if (t > 0.1) {
          onUpdate();
          t = 0;
        }
      }
    };
    timer.start();
    return scene;
  }

  private void onUpdate() {
    me.onUpdate(wall);
  }

  @Override
  public void start(Stage stage)throws Exception {
/*
    final Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
    final Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

    stage.setTitle("FavaFx and Gradle");
    stage.setScene(scene);
    stage.show();
*/
    Scene scene = createScene();
    scene.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case W:
          me.setDirectionPositiveZ();
          break;
        case S:
          me.setDirectionNegativeZ();
          break;
        case A:
          me.setDirectionNegativeX();
          break;
        case D:
          me.setDirectionPositiveX();
          break;
        case UP:
          me.setDirectionNegativeY();
          break;
        case DOWN:
          me.setDirectionPositiveY();
          break;
      }
    });
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
