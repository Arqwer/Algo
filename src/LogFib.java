import java.io.*;
import java.util.Locale;

/**
 * User: Roman
 * Date: 21.10.12
 */
public class LogFib {
  private static void solve() throws IOException {
    int n = rInt();
    System.out.println(matrixPow(q[1], n - 1)[0]);

  }

  public static long[] matrixMult(long[] a, long[] b) {
    long[] res = new long[4];
    res[0] = a[0] * b[0] + a[1] * b[2];
    res[1] = a[0] * b[1] + a[1] * b[3];
    res[2] = a[2] * b[0] + a[3] * b[2];
    res[3] = a[2] * b[1] + a[3] * b[3];
    return res;
  }

  public static boolean[] closest2(long a) {
    boolean[] r = new boolean[64];
    long n = 1;
    for (int i = 0; (i < 64) && (n <= a); i++) {
      r[i] = ((a & n) != 0);
      n = n << 1;

    }
    return r;
  }

  public static long[][] q = new long[64][4];

  static {
    q[1] = new long[]{1, 1, 1, 0};
  }

  public static long[] matrixPow2(long[] a, int b) {
    if (b == 1) return a;
    if (q[b][0] != 0) return q[b];
    long[] first = matrixPow2(a, b / 2);
    q[b] = matrixMult(first, first);
    return q[b];
  }

  public static long[] matrixPow(long[] a, int n) {
    boolean[] b = closest2(n);
    long[] ans = a.clone();
    for (int i = 0; i < 64; i++) {
      if (b[i]) ans = matrixMult(ans, matrixPow2(a, i));
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
