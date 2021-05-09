package Maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {
  List<Coord> nodes = new ArrayList<>();
  int[][] maze;
  int height;
  int width;
  ThreadLocalRandom random = ThreadLocalRandom.current();
  Deque<Coord> history = new ArrayDeque<>();
  public Maze(int height, int width){
    // height and width must be odd and greater than 5
    this.height = height;
    this.width = width;
    maze = new int[height][width];
    makeWall();
    makeNodes();
    makeMaze();
  }

  void print() {
    System.out.println("path---------------");
        for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.out.print(maze[i][j]);
      }
      System.out.print("\n");
    }
    System.out.println("nodes---------------");
        nodes.forEach( n -> System.out.print(n));
      System.out.print("\n");
  }

  void makeWall() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (i == 0) {
          maze[i][j] = 1;
        }
        if (j == 0 ) {
          maze[i][j] = 1;
        }
        if (i == height - 1 ) {
          maze[i][j] = 1;
        }
        if (j == width - 1 ) {
          maze[i][j] = 1;
        }
      }
    }
  }

  void makeNodes() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (j%2 == 0 && i !=0 && j !=0 && i != height - 1 && j != width -1 && i%2 == 0) {
          nodes.add(new Coord(i, j));
        }
      }
    }
  }

  void extendsWall(int i, int j) {
    List<Direction> directions = new ArrayList<>();
    if (maze[i][j - 1] == 0 && !history.contains(new Coord(i, j - 2))) {
        directions.add(Direction.UP);
    }
    if (maze[i + 1][j] == 0 && !history.contains(new Coord(i + 2, j))) {
         directions.add(Direction.RIGHT);
    }
    if (maze[i][j + 1] == 0 && !history.contains(new Coord(i, j + 2))) {
      directions.add(Direction.DOWN);
    }
    if (maze[i - 1][j] == 0 && !history.contains(new Coord(i -2, j ))) {
      directions.add(Direction.LEFT);
    }
    if (!directions.isEmpty()) {
      setWall(i, j);

      boolean shouldContinue = false;
      int dirInd = random.nextInt(directions.size());
      int nextI;
      int nextJ;
      switch (directions.get(dirInd)) {
        case UP:
          shouldContinue = maze[i][j - 2] == 0;
          setWall(i, j - 1);
          setWall(i, j - 2);
          nextI = i;
          nextJ = j - 2;
          break;
        case RIGHT:
          shouldContinue = maze[i + 2][j] == 0;
          setWall(i + 1, j);
          setWall(i + 2, j);
          nextI = i + 2;
          nextJ = j;
          break;
        case DOWN:
          shouldContinue = maze[i][j + 2] == 0;
          setWall(i, j + 1);
          setWall(i, j + 2);
          nextI = i;
          nextJ = j + 2;
          break;
        case LEFT:
          shouldContinue = maze[i - 2][j] == 0;
          setWall(i - 1, j);
          setWall(i - 2, j);
          nextI = i - 2;
          nextJ = j;
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + directions.get(dirInd));
      }
      if (shouldContinue) {
        extendsWall(nextI, nextJ);
      }
    } else {
      Coord before = history.removeFirst();
      extendsWall(before.height, before.width);
    }
  }

  void setWall(int height, int width) {
      maze[height][width] = 1;
      history.addFirst(new Coord(height, width));
  }

  void makeMaze() {
    while (!nodes.isEmpty()) {
      int index = random.nextInt(nodes.size());
      Coord c = nodes.remove(index);
      int i = c.height;
      int j = c.width;
      if (maze[i][j] == 0) {
        history.clear();
        extendsWall(i, j);
      }
    }
  }

  public int[][] getMaze(){
    return maze;
  }

  static class Coord {
    int height;
    int width;
    Coord(int height, int width) {
      this.height = height;
      this.width = width;
    }
    @Override
    public boolean equals(Object obj) {
      Coord another = (Coord) obj;
      return this.height == another.height && this.width == another.width;
    }

    @Override
    public String toString() {
      return "(" + height + ", " + width + ")";
    }
  }


 static enum Direction{
    UP,RIGHT, LEFT, DOWN;
 }

  static public void main(String[] args) {
    Maze m = new Maze(15,15);
    m.print();
  }
}
