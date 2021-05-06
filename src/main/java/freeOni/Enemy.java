package freeOni;

import java.util.concurrent.ThreadLocalRandom;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Enemy extends SimpleCube {
  private Point3D direction;
  private Group player;
  private double territoryRadius = 7;
  private ThreadLocalRandom random = ThreadLocalRandom.current();
  private Point3D currentPoint = new Point3D(
      random.nextInt(20) - 10,
      0,
      random.nextInt(20) - 10
  );

  public Enemy(Group player) {
    super(Color.RED);
    this.player = player;
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
    if (shouldBeExited()) {
      setPlayerDirection();
    } else {
      setRandomDirection();
    }
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

  private void setPlayerDirection() {
    double degree = Oni2DUtils.getDegree(player, this);

    if (-45 <= degree && degree < 45) {
      direction = new Point3D(1, 0, 0);
    }
    if (45 <= degree && degree < 135) {
      direction = new Point3D(0, 0, 1);
    }
    if (135 <= degree && degree <= 180) {
      direction = new Point3D(-1, 0, 0);
    }
    if (-180 <= degree  && degree < -135) {
      direction = new Point3D(-1, 0, 0);
    }
    if (-135 <= degree  && degree < -45) {
      direction = new Point3D(0, 0, -1);
    }
  }

  private boolean shouldBeExited() {
    return Oni2DUtils.getDistance(player, this) < territoryRadius;
  }
}
