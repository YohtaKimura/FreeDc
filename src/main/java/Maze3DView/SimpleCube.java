package Maze3DView;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class SimpleCube extends Box {
  protected Point3D currentPoint;

  public SimpleCube(Color color) {
    super(1,1,1);
    setMaterial(new PhongMaterial(color));
  }

  public SimpleCube(Color color, Point3D currentPoint) {
    super(1,1,1);
    setMaterial(new PhongMaterial(color));
    this.currentPoint = currentPoint;
    setTranslateX(currentPoint.getX());
    setTranslateY(currentPoint.getY());
    setTranslateZ(currentPoint.getZ());
  }

  public void set(Point3D p) {
    currentPoint = p;
    setTranslateX(p.getX());
    setTranslateY(p.getY());
    setTranslateZ(p.getZ());
  }

  public boolean isColliding(SimpleCube c) {
    return getTranslateX() == c.getTranslateX() &&
        getTranslateY() == c.getTranslateY() &&
        getTranslateZ() == c.getTranslateZ();
  }

  Point3D getCurrentPoint() {
    return currentPoint;
  }
}
