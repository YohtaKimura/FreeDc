package clockNumber;

import java.time.LocalDateTime;

public class ClockNumber {

  LocalDateTime localDateTime = LocalDateTime.now();
  public int getHeight() {
    return 8;
  }

  public int getWidth() {
    return 5;
  }

  public int[][] getFirstHourArray() {
    int h = localDateTime.getHour();
    int result = h/10;
    return getNumberArray(result);
  }

  public int[][] getSecondHourArray() {
    int h = localDateTime.getHour();
    int result = h%10;
    return getNumberArray(result);
  }

  public int[][] getFirstMinuteArray() {
    int m = localDateTime.getMinute();
    int result = m/10;
    return getNumberArray(result);
  }

  public int[][] getSecondMinuteArray() {
    int m = localDateTime.getMinute();
    int result = m%10;
    return getNumberArray(result);
  }

  private int[][] getNumberArray(int number) {
    switch (number) {
      case 0:
        return ZeroArray.getZero();
      case 1:
        return OneArray.getOne();
      case 2:
        return TwoArray.getTwo();
      case 3:
        return ThreeArray.getThree();
      case 4:
        return FourArray.getfour();
      case 5:
        return FiveArray.getFive();
      case 6:
        return SixArray.getSix();
      case 7:
        return SevenArray.getSeven();
      case 8:
        return EightArray.getEight();
      case 9:
        return NineArray.getNine();
      default:
        return null;
    }
  }
}
