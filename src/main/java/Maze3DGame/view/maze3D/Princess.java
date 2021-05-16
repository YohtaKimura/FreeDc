package Maze3DGame.view.maze3D;

import Maze3DGame.controller.Maze3DController;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Princess extends SimpleCube {
  private Point3D direction;
  private SimpleCube player;
  private double territoryRadius = 7;
  private ThreadLocalRandom random = ThreadLocalRandom.current();
  private Point3D currentPoint;

  public Princess(SimpleCube player, Point3D currentPoint) {
    super(Color.DEEPPINK);
    this.currentPoint = currentPoint;
    this.player = player;
    setRandomDirection();
    this.currentPoint.add(direction);
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

  public void onUpdate(Maze3DController controller, SimpleCube player, Group participant) {
    if (shouldBeExited()) {
      setPlayerDirection();
    } else {
      setRandomDirection();
    }

    Point3D nextPoint = getNextPoint();
    ObservableList<Node> children  = participant.getChildren();
    for (Node child: children)
    if (child instanceof Wall) {
      Wall wall = (Wall) child;
      if (CubeUtils.isColliding(nextPoint, wall.getCurrentPoint())) {
        direction = new Point3D(0, 0, 0);
        return;
      }
      if (CubeUtils.isColliding(nextPoint, wall.getCurrentPoint()) || CubeUtils.isColliding(currentPoint, player.getCurrentPoint())) {
        controller.handleCollisionWith();
      }
    }

    currentPoint = currentPoint.add(direction);
    set(currentPoint);
  }

  Point3D getNextPoint() {
    return new Point3D(currentPoint.getX() + direction.getX(), currentPoint.getY() + direction.getY(), currentPoint.getZ() + direction.getZ());
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
      direction = new Point3D(-1, 0, 0);
    }
    if (45 <= degree && degree < 135) {
      direction = new Point3D(0, 0, -1);
    }
    if (135 <= degree && degree <= 180) {
      direction = new Point3D(-1, 0, 0);
    }
    if (-180 <= degree  && degree < -135) {
      direction = new Point3D(1, 0, 0);
    }
    if (-135 <= degree  && degree < -45) {
      direction = new Point3D(0, 0, 1);
    }
  }

  private boolean shouldBeExited() {
    return Oni2DUtils.getDistance(player, this) < territoryRadius;
  }
}
