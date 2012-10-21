package dynamic;

/**
 * User: Roman
 * Date: 21.10.12
 */

import java.io.*;
import java.util.Locale;


public class N3 {
  private static void solve() throws IOException {

  }

  private static final int N_MAX = 30;
  private static final int M_MAX = 30;
  public static long[][] waysArr = new long[N_MAX][M_MAX];

  public static long ways(int n, int m) {
    if ((n <= 1) || (m <= 1)) return 1;
    if (waysArr[n][m] != 0) return waysArr[n][m];
    long ans = 0;
    for (int i = 1; i < m; i++) {
      ans += ways(n - 1, i);
    }
    return ans;
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
