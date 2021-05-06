package freeOni;

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
  private Snake snake = new Snake();

  private SimpleCube origin = new SimpleCube(Color.SPRINGGREEN);
  private SimpleCube food = new SimpleCube(Color.YELLOW);
  private Enemy enemy = new Enemy(snake);
  private ThreadLocalRandom random = ThreadLocalRandom.current();

  private double t = 0;
  private AnimationTimer timer;

  private Scene createScene() {
    food.setTranslateX(random.nextInt(10) - 5);
    food.setTranslateY(0);
    food.setTranslateZ(random.nextInt(10) - 5);

    origin.setTranslateX(0);
    origin.setTranslateY(0);
    origin.setTranslateY(0);

    root.getChildren().addAll(snake, food, origin, enemy);
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

  private void moveFood() {
    food.setTranslateX(random.nextInt(10) - 5);
    food.setTranslateY(0);
    food.setTranslateZ(random.nextInt(10) - 5);
  }

  private void onUpdate() {
    snake.onUpdate();
    enemy.onUpdate();
    if (snake.isCollision(food)) {
      moveFood();
      snake.grow();
    }
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
          snake.setDirectionPositiveZ();
          break;
        case S:
          snake.setDirectionNegativeZ();
          break;
        case A:
          snake.setDirectionNegativeX();
          break;
        case D:
          snake.setDirectionPositiveX();
          break;
        case UP:
          snake.setDirectionNegativeY();
          break;
        case DOWN:
          snake.setDirectionPositiveY();
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
