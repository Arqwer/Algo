package request;

import java.io.*;
import java.util.Locale;

/**
 * User: Roman
 * Date: 02.12.12
 */
public class PartialSum {
  static class Node {
    public int min;
    public int amount;

    Node(int min, int amount) {
      this.min = min;
      this.amount = amount;
    }
  }

  static int[] values;
  public static int[] psum;
  public static int[] tSumArr;
  public static Node[] tMinArr;

  private static void solve() throws IOException {
    final int n = rInt();
    final int m = rInt();

    values = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      values[i] = rInt();
    }
    //init();
    minInit();
    for (int i = 0; i < m; i++) {
      final char c = rNext().charAt(0);
      if (c == 'S') {
//        out.println(sum(rInt(), rInt()));
        System.out.println(getMin(rInt(), rInt()));
      } else if (c == 'U') {
        tMinUpdate(1, 0, values.length - 1, rInt(), rInt());
      }
    }
  }

  private static void init() {
    psum = new int[values.length + 1];
    int sum = 0;
    for (int i = 1; i <= values.length; i++) {
      sum += values[i - 1];
      psum[i] = sum;
    }
  }

  private static void tInit() {
    tSumArr = new int[4 * values.length];
    build(1, 0, values.length - 1);
  }

  private static void build(int v, int l, int r) {
    if (r == l) {
      tSumArr[v] = values[l];
    } else {
      final int m = (l + r) / 2;
      build(2 * v, l, m);
      build(2 * v + 1, m + 1, r);
      tSumArr[v] = tSumArr[2 * v] + tSumArr[2 * v + 1];
    }
  }

  private static void minInit() {
    tMinArr = new Node[4 * values.length];
    minBuild(1, 0, values.length - 1);
  }

  private static Node combine(Node a, Node b) {
    if (a.min > b.min) return b;
    if (a.min < b.min) return a;
    return new Node(a.min, a.amount + b.amount);
  }

  private static void minBuild(int v, int l, int r) {
    if (r == l) {
      tMinArr[v] = new Node(values[l], 1);
    } else {
      final int m = (l + r) / 2;
      minBuild(2 * v, l, m);
      minBuild(2 * v + 1, m + 1, r);
      tMinArr[v] = combine(tMinArr[2 * v], tMinArr[2 * v + 1]);
    }
  }

  private static int tSum(int v, int tl, int tr, int l, int r) {
    if (l > r) {
      return 0;
    }
    if ((l == tl) && (r == tr)) {
      return tSumArr[v];
    }
    int tm = (tl + tr) / 2;
    return tSum(2 * v, tl, tm, l, Math.min(r, tm)) + tSum(2 * v + 1, tm + 1, tr, Math.max(l, tm + 1), r);
  }

  public static int getMin(int l, int r) {
    return tMin(1, 0, values.length - 1, l, r).min;
  }

  private static Node tMin(int v, int tl, int tr, int l, int r) {
    if (l > r) {
      return new Node(Integer.MAX_VALUE, 0);
    }
    if ((l == tl) && (r == tr)) {
      return tMinArr[v];
    }
    int tm = (tl + tr) / 2;
    return combine(tMin(2 * v, tl, tm, l, Math.min(r, tm)), tMin(2 * v + 1, tm + 1, tr, Math.max(l, tm + 1), r));
  }

  private static void tUpdate(int v, int tl, int tr, int pos, int new_val) {
    if (tl == tr) {
      tSumArr[v] = new_val;
    } else {
      int tm = (tl + tr) / 2;
      if (pos <= tm)
        tUpdate(v * 2, tl, tm, pos, new_val);
      else
        tUpdate(v * 2 + 1, tm + 1, tr, pos, new_val);
      tSumArr[v] = tSumArr[v * 2] + tSumArr[v * 2 + 1];
    }
  }

  private static void tMinUpdate(int v, int tl, int tr, int pos, int new_val) {
    if (tl == tr) {
      tMinArr[v].min = new_val;
      tMinArr[v].amount = 1;
    } else {
      int tm = (tl + tr) / 2;
      if (pos <= tm)
        tMinUpdate(v * 2, tl, tm, pos, new_val);
      else
        tMinUpdate(v * 2 + 1, tm + 1, tr, pos, new_val);
      tMinArr[v] = combine(tMinArr[v * 2], tMinArr[v * 2 + 1]);
    }
  }

  private static void update(int pos, int val) {
    values[pos] = val;
    int sum = 0;
    for (int i = 1; i <= values.length; i++) {
      sum += values[i - 1];
      psum[i] = sum;
    }
  }

  private static int sum(int i, int j) {
    return psum[j] - psum[i - 1];
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "request";
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
