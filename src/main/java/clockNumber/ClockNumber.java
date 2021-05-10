package clockNumber;

import java.time.LocalDateTime;

public class ClockNumber {


  public int getHeight() {
    return 8;
  }

  public int getWidth() {
    return 5;
  }

  public int getFirstHour() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int h = localDateTime.getHour();
    return h/10;
  }

  public int[][] getFirstHourArray() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int h = localDateTime.getHour();
    int result = h/10;
    return getNumberArray(result);
  }

  public int getSecondHour() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int h = localDateTime.getHour();
    return h%10;
  }

  public int[][] getSecondHourArray() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int h = localDateTime.getHour();
    int result = h%10;
    return getNumberArray(result);
  }

  public int getFirstMinute() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int h = localDateTime.getMinute();
    return h/10;
  }

  public int[][] getFirstMinuteArray() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int m = localDateTime.getMinute();
    int result = m/10;
    return getNumberArray(result);
  }

  public int getSecondMinute() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int h = localDateTime.getMinute();
    return h%10;
  }

  public int[][] getSecondMinuteArray() {
    LocalDateTime localDateTime = LocalDateTime.now();
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

  public int[][] getColon(){
    return ColonArray.getColon();
  }
}
