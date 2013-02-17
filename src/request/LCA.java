package request;

import java.io.*;
import java.util.StringTokenizer;

/**
 * User: Roman
 * Date: 17.02.13
 */
public class LCA {
  public static int[][] graph;
  public static int[][] up;
  public static int[] tin;
  public static int[] tout;
  public static int NODES;
  public static int timer;
  public static int l;

  public static void dfs(int v, int p) {
    tin[v] = ++timer;
    up[v][0] = p;
    for (int i = 1; i < l; i++) {
      up[v][i] = up[up[v][i - 1]][i - 1];
    }
    for (int i = 0; i < NODES; i++) {
      if (graph[v][i] != 0 && i != p) {
        dfs(i, v);
      }
    }
    tout[v] = ++timer;
  }


  public static boolean upper(int a, int i) {
    return (tin[a] < tin[i]) && (tout[a] > tout[i]);
  }

  public static int lca(int a, int b) {
    for (int i = l; i >= 0; i--) {
      if (!upper(up[a][i], b)) {
        a = up[a][i];
      }
    }
    return up[a][0];
  }

  private static void solve() throws IOException {
    NODES = rInt();
    graph = new int[NODES][NODES];
    l = 1;
    while (1 << l < NODES) l++;
    up = new int[NODES][l + 1];
    tin = new int[NODES];
    tout = new int[NODES];
    for (int i = 0; i < NODES; i++) {
      for (int j = 0; j < NODES; j++) {
        graph[i][j] = rInt();
      }
    }
    timer = 0;
    dfs(0, 0);
    System.out.println(lca(5, 6));
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
