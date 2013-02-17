package training.region_2011;

import java.io.*;
import java.util.StringTokenizer;

/**
 * User: Roman
 * Date: 18.01.13
 */
public class Wheel {
  private static void solve() throws IOException {
    int n = rInt();
    int[] wheel = new int[n];
    int[] rWheel = new int[n];
    for (int i = 0; i < n; i++) {
      wheel[i] = rInt();
      rWheel[n - i - 1] = wheel[i];
    }
    int vMin = rInt();
    int vMax = rInt();
    int resistance = rInt();
    int xMin = 0;
    int xMax = 0;
    int max = 0;
    boolean wholeWheel = false;
    xMin = vMin / resistance;
    xMax = vMax / resistance;
    if (xMax - xMin >= n) wholeWheel = true;
    xMin %= n;
    xMax %= n;
    if (xMin != xMax) {
      if (wholeWheel) {
        for (int i = 0; i < n; i++) {
          if (wheel[i] > max) {
            max = wheel[i];
          }
          if (rWheel[(i - 1 + n) % n] > max) {
            max = rWheel[(i - 1 + n) % n];
          }
        }
      } else if (xMin < xMax) {
        for (int i = xMin; i < xMax; i++) {
          if (wheel[i] > max) {
            max = wheel[i];
          }
          if (rWheel[(i - 1 + n) % n] > max) {
            max = rWheel[(i - 1 + n) % n];
          }
        }
      } else {
        for (int i = xMin; i < n; i++) {
          if (wheel[i] > max) {
            max = wheel[i];
          }
          if (rWheel[(i - 1 + n) % n] > max) {
            max = rWheel[(i - 1 + n) % n];
          }
        }
        for (int i = 0; i < xMax; i++) {
          if (wheel[i] > max) {
            max = wheel[i];
          }
          if (rWheel[(i - 1 + n) % n] > max) {
            max = rWheel[(i - 1 + n) % n];
          }
        }
      }
    } else {
      if (wheel[xMin] > max) {
        max = wheel[xMin];
      }
      if (wheel[(n - xMin) % n] > max) {
        max = wheel[(n - xMin) % n];
      }

    }
    out.println(max);
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
