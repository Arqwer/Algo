package request;

import java.io.*;
import java.util.Locale;

/**
 * User: Roman
 * Date: 02.12.12
 */
public class PartialSum {
  static int[] values;
  public static int[] psum;

  private static void solve() throws IOException {
    final int n = rInt();
    final int m = rInt();

    values = new int[n];
    for (int i = 0; i < n; i++) {
      values[i] = rInt();
    }
    init();
    for (int i = 0; i < m; i++) {
      final char c = rNext().charAt(0);
      if (c == 'S') {
        out.println(sum(rInt(), rInt()));
        System.out.println(sum(rInt(), rInt()));
      } else if (c == 'U') {
        update(rInt(), rInt());
      }
    }
  }

  private static void init() {
    psum = new int[values.length + 1];
    int sum = 0;
    for (int i = 1; i <= values.length; i++) {
      sum += values[i - 1];
      psum[i] = sum;
    }
  }

  private static void update(int pos, int val) {
    // TODO: implement
  }

  private static int sum(int i, int j) {
    return psum[j] - psum[i - 1];
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "request";
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
