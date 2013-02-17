import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * User: Roman
 * Date: 30.09.12
 */
public class Hello {
  private static void solve() throws IOException {
    System.out.println(Math.log10(Integer.MAX_VALUE));


  }

  public static BufferedReader br;

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);


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

  private static StringTokenizer st;
  private static PrintWriter out;

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
