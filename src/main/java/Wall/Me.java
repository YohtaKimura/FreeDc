package Wall;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

class Me extends SimpleCube{
  private Point3D direction = new Point3D(1, 0, 0);
  private Point3D currentPoint = new Point3D(0, 0, 0);

  public Me(Color color, Point3D currentPoint) {
    super(color, currentPoint);
  }

  public void onUpdate(SimpleCube participant) {

    Point3D nextPoint = getNextPoint();
    if (participant instanceof Wall) {
      Wall wall = (Wall) participant;
      if (CubeUtils.isColliding(nextPoint, wall.getCurrentPoint())) {
        return;
      }
    }

    set(nextPoint);
    currentPoint = nextPoint;
    direction = new Point3D(0, 0, 0);
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
}
