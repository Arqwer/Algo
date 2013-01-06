package olymp.MIPT.B;

import java.io.*;
import java.util.Locale;


/**
 * User: Roman
 * Date: 06.01.13
 */
public class Main {
  public static Long remainder(long n, long m) {
    return m >= n + 1 ? 2 * m * n + m - n : 2 * m * n + n - m + 1;
  }

  private static void solve() throws IOException {
    long[][] ans = new long[20][2];
    long x = rLong();  //TODO:rewrite
    int counter = 0;
    for (int i = 1; i <= x / 3 + 1; i++) {
      for (int j = 1; j <= x / 3 + 1; j++) {
        if (remainder(i, j) == x) {
          ans[counter][0] = i;
          ans[counter][1] = j;
          counter++;
          break;
        }
      }
    }
    // System.out.println(Integer.MAX_VALUE);
    System.out.println(counter);
    for (int i = 0; i < counter; i++) {
      System.out.println(ans[i][0] + " " + ans[i][1]);
    }
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "";
      br = new BufferedReader(new FileReader(fileName + ".in"));
      out = new PrintWriter(new FileWriter(fileName + ".out"));
    } catch (Exception e) {
      br = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new OutputStreamWriter(System.out));
    }
    st = new StreamTokenizer(br);

    solve();

    br.close();
    out.close();
  }

  private static StreamTokenizer st;
  private static PrintWriter out;

  static String rNext() throws IOException {
    st.nextToken();
    return st.sval;
  }

  static int rInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

  static long rLong() throws IOException {
    return Long.parseLong(rNext());
  }

  static double rDouble() throws IOException {
    return Double.parseDouble(rNext());
  }
}
