package olymp;

/**
 * User: Roman
 * Date: 09.12.12
 */
public class Task13 {
  public static int game(int n, int x) {
    int a = 1;
    int b = n;
    int steps = 0;
    while (a + 1 != b && a != b) {
      final int s = a + b;
      final int i = s % 2 == 0 ? (s >> 1) - 1 : s >> 1;
      if (x > i) {
        a = i + 1;
      } else {
        b = i;
      }
      steps++;
    }
    return steps + 1;
  }

  public static void main(String[] args) {
    int n = 512;
//    System.out.println();
    for (int i = n; i < 1030; i++) {
      int n1 = 0;
      int n2 = 0;
      for (int j = 1; j <= i; j++) {
        final int game = game(i, j);
        if (game == 9) n1++;
        if (game == 10) n2++;
      }
      if (n1 == n2 + 35) {
        System.out.println("win" + i);
      }
//      System.out.println(i);
    }
  }
}
