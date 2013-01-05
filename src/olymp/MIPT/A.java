package olymp.MIPT;

import java.io.*;
import java.util.Locale;

/**
 * User: Roman
 * Date: 05.01.13
 */
public class A {
  private static void solve() throws IOException {
    int n = rInt(), k = rInt();
    int min, max;
    min = (int) Math.ceil((float) n / k);
    max = n - k + 1;
    out.print(min + " " + max);
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "";
      br = new BufferedReader(new FileReader("input.txt"));
      out = new PrintWriter(new FileWriter("output.txt"));
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
