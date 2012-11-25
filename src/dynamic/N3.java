package dynamic;

/**
 * User: Roman
 * Date: 21.10.12
 */

import java.io.*;
import java.util.Locale;


public class N3 {
  private static void solve() throws IOException {
    int n = rInt();
    int m = rInt();
    out.println(ways(n, m));
  }

  private static final int N_MAX = 45;
  private static final int M_MAX = 45;
  public static long[][] waysArr = new long[N_MAX][M_MAX];

  public static long ways(int n, int m) {
    if ((n <= 1) || (m <= 1)) return 1;
    if (waysArr[n][m] != 0) return waysArr[n][m];
    long ans = 0;
    ans = ways(n - 1, m) + ways(n, m - 1);
    waysArr[n][m] = ans;
    return ans;
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "dyn3";
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
