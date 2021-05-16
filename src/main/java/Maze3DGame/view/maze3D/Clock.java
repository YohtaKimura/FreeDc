package Maze3DGame.view.maze3D;

import clockNumber.ClockNumber;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Clock extends Group {
  private ClockNumber clockNumber = new ClockNumber();
  private int firstHour = clockNumber.getFirstHour();
  private int secondHour = clockNumber.getSecondHour();
  private int firstMinute = clockNumber.getFirstMinute();
  private int secondMinute = clockNumber.getSecondMinute();

  public Clock() {
refresh();
  }

    private void refresh() {
    int[][] firstHourArray = clockNumber.getFirstHourArray();
    int[][] secondHourArray = clockNumber.getSecondHourArray();
    int[][] colon = clockNumber.getColon();
    int[][] firstMinuteArray = clockNumber.getFirstMinuteArray();
    int[][] secondMinuteArray = clockNumber.getSecondMinuteArray();

    int height = clockNumber.getHeight();
    int width = clockNumber.getWidth();
    int space = 4;

        for(int i = 0;  i < height; i++) {
      for(int j = 0; j < width; j++) {
        if (firstHourArray[i][j] == 1) {
          Wall wall = new Wall(Color.GRAY, new Point3D(j - space - 2 * width , 50, - i ));
          getChildren().add(wall);
        }
      }
    }

    for(int i = 0;  i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (secondHourArray[i][j] == 1) {
          Wall wall = new Wall(Color.GRAY, new Point3D(j - space - width, 50, - i ));
          getChildren().add(wall);
        }
      }
    }

    for(int i = 0;  i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (colon[i][j] == 1) {
          Wall wall = new Wall(Color.GRAY, new Point3D(j, 50, - i ));
          getChildren().add(wall);
        }
      }
    }

    for(int i = 0;  i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (firstMinuteArray[i][j] == 1) {
          Wall wall = new Wall(Color.GRAY, new Point3D(j + space + width, 50, -i));
          getChildren().add(wall);
        }
      }
    }

    for(int i = 0;  i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (secondMinuteArray[i][j] == 1) {
          Wall wall = new Wall(Color.GRAY, new Point3D(j + space + 2 * width, 50, -i));
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

  public void onUpdate() {
    if (firstHour != clockNumber.getFirstHour() || secondHour != clockNumber.getSecondHour()
        || firstMinute != clockNumber.getFirstMinute() || secondMinute != clockNumber
        .getSecondMinute()) {

      firstHour = clockNumber.getFirstHour();
      secondHour = clockNumber.getSecondHour();
      firstMinute = clockNumber.getFirstMinute();
      secondMinute = clockNumber.getSecondMinute();
      getChildren().clear();
      refresh();
    }
  }
}
