package olymp.MIPT.K;

import java.io.*;
import java.util.StringTokenizer;


/**
 * @author Shchedrin Roman
 * @date 18.01.2013
 */
public class Main {
  public static char[][] map1;
  public static char[][] map2;
  public static int[][] areasMap;
  public static int areas;
  public static int inaccessible;

  public static boolean isTable(int i, int j) {
    if (i < 0 || j < 0) return false;
    if (i >= map1.length || j >= map1[0].length) return false;
    return map1[i][j] == 'T';
  }

  public static boolean isAccessible(int i, int j) {
    if (i < 0 || j < 0) return false;
    if (i >= map2.length || j >= map2[0].length) return false;
    return map2[i][j] == '+';
  }

  public static void setMap(char c, int i, int j) {
    map1[i][j] = c;
    switch (c) {
      case '.': {
        for (int k = i * 3; k < i * 3 + 3; k++) {
          for (int l = j * 3; l < j * 3 + 3; l++) {
            if (map2[k][l] != '+') {
              if (map2[k][l] != '.') {
                inaccessible++;
                map2[k][l] = '.';
              }
            }
          }
        }
      }
      break;
      case 'C': {
        if (isTable(i, j - 1) || isTable(i, j + 1)) {
          for (int k = 3 * j; k < 3 * j + 3; k++) {
            if (map2[i * 3 + 1][k] == '.') inaccessible--;
            map2[i * 3 + 1][k] = '#';
          }
          if (isTable(i, j + 1)) {
            if (map2[i * 3 + 1][3 * j + 3] == '.') inaccessible--;
            map2[i * 3 + 1][3 * j + 3] = '#';
            break;
          }
          if (isTable(i, j - 1)) {
            if (map2[i * 3 + 1][3 * j - 1] == '.') inaccessible--;
            map2[i * 3 + 1][3 * j - 1] = '#';
            break;
          }
        }
        if (isTable(i + 1, j) || isTable(i - 1, j)) {
          for (int k = 3 * i; k < 3 * i + 3; k++) {
            if (map2[k][3 * j + 1] == '.') inaccessible--;
            map2[k][3 * j + 1] = '#';
          }
          if (isTable(i + 1, j)) {
            if (map2[3 * i + 3][3 * j + 1] == '.') inaccessible--;
            map2[3 * i + 3][3 * j + 1] = '#';
            break;
          }
          if (isTable(i - 1, j)) {
            if (map2[3 * i - 1][3 * j + 1] == '.') inaccessible--;
            map2[3 * i - 1][3 * j + 1] = '#';
            break;
          }
        }
      }
      break;
      case 'T': {
        if (map2[3 * i + 1][3 * j + 1] == '.') inaccessible--;
        map2[3 * i + 1][3 * j + 1] = '#';
      }
      break;
    }
  }

  public static void m2init() {
    map2 = new char[map1.length * 3][map1[0].length * 3];
    for (int i = 0; i < map2.length; i++) {
      for (int j = 0; j < map2[0].length; j++) {
        map2[i][j] = '.';
      }
    }
    for (int i = 0; i < map2.length; i++) {
      map2[i][0] = '+';
    }
    for (int i = 0; i < map2.length; i++) {
      map2[i][map2[0].length - 1] = '+';
    }
    for (int i = 0; i < map2[0].length; i++) {
      map2[0][i] = '+';
    }
    for (int i = 0; i < map2[0].length; i++) {
      map2[map2.length - 1][i] = '+';
    }
    inaccessible = 0;
    for (int i = 0; i < map2.length; i++) {
      for (int j = 0; j < map2[0].length; j++) {
        if (map2[i][j] == '.') inaccessible++;
      }
    }

    areas = 1;
    for (int i = 0; i < areasMap.length; i++) {
      for (int j = 0; j < areasMap[0].length; j++) {
        if (map2[i][j] == '.') {
          areasMap[i][j] = areas++;
        }
      }
    }
    for (int i = 0; i < areasMap.length; i++) {
      areasMap[i][0] = 1;
    }
    for (int i = 0; i < areasMap.length; i++) {
      areasMap[i][areasMap[0].length - 1] = 1;
    }
    for (int i = 0; i < areasMap[0].length; i++) {
      areasMap[0][i] = 1;
    }
    for (int i = 0; i < areasMap[0].length; i++) {
      areasMap[areasMap.length - 1][i] = 1;
    }

  }

  private static int gArMa(int i, int j) {
    if (i < 0) return 0;
    if (j < 0) return 0;
    if (i >= areasMap.length) return 0;
    if (j >= areasMap[0].length) return 0;
    return areasMap[i][j];
  }

  public static int qMin(int a, int b, int c, int d, int def) {
    if (a <= b && a <= c && a <= d && a != 0) return a;
    if (b <= a && b <= c && b <= d && b != 0) return b;
    if (c <= a && c <= b && c <= d && c != 0) return c;
    if (d <= a && d <= b && d <= c && d != 0) return d;
    return def;
  }

  public static boolean step() {
    boolean res = false;
    int amax = 0;
    for (int i = 0; i < map2.length; i++) {
      for (int j = 0; j < map2[0].length; j++) {
        if (map2[i][j] == '.') {
          if (isAccessible(i, j - 1) ||
              isAccessible(i, j + 1) ||
              isAccessible(i - 1, j) ||
              isAccessible(i + 1, j)) {
            map2[i][j] = '+';
            inaccessible--;
            areasMap[i][j] =
                qMin(
                    gArMa(i + 1, j),
                    gArMa(i, j + 1),
                    gArMa(i - 1, j),
                    gArMa(i, j - 1),
                    gArMa(i, j)
                );
            res = true;
          }
        }
        if (areasMap[i][j] > amax) amax = areasMap[i][j];
      }
    }
    areas = amax;
    return res;
  }

  public static boolean isOk() {
    while (step()) ;
    return areas == 1;
  }

  private static void solve() throws IOException {
    int n = rInt(), m = rInt();
    areasMap = new int[9 * n][9 * m];
    map1 = new char[3 * n][3 * m];
    m2init();
    for (int i = 0; i < 3 * n; i++) {
      map1[i] = rNext().toCharArray();
    }
    for (int i = 0; i < map1.length; i++) {
      for (int j = 0; j < map1[0].length; j++) {
        setMap(map1[i][j], i, j);
      }
    }
    System.out.println(isOk());
    for (int i = 0; i < map2.length; i++) {
      for (int j = 0; j < map2[0].length; j++) {
        System.out.print(map2[i][j]);
      }
      System.out.println();
    }


  }

  public static BufferedReader br;
  private static StringTokenizer st;
  private static PrintWriter out;

  public static void main(String[] args) throws IOException {
    try {
      final String fileName = "";
      br = new BufferedReader(new FileReader(fileName + ".in"));
      out = new PrintWriter(new FileWriter(fileName + ".out"));
    } catch (Exception e) {
      br = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new OutputStreamWriter(System.out));
    }
    st = new StringTokenizer(br.readLine());
    solve();
    br.close();
    out.close();
  }

  static String rNext() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      st = new StringTokenizer(br.readLine());
    }
    return st.nextToken();
  }

  static int rInt() throws IOException {
    return Integer.parseInt(rNext());
  }

  static long rLong() throws IOException {
    return Long.parseLong(rNext());
  }

  static double rDouble() throws IOException {
    return Double.parseDouble(rNext());
  }
}