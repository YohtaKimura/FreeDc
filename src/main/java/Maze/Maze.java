package Maze;

class Maze {
  int[][] field;
  int height;
  int width;

  Maze(int height, int width){
    // height and width must be odd
   field = new int[height][width];
  }
}
