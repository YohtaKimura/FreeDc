package Maze3D;

import javafx.scene.Group;

class Oni2DUtils {
  static double getDegree(Group one, SimpleCube another) {
    SimpleCube target = one.getChildren().stream().map(n -> (SimpleCube) n).findFirst().get();
    double targetX = target.getTranslateX();
    double targetZ = target.getTranslateZ();
    double radian = Math.atan2(targetZ - another.getTranslateZ() ,targetX - another.getTranslateX());
    return radian * 180d / Math.PI;
  }

  static double getDistance(Group one, SimpleCube another) {
    SimpleCube target = one.getChildren().stream().map(n -> (SimpleCube) n).findFirst().get();
    double targetX = target.getTranslateX();
    double targetZ = target.getTranslateZ();
    return Math.sqrt(Math.pow(targetX - another.getTranslateX(), 2) + Math.pow(targetZ - another.getTranslateZ(), 2));
  }

  static double getDegree(SimpleCube one, SimpleCube another) {
    double targetX = one.getTranslateX();
    double targetZ = one.getTranslateZ();
    double radian = Math.atan2(targetZ - another.getTranslateZ() ,targetX - another.getTranslateX());
    return radian * 180d / Math.PI;
  }

  static double getDistance(SimpleCube one, SimpleCube another) {
    double targetX = one.getTranslateX();
    double targetZ = one.getTranslateZ();
    return Math.sqrt(Math.pow(targetX - another.getTranslateX(), 2) + Math.pow(targetZ - another.getTranslateZ(), 2));
  }

}
