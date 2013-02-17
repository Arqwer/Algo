import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yuri Denison
 * @date 12.02.12
 */
public class PatternOld implements Runnable {
  public void solve() throws IOException {

  }

  public static void main(String[] args) {
    (new Thread(new PatternOld())).start();
  }

  private StringTokenizer st;
  private PrintWriter out;
  private BufferedReader br;

  public void run() {
    Locale.setDefault(Locale.US);
    Logger logger = Logger.getLogger(PatternOld.class.getName());
    try {
      try {
        final String fileName = "";
        br = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new FileWriter(fileName + ".out"));
      } catch (FileNotFoundException e) {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
      }
      try {
        solve();
      } catch (Exception e) {
        logger.log(Level.SEVERE, null, e);
        System.exit(1);
      }
      br.close();
      out.close();
    } catch (IOException e) {
      logger.log(Level.SEVERE, null, e);
    }
  }

  String rNext() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      st = new StringTokenizer(br.readLine());
    }
    return st.nextToken();
  }

  int rInt() throws IOException {
    return Integer.parseInt(rNext());
  }

  long rLong() throws IOException {
    return Long.parseLong(rNext());
  }

  double rDouble() throws IOException {
    return Double.parseDouble(rNext());
  }

  String rLine() throws IOException {
    if (st == null || !st.hasMoreTokens()) {
      return br.readLine();
    } else {
      String result = st.nextToken();
      while (st.hasMoreTokens()) {
        result += " " + st.nextToken();
      }
      return result;
    }
  }

  boolean rReady() throws IOException {
    return br.ready() || (st != null && st.hasMoreTokens());
  }

  void dbg(Object... os) {
    System.err.println(Arrays.deepToString(os));
  }
}
