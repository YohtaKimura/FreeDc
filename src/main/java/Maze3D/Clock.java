package Maze3D;

import clockNumber.ClockNumber;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Clock extends Group {
  private ClockNumber clockNumber = new ClockNumber();
  public Clock() {
    int[][] firstHourArray = clockNumber.getFirstHourArray();
    int height = clockNumber.getHeight();
    int width = clockNumber.getWidth();
    for(int i = 0;  i < height; i++) {
          for(int j = 0; j < width; j++) {
            if (firstHourArray[i][j] == 1) {
              System.out.println(i);
              System.out.println(j);
              Wall wall = new Wall(Color.GRAY, new Point3D(i, 50, j));
              getChildren().add(wall);
            }
          }
    }
  }

  public boolean isCollision(SimpleCube cube) {
    return getChildren().stream().
        map(n -> (SimpleCube) n).
        anyMatch(c -> c.isColliding(cube));
  }
}
