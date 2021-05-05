package free;

import java.util.Random;
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
  private Point3D dir = new Point3D(1, 0, 0);
  private Point3D next = new Point3D(0, 0, 0);
  private Group root = new Group();
  private Group snake = new Group();
  private Cube food = new Cube(Color.YELLOW);
  private ThreadLocalRandom random = ThreadLocalRandom.current();

  private double t = 0;
  private AnimationTimer timer;

  private Scene createScene() {
    Cube cube = new Cube(Color.BLUE);
    snake.getChildren().add(cube);

    food.setTranslateX(random.nextInt(10) - 5);
    food.setTranslateY(random.nextInt(10) - 5);
    food.setTranslateZ(random.nextInt(10) - 5);

    root.getChildren().addAll(snake, food);
    Scene scene = new Scene(root, 1280, 720, true);
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.getTransforms().addAll(new Translate(0, -20, -20), new Rotate(-45, Rotate.X_AXIS));
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

  private void moveFood() {
    food.setTranslateX(random.nextInt(10) - 5);
    food.setTranslateY(random.nextInt(10) - 5);
    food.setTranslateZ(random.nextInt(10) - 5);
  }

  private void onUpdate() {
    next = next.add(dir);
    Cube c = (Cube) snake.getChildren().remove(0);
    c.set(next);
    snake.getChildren().add(c);
    dir = new Point3D(0, 0, 0);

    boolean collision = snake.getChildren()
        .stream()
        .map(n -> (Cube) n)
        .anyMatch( cube -> cube.isColliding(food));
    if (collision) {
      grow();
    }
  }

  void grow() {
    moveFood();
    Cube cube = new Cube(Color.BLUE);
    cube.set(next.add(dir));

    snake.getChildren().add(cube);
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
          dir = new Point3D(0, 0, 1);
          break;
        case S:
          dir = new Point3D(0, 0, -1);
          break;
        case A:
          dir = new Point3D(-1, 0, 0);
          break;
        case D:
          dir = new Point3D(1, 0, 0);
          break;
        case UP:
          dir = new Point3D(0, -1, 0);
          break;
        case DOWN:
          dir = new Point3D(0, 1, 0);
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
