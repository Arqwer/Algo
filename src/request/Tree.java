package request;

import java.io.*;
import java.util.Locale;

/**
 * User: Roman
 * Date: 23.12.12
 */
public class Tree {
  static class Node {
    public int gcd;

    Node(int gcd) {
      this.gcd = gcd;
    }
  }

  static int[] values;
  public static Node[] gcdArr;

  private static void solve() throws IOException {
    final int n = rInt();
    final int m = rInt();

    values = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      values[i] = rInt();
    }
    //init();
    gcdInit();
    for (int i = 0; i < m; i++) {
      final char c = rNext().charAt(0);
      if (c == 'S') {
//        out.println(sum(rInt(), rInt()));
        System.out.println(getGcd(rInt(), rInt()));
      } else if (c == 'U') {
        gcdUpdate(1, 0, values.length - 1, rInt(), rInt());
      }
    }
  }


  private static void gcdInit() {
    gcdArr = new Node[4 * values.length];
    gcdBuild(1, 0, values.length - 1);
  }


  public static int gcd(long a, long b) {
    while (b != 0) {
      long t = b;
      b = a % b;
      a = t;
    }
    return (int) Math.abs(a);
  }

  public static int lcm(long a, long b) {
    return (int) Math.abs(a / gcd(a, b) * b);
  }

  private static Node gcdCombine(Node a, Node b) {
    int gcd = lcm(a.gcd, b.gcd);
    return new Node(gcd);
  }

  private static void gcdBuild(int v, int l, int r) {
    if (r == l) {
      gcdArr[v] = new Node(values[l]);
    } else {
      final int m = (l + r) / 2;
      gcdBuild(2 * v, l, m);
      gcdBuild(2 * v + 1, m + 1, r);
      gcdArr[v] = gcdCombine(gcdArr[2 * v], gcdArr[2 * v + 1]);
    }
  }

  public static int getGcd(int l, int r) {
    return tGcd(1, 0, values.length - 1, l, r).gcd;
  }

  private static Node tGcd(int v, int tl, int tr, int l, int r) {
    if (l > r) {
      return new Node(1);
    }
    if ((l == tl) && (r == tr)) {
      return gcdArr[v];
    }
    int tm = (tl + tr) / 2;
    return gcdCombine(tGcd(2 * v, tl, tm, l, Math.min(r, tm)), tGcd(2 * v + 1, tm + 1, tr, Math.max(l, tm + 1), r));
  }


  private static void gcdUpdate(int v, int tl, int tr, int pos, int new_val) {
    if (tl == tr) {
      gcdArr[v].gcd = new_val;
    } else {
      int tm = (tl + tr) / 2;
      if (pos <= tm)
        gcdUpdate(v * 2, tl, tm, pos, new_val);
      else
        gcdUpdate(v * 2 + 1, tm + 1, tr, pos, new_val);
      gcdArr[v] = gcdCombine(gcdArr[v * 2], gcdArr[v * 2 + 1]);
    }
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
