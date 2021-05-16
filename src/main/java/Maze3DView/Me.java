package Maze3DView;

import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

class Me extends SimpleCube {
  private Point3D direction = new Point3D(0, 0, 0);
  private Point3D currentPoint = new Point3D(0, 0, 0);

  public Me(Color color, Point3D currentPoint) {
    super(color, currentPoint);
    this.currentPoint = currentPoint;
  }

  public Point3D onUpdate(Group participant) {

    Point3D nextPoint = getNextPoint();
    ObservableList<Node> children  = participant.getChildren();
    Point3D cameraNextDirection;
    for (Node child: children)
    if (child instanceof Wall) {
      Wall wall = (Wall) child;
      if (CubeUtils.isColliding(nextPoint, wall.getCurrentPoint())) {
        cameraNextDirection = new Point3D(0, 0, 0);
        direction = new Point3D(0, 0, 0);
        return cameraNextDirection;
      }
    }

    set(nextPoint);
    currentPoint = nextPoint;
    cameraNextDirection = direction;
    direction = new Point3D(0, 0, 0);
    return cameraNextDirection;
  }

  public void setDirectionPositiveZ() {
    direction =new Point3D(0,0,1);
  }

  public void setDirectionNegativeZ() {
    direction = new Point3D(0, 0, -1);
  }

  public void setDirectionPositiveX() {
    direction = new Point3D(1, 0, 0);
  }

  public void setDirectionNegativeX() {
    direction = new Point3D(-1, 0, 0);
  }

  public void setDirectionPositiveY() {
    direction = new Point3D(0, 1, 0);
  }

  public void setDirectionNegativeY() {
    direction = new Point3D(0, -1, 0);
  }

  Point3D getNextPoint() {
    return new Point3D(currentPoint.getX() + direction.getX(), currentPoint.getY() + direction.getY(), currentPoint.getZ() + direction.getZ());
  }

  Point3D getDirection() {
    return direction;
  }
}
