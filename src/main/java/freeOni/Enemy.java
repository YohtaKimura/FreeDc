package freeOni;

import java.util.concurrent.ThreadLocalRandom;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Enemy extends SimpleCube {
  private Point3D direction;
  private ThreadLocalRandom random = ThreadLocalRandom.current();
  private Point3D currentPoint = new Point3D(
      random.nextInt(10) -5,
      0,
      random.nextInt(10) -5
  );

  public Enemy() {
    super(Color.RED);
    setRandomDirection();
    currentPoint.add(direction);
    set(currentPoint);
  }

  public void set(Point3D p) {
    setTranslateX(p.getX());
    setTranslateY(p.getY());
    setTranslateZ(p.getZ());
  }

  public  boolean isColliding(SimpleCube c) {
    return getTranslateX() == c.getTranslateX() &&
        getTranslateY() == c.getTranslateY() &&
        getTranslateZ() == c.getTranslateZ();
  }

  public void onUpdate() {
    setRandomDirection();
    currentPoint = currentPoint.add(direction);
    set(currentPoint);
  }

  private void setRandomDirection() {
    switch (random.nextInt(4)) {
      case 0:
        direction = new Point3D(0, 0, 1);
        break;
      case 1:
        direction = new Point3D(0, 0, -1);
        break;
      case 2:
        direction = new Point3D(-1, 0, 0);
        break;
      case 3:
        direction = new Point3D(1, 0, 0);
        break;
      default:
        throw new IllegalStateException();
    }
  }
}
