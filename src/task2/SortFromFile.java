package task2;

import java.io.*;
import java.util.*;

/**
 * User: Roman
 * Date: 14.10.12
 */
public class SortFromFile {
  private static void solve() throws IOException {
    List<Long> list = new ArrayList<Long>();
    while (st.nextToken() != StreamTokenizer.TT_EOF) {
      list.add((long) st.nval);
    }
    out.println(Arrays.toString(list.toArray()));
    Collections.sort(list);
    out.println(Arrays.toString(list.toArray()));
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);


    try {
      final String fileName = "sort";
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
  private static BufferedReader br;

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
