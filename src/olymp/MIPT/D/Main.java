package olymp.MIPT.D;

import java.io.*;


/**
 * User: Roman
 * Date: 06.01.13
 */
public class Main {
  private static void solve() throws IOException {
    int n = rInt();
    int m = rInt();
    int[] blocks = new int[n + 2];
    int[] seasons = new int[m + 2];
    for (int i = 1; i <= n; i++) {
      blocks[i] = rInt();
    }
    for (int i = 1; i <= m; i++) {
      seasons[i] = rInt();
    }
    int block = 1;
    int season = 1;
    int sBlock = 1;
    int sSeason = 1;
    do {
      out.println(block + "ACV" + String.format("%02d", sBlock) + " S" + String.format("%02d", season) + "E" + String.format("%02d", sSeason));
      sBlock++;
      sSeason++;
      if (sBlock > blocks[block]) {
        sBlock = 1;
        block++;
      }
      if (sSeason > seasons[season]) {
        sSeason = 1;
        season++;
      }
    } while (block <= n);
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
