package olymp.IOIP1.Halk;

import java.io.*;
import java.util.StringTokenizer;


/**
 * @author Shchedrin Roman
 * @date 18.01.2013
 */
public class Main {
  private static void solve() throws IOException {
    int dimensions = rInt();
    int[] ice = new int[dimensions];
    int[] h1 = new int[dimensions];
    int[] h2 = new int[dimensions];
    for (int i = 0; i < dimensions; i++) {
      ice[i] = rInt();
    }
    for (int i = 0; i < dimensions; i++) {
      h1[i] = rInt();
    }
    for (int i = 0; i < dimensions; i++) {
      h2[i] = rInt();
    }
    int ans = dimensions * 2;
    for (int i = 0; i < dimensions; i++) {
      if (h1[i] == ice[i]) ans--;
      if (h1[i] == 0) ans--;
      if (h2[i] == ice[i]) ans--;
      if (h2[i] == 0) ans--;
    }
    out.println(ans);
  }

  public static BufferedReader br;
  private static StringTokenizer st;
  private static PrintWriter out;

  public static void main(String[] args) throws IOException {
    try {
      final String fileName = "frozen";
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