package olymp.MIPT.A;

import java.io.*;


/**
 * User: Roman
 * Date: 05.01.13
 */
public class Main {
  private static void solve() throws IOException {
    long n = rLong();
    long k = rLong();
    long min, max;
    min = (long) Math.ceil((double) n / k);
    max = n - k + 1;
    out.print(min + " " + max);
  }

  public static void main(String[] args) throws IOException {


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
    st.nextToken();
    return (long) st.nval;
  }

  static double rDouble() throws IOException {
    return Double.parseDouble(rNext());
  }
}
