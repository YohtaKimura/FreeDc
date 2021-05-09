package Maze3D;

import Maze.Maze;
import freeOni.SimpleCube;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class MazeWall extends Group {

  public MazeWall(int height, int width) {
    Maze maze = new Maze(height, width);
    int[][] mazeInt = maze.getMaze();
    for(int i = 0; i < 15; i++) {
          for(int j = 0; j < 15; j++) {
            if (mazeInt[i][j] == 1) {
              Wall wall = new Wall(Color.GRAY, new Point3D(i - (height - 1)/2, 0, j - (width - 1)/2));
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
