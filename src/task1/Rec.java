package task1;

/**
 * User: Roman
 * Date: 11.11.12
 */
public class Rec {
  public static int c = 0;

  public static void main(String[] args) {
    rec();
  }

  private static void rec() {
    System.out.println(c++);
    rec();
  }
}
