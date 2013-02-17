package olymp.MIPT.B;

import java.io.*;


/**
 * User: Roman
 * Date: 06.01.13
 */
public class Main {
  public static Long remainder(long n, long m) {
    return m >= n + 1 ? 2 * m * n + m - n : 2 * m * n + n - m + 1;
  }

  private static void solve() throws IOException {  // time limit
    long[][] ans = new long[20000][2];
    long x = rLong();  //TODO:rewrite
    int counter = 0;
    long n, m;
//    for (long i = 1; i <= x / 3 + 1; i++) {
//      for (long j = (x + i) / (2 * i + 1); j <= x / 3 + 1; j++) {
//        if (remainder(i, j) == x) {
//          ans[counter][0] = i;
//          ans[counter][1] = j;
//          counter++;
//          break;
//        }
//      }
//    }
    for (n = 1; n < (m = (x + n) / (2 * n + 1)); n++) {
      if (remainder(n, m) == x) {
        ans[counter][0] = n;
        ans[counter][1] = m;
        ++counter;
      }
    }
    for (m = 1; m <= (n = (m + x - 1) / (2 * m + 1)); m++) {

      if (remainder(n, m) == x) {
        ans[counter][0] = n;
        ans[counter][1] = m;
        ++counter;
      }
    }

    out.println(counter);
    for (int i = 0; i < counter; i++) {
      out.println(ans[i][0] + " " + ans[i][1]);
    }
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
