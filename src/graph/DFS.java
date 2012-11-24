package graph;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Stack;

/**
 * User: Roman
 * Date: 10.11.12
 */

public class DFS {


  static int[][] graph;// = {{0, 0, 3, 0, 8}, {0, 0, 0, 4, 0}, {3, 0, 0, 0, 6}, {0, 4, 0, 0, 0}, {8, 0, 6, 0, 0}};
  public static long[] marked;  //0 - never visited, 1 - visited, 2 - everything is discovered nearby.
  private static int NODES;
  private static int[] connected;
  public static int[] minfrom;

  public static void dfs_rec(int start, int counter) {
    marked[start] = 1;
    connected[start] = counter;
    for (int i = 0; i < NODES; i++) {
      if ((graph[start][i] != 0) && (marked[i] == 0)) {
        dfs(i, counter);
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

//  public static void bfs(int start, int counter) {
//    Queue queue = new LinkedList();
//    marked[start] = 1;
//    connected[start]=counter;
//    do {
//      for (int i = 0; i < NODES; i++) {
//        if ((graph[start][i] != 0) && (marked[i] == 0)) {
//          queue.offer(i);
//          connected[i]=counter;
//          marked[i] = 1;
//          start = i;
//          i = 0;
//        }
//
//      }
//      marked[start] = 2;
//      final Object poll = queue.poll();
//      start = poll != null ? (Integer) poll : 0;
//    } while (queue.peek() != null);
//
//  }

  public static void bfs(int start, int counter) {
    int[] d = new int[NODES];
    Arrays.fill(d, -1);
    int[] queue = new int[NODES * 10];
    int head = 0;
    int tail = 0;
    d[start] = 0;
    queue[head++] = start;
    while (head != tail) {
      int v = queue[tail++];
      connected[v] = counter;
      for (int i = 0; i < NODES; i++) {
        if ((graph[i][v] == 1) && (d[i] == -1)) {
          d[i] = d[v] + 1;
          queue[head++] = i;
        }
      }
    }
  }

  public static void dijkstra(int start) {
    int[] done = new int[NODES];
    long[] distance = new long[NODES];
    Arrays.fill(distance, Long.MAX_VALUE);
    distance[start] = 0;
    Arrays.fill(done, 0);
    //int[] queue = new int[NODES * 10];
    int head = 0;
    int tail = 0;
    done[start] = 1;
    int v = start;
    while (true) {
      for (int i = 0; i < NODES; i++) {
        if ((graph[i][v] != 0) && (done[i] != 1)) {
          if (distance[i] > distance[v] + graph[i][v]) {
            distance[i] = distance[v] + graph[i][v];
          }
        }
      }
      long minlen = Long.MAX_VALUE;
      int minlen_id = -1;
      for (int j = 0; j < NODES; j++) {
        if ((distance[j] < minlen) && (done[j] != 1)) {
          minlen = distance[j];
          minlen_id = j;
        }
      }
      if (minlen == Long.MAX_VALUE) break;
      if (minlen_id == -1) break;
      minfrom[minlen_id] = v;
      v = minlen_id;
      done[minlen_id] = 1;
    }
  }

  public static void printPath(int from, int to) {
    if (minfrom[to] == -1) {
      out.println("Path not found.");
      return;
    }
    int[] stack = new int[NODES * 10];
    int head = 0;
    stack[head++] = to;
    do {
      if (minfrom[stack[head - 1]] == -1) {
        out.println("Path not found.");
        return;
      }
      stack[head++] = minfrom[stack[head - 1]];
    } while (stack[head] != from);
    while (head != 0) {
      out.print(stack[head--] + "-->");
    }
  }

  public static void dfs(int start, int counter) {
    int[] d = new int[NODES];
    Arrays.fill(d, -1);
    int[] stack = new int[NODES * 10];
    int head = 0;
    d[start] = 0;
    stack[head++] = start;
    while (head != 0) {
      int v = stack[head--];
      connected[v] = counter;
      for (int i = 0; i < NODES; i++) {
        if ((graph[i][v] == 1) && (d[i] == -1)) {
          d[i] = d[v] + 1;
          stack[head++] = i;
        }
      }
    }
  }


  private static int unmarked() {
    for (int i = 0; i < NODES; i++) {
      if (connected[i] == 0) return i;
    }
    return -1;
  }


  public static int n_connected() {
    int counter = 0;
    // Arrays.fill(marked, 0);
    while (unmarked() != -1) {
      counter++;
      dfs(unmarked(), counter);
    }
    return counter;
  }


  private static void solve() throws IOException {
    NODES = rInt();
    marked = new long[NODES];
    connected = new int[NODES];
    graph = new int[NODES][NODES];
    minfrom = new int[NODES];
    Arrays.fill(minfrom, -1);
    final int n = rInt();
    for (int i = 0; i < n; i++) {
      int a = rInt();
      int b = rInt();
      int c = rInt();
      graph[a][b] = c;
      graph[b][a] = c;
    }

//    System.out.println(n_connected());
//    System.out.println(Arrays.toString(connected));
    dijkstra(1);
    printPath(1, 5);
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "dfs";
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
