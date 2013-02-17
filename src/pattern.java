import java.io.*;
import java.util.StringTokenizer;

class Pattern {
  public static void solve() throws IOException {
  }

  public static BufferedReader br;
  public static PrintWriter out;
  public static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    String filename = "";
    try {
      br = new BufferedReader(new FileReader(filename + ".in"));
      out = new PrintWriter(new FileWriter(filename + ".out"));
    } catch (Exception e) {
      br = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new OutputStreamWriter(System.out));
    }
    st = new StringTokenizer(br.readLine());
    solve();
    br.close();
    out.close();
  }

  private static String rNext() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      st = new StringTokenizer(br.readLine());
    }
    return st.nextToken();
  }

  private static int rInt() throws IOException {
    return Integer.parseInt(rNext());
  }
}