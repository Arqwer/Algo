package olymp.IOIP1.Tesseract;

import java.io.*;
import java.util.StringTokenizer;


/**
 * @author Shchedrin Roman
 * @date 18.01.2013
 */
public class Main {


  private static void solve() throws IOException {
    String s = rNext();
    long res = s.length();
    for (int i = 1; i < s.length() - 1; i++) {
      for (int d = 1; d <= Math.min(i, s.length() - i - 1); d++) {
        if (s.charAt(i - d) == s.charAt(i + d)) {
          res++;
        } else {
          break;
        }
      }
    }
    for (int i = 1; i < s.length(); i++) {
      for (int d = 0; d <= Math.min(i - 1, s.length() - i - 1); d++) {
        if (s.charAt(i - d - 1) == s.charAt(i + d)) {
          res++;
        } else {
          break;
        }
      }
    }
    boolean simetric = true;
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - i - 1)) simetric = false;
    }
//    out.println(simetric);
    if (simetric) res--;
    out.println(res);
  }

  public static BufferedReader br;
  private static StringTokenizer st;
  private static PrintWriter out;

  public static void main(String[] args) throws IOException {
    try {
      final String fileName = "tesseract";
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