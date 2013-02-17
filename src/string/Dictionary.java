package string;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * User: Roman
 * Date: 24.01.13
 */
public class Dictionary {
  public static final int NUM = 31;
  public static final int SIZE = 1000001;

  static class Vertex {
    public int[] next;
    public boolean isLeaf;
    public int numDict;

    public Vertex() {
      next = new int[NUM];
      Arrays.fill(next, -1);
      isLeaf = false;
    }

  }

  public static Vertex[] bor;
  public static int size;


  public static void addString(String s, int j) {
    int v = 0;
    for (int i = 0; i < s.length(); i++) {
      final int index = s.charAt(i) - 'a' + 1;
      if (bor[v].next[index] == -1) {
        bor[size] = new Vertex();
        bor[v].next[index] = size;
        size++;
      }
      v = bor[v].next[index];
    }
    bor[v].isLeaf = true;
    bor[v].numDict = j;
  }


  private static void solve() throws IOException {
    initBor();
    final char[] text = rNext().toCharArray();
    final int n = rInt();
    boolean[] res = new boolean[n];
    for (int i = 0; i < n; i++) {
      final String s = rNext();
      res[i] = false;
      addString(s, i);
    }
    for (int i = 0; i < text.length; i++) {
      int v = 0;
      while (true) {
        int cur = i;
        if (bor[v].isLeaf) {
          res[bor[v].numDict] = true;
        }
        v = bor[v].next[text[cur++] - 'a' + 1];
        if (v == -1 || cur >= text.length) {
          break;
        }
      }
    }
    System.out.println(Arrays.toString(res));
  }

  private static void initBor() {
    bor = new Vertex[SIZE];
    bor[0] = new Vertex();
    size = 1;
  }

  public static BufferedReader br;
  private static StringTokenizer st;
  private static PrintWriter out;

  public static void main(String[] args) throws IOException {
    try {
      final String fileName = "";
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

