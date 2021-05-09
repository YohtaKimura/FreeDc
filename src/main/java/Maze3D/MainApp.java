package Maze3D;

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
  private Clock clock = new Clock();
  private MazeWall mazeWall = new MazeWall(15, 15);

  private ThreadLocalRandom random = ThreadLocalRandom.current();
  private PerspectiveCamera camera = new PerspectiveCamera(true);
  private Translate cameraPosition = new Translate(0, -30, -30);
  private double cameraAngleDegree = - 45.;
  private Rotate cameraAngle = new Rotate(cameraAngleDegree, Rotate.X_AXIS);
  private double t = 0;
  private AnimationTimer timer;

  private Scene createScene() {

    origin.setTranslateX(0);
    origin.setTranslateY(0);
    origin.setTranslateZ(0);

    root.getChildren().addAll(origin, me, mazeWall, clock);
    Scene scene = new Scene(root, 1280, 720, true);

    camera.getTransforms().addAll(cameraPosition,  cameraAngle);
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
    cameraPosition.setX(cameraPosition.getTx() + me.getDirection().getX());
    cameraPosition.setZ(cameraPosition.getTz() + me.getDirection().getZ());
    cameraAngle.setAngle(cameraAngleDegree);

    me.onUpdate(mazeWall);
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
        case J:
          cameraAngleDegree++;
          break;
        case K:
          cameraAngleDegree--;
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
