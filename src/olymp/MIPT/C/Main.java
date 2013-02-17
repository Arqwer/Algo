package olymp.MIPT.C;

import java.io.*;


/**
 * User: Roman
 * Date: 06.01.13
 */
public class Main {
  private static void solve() throws IOException {
    long w = rInt();
    long h = rInt();
    long a = rInt();
    long b = rInt();
    long hb = (long) Math.floor((double) h / b);
    long wa = (long) Math.floor((double) w / a);
    long d1 = 0, d2 = 0;
    long ans;
    boolean d = true, c1 = false, c2 = false;
    if (w - wa * a != 0) {
      c1 = true;
      if (Math.ceil((double) (hb + 1) / Math.floor((double) a / (w - wa * a))) ==
          (d1 = (long) Math.ceil((double) (hb + 0) / Math.floor((double) a / (w - wa * a))))) d = false;
    }
    if (h - hb * b != 0) {
      c2 = true;
      if (Math.ceil((double) (wa + 1) / Math.floor((double) b / (h - hb * b))) ==
          (d2 = (long) Math.ceil((double) (wa + 0) / Math.floor((double) b / (h - hb * b))))) d = false;
    }
    d &= c1;
    d &= c2;
    ans = wa * hb + d1 + d2 + (d ? 1 : 0);
    out.println(ans);
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
    return Long.parseLong(rNext());
  }

  static double rDouble() throws IOException {
    return Double.parseDouble(rNext());
  }
}
