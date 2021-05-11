package Maze3D;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.LightBase;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class MainApp extends Application {

  private Group root = new Group();

  private SimpleCube origin = new SimpleCube(Color.SPRINGGREEN);
  private Me me;
  private Enemy enemy;
  private Clock clock = new Clock();
  private MazeWall mazeWall = new MazeWall(15, 15);

  private ThreadLocalRandom random = ThreadLocalRandom.current();
  private PerspectiveCamera camera = new PerspectiveCamera(true);
  private Translate initialCameraPosition = new Translate(0, -30, -30);
  private double cameraAngleDegree = - 45.;
  private Rotate cameraAngle = new Rotate(cameraAngleDegree, Rotate.X_AXIS);
  private LightBase light = new PointLight();


  private double t = 0;
  private AnimationTimer timer;

  private Scene createScene() {

    origin.setTranslateX(0);
    origin.setTranslateY(0);
    origin.setTranslateZ(0);

    List<Point3D> pathPoints = mazeWall.mazePoints.stream().filter(mazeWall::isPath).collect(Collectors.toList());
    Point3D a = pathPoints.get(random.nextInt(pathPoints.size()));
    Point3D initialMePosition = pathPoints.get(random.nextInt(pathPoints.size()));
    me = new Me(Color.BLUE, initialMePosition);
    initialCameraPosition.setX(initialCameraPosition.getTx() + initialMePosition.getX());
    initialCameraPosition.setZ(initialCameraPosition.getTz() + initialMePosition.getZ());

    enemy = new Enemy(me, pathPoints.get(random.nextInt(pathPoints.size())));
    light.getTransforms().add( new Translate( 50 , -100.0 , 0 ) );

    root.getChildren().addAll(origin, me, enemy, mazeWall, clock, light);
    Scene scene = new Scene(root, 1280, 720, true);

    camera.getTransforms().addAll(initialCameraPosition,  cameraAngle);
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
    Point3D cameraNextDir = me.onUpdate(mazeWall);
    initialCameraPosition.setX(initialCameraPosition.getTx() + cameraNextDir.getX());
    initialCameraPosition.setZ(initialCameraPosition.getTz() + cameraNextDir.getZ());
    cameraAngle.setAngle(cameraAngleDegree);
    clock.onUpdate();
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
