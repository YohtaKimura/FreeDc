package Maze3DView;

import Maze.Maze;
import freeOni.SimpleCube;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class MazeWall extends Group {
  int[][] mazeInt;
  Map<Point3D, Boolean> pointIsPath;
  List<Point3D> mazePoints;
  public MazeWall(int height, int width) {
    Maze maze = new Maze(height, width);
    pointIsPath = new HashMap<>();
    mazePoints = new ArrayList<>();
    mazeInt = maze.getMaze();
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        if (mazeInt[i][j] == 1) {
          Point3D wallPoint = new Point3D(i - (height - 1)/2, 0, j - (width - 1)/2);
          Wall wall = new Wall(Color.GRAY, wallPoint);
          pointIsPath.put(wallPoint, false);
          mazePoints.add(wallPoint);
          getChildren().add(wall);
        } else {
          Point3D pathPoint = new Point3D(i - (height - 1)/2, 0, j - (width - 1)/2);
          pointIsPath.put(pathPoint, true);
          mazePoints.add(pathPoint);
        }
      }
    }
  }

  public boolean isCollision(SimpleCube cube) {
    return getChildren().stream().
        map(n -> (SimpleCube) n).
        anyMatch(c -> c.isColliding(cube));
  }

  public boolean isPath(Point3D point) {
    return pointIsPath.get(point);
  }

  public List<Point3D> getMazePoints() {
    return mazePoints;
  }
}
