import java.io.*;
import java.util.StringTokenizer;


/**
 * @author Shchedrin Roman
 * @date 18.01.2013
 */
public class NewPattern {
  private static void solve() throws IOException {

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