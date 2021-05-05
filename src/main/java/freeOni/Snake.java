package freeOni;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Snake extends Group {

  private Point3D direction = new Point3D(1, 0, 0);
  private Point3D currentPoint = new Point3D(0, 0, 0);

  public Snake() {
    SimpleCube simpleCube = new SimpleCube(Color.BLUE);
    getChildren().add(simpleCube);
  }

  public void onUpdate() {
    currentPoint = currentPoint.add(direction);
    SimpleCube c = (SimpleCube) getChildren().remove(0);
    c.set(currentPoint);
    getChildren().add(c);
    direction = new Point3D(0, 0, 0);
  }

  public void grow() {
    SimpleCube simpleCube = new SimpleCube(Color.BLUE);
    simpleCube.set(currentPoint.add(direction));
    getChildren().add(simpleCube);
  }

  public boolean isCollision(SimpleCube cube) {
    return getChildren().stream().
        map(n -> (SimpleCube) n).
        anyMatch(c -> c.isColliding(cube));
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
}
