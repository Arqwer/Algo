package olymp;

/**
 * User: Roman
 * Date: 09.12.12
 */
public class Task2 {
  public static void main(String[] args) {
    final int i1 = 64;
    for (int i = 1; i < i1; i++) {
      final int m = meth(i);
      System.out.println(i + " " + m);
    }
  }

  private static int meth(int m) {
    int s = 64;
    int t = 0;
    int d = 1;
    int i = 2;
    while (s > 0) {
      t += d * (s % i);
      s /= i;
      d *= 10;
      i *= m;
    }
    return t;
  }

}
