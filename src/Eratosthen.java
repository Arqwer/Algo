import java.io.*;
import java.util.Arrays;
import java.util.Locale;

/**
 * User: Roman
 * Date: 28.10.12
 */
public class Eratosthen {

  private static void solve() throws IOException {
    int n = rInt();
    out.println(Arrays.toString(eratosthen(n)));
  }

  public static long[] eratosthen(int n) {
    boolean[] sieve = new boolean[n];
    long[] res = new long[n];
    for (int i = 2; i < Math.sqrt(n); i++) {
      if (!sieve[i]) {
        for (int j = i * i; j < n; j += i) {
          sieve[j] = true;
        }
      }
    }
    for (int i = 2, j = 0; i < n; i++) {
      if (!sieve[i]) {
        res[j] = i;
        j++;
      }
    }
    return res;
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "Eratosthen";
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
