package Maze3DGame.view.maze3D;

import javafx.geometry.Point3D;

class CubeUtils {
  static boolean isColliding(Point3D one, Point3D another) {
    // TODO consider double
    return one.getX() == another.getX() && one.getY() == another.getY() && one.getZ() == another.getZ();
  }
}
