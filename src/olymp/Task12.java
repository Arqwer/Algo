package olymp;

/**
 * User: Roman
 * Date: 09.12.12
 */
public class Task12 {
  public static int[][] arr = {
      {1, 0, 1, 1, 0, 0, 1, 1},
      {1, 0, 0, 0, 1, 1, 1, 1},
      {0, 0, 0, 0, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 1, 1},
      {0, 0, 1, 1, 1, 1, 1, 0},
      {0, 0, 0, 0, 1, 1, 1, 1},
      {0, 0, 0, 0, 1, 1, 1, 0},
      {0, 0, 1, 1, 0, 0, 1, 0}
  };

  public static void main(String[] args) {
    for (int i = 1; i < 100; i++) {
      fun(i);
    }
  }

  public static void fun(int m) {
    int c = 0;
    int d = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (arr[i][j] == 0 && arr[j][i] == 1) {
          c++;
        } else {
          c = 0;
        }
        if (c == m) d++;
      }
    }
    System.out.println(m + " " + d);
  }
}
