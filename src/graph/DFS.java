package graph;

import java.io.*;
import java.util.*;

/**
 * User: Roman
 * Date: 10.11.12
 */

public class DFS {


  static long[][] graph = {{0, 0, 3, 0, 8}, {0, 0, 5, 0, 1}, {3, 5, 0, 0, 6}, {0, 0, 0, 0, 0}, {8, 1, 6, 0, 0}};
  public static long[] marked = {0, 0, 0, 0, 0};  //0 - never visited, 1 - visited, 2 - everything is discovered nearby.
  private static final int NODES = 5;

  public static void dfs(int start) {
    marked[start] = 1;
    for (int i = 0; i < NODES; i++) {
      if ((graph[start][i] != 0) && (marked[i] == 0)) {
        dfs(i);
      }
    }
    marked[start] = 2;
  }

  public static void stack_dfs() {
    Stack stack = new Stack();
    int start = 0;
    marked[start] = 1;
    do {
      for (int i = 0; i < NODES; i++) {
        if ((graph[start][i] != 0) && (marked[i] == 0)) {
          stack.push(i);
          marked[i] = 1;
          start = i;
          i = 0;
        }

      }
      marked[start] = 2;
      start = (Integer) stack.pop();
    } while (!stack.empty());

  }

  public static void bfs() {
    Queue queue = new LinkedList();
    int start = 0;
    marked[start] = 1;
    do {
      for (int i = 0; i < NODES; i++) {
        if ((graph[start][i] != 0) && (marked[i] == 0)) {
          queue.offer(i);
          marked[i] = 1;
          start = i;
          i = 0;
        }

      }
      marked[start] = 2;
      start = (Integer) queue.poll();
    } while (queue.peek() != null);

  }


  private static void solve() throws IOException {
    System.out.println(Arrays.toString(marked));
    bfs();
    System.out.println(Arrays.toString(marked));
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
