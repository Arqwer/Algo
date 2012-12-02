package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Stack;

/**
 * User: Roman
 * Date: 10.11.12
 */

public class DFS {


  public static final long INF = Long.MAX_VALUE;

  static class Edge {
    int from;
    int to;
    long weight;

    Edge(int from, int to, long weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  static int[][] graph;// = {{0, 0, 3, 0, 8}, {0, 0, 0, 4, 0}, {3, 0, 0, 0, 6}, {0, 4, 0, 0, 0}, {8, 0, 6, 0, 0}};
  public static long[] marked;  //0 - never visited, 1 - visited, 2 - everything is discovered nearby.
  private static int NODES;
  private static int[] connected;
  public static int[] minfrom;
  public static ArrayList<Edge> edges;


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
    boolean[] done = new boolean[NODES];
    long[] distance = new long[NODES];
    Arrays.fill(distance, Long.MAX_VALUE);
    distance[start] = 0;
    //int[] queue = new int[NODES * 10];
    int head = 0;
    int tail = 0;
    int v = start;
    while (true) {

      long minlen = Long.MAX_VALUE;
      int minlen_id = -1;
      for (int j = 0; j < NODES; j++) {
        if ((distance[j] < minlen) && (!done[j])) {
          minlen = distance[j];
          minlen_id = j;
        }
      }
      if (minlen_id == -1) break;

      done[minlen_id] = true;
      v = minlen_id;
      for (int i = 0; i < NODES; i++) {
        if ((graph[i][v] != 0) && (!done[i])) {
          if (distance[i] > distance[v] + graph[i][v]) {
            distance[i] = distance[v] + graph[i][v];
            minfrom[i] = v;
          }
        }
      }

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
    out.print(stack[head]);
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

  public static void fordBellman(int start) {
    long[] distance = new long[NODES];
    Arrays.fill(distance, INF);
    distance[start] = 0;
    boolean relaxed;
    do {
      relaxed = false;
      for (Edge edge : edges) {
        if (edge.from != INF) {
          if (edge.weight + distance[edge.from] < distance[edge.to]) {
            distance[edge.to] = edge.weight + distance[edge.from];
            minfrom[edge.to] = edge.from;
            relaxed = true;
          }
        }
      }
    } while (relaxed);
  }

  public static long[][] floyd() {
    long[][] distance = new long[NODES][NODES];
    for (int i = 0; i < NODES; i++) {
      for (int j = 0; j < NODES; j++) {
        distance[i][j] = graph[i][j] == 0 ? INF : graph[i][j];
      }
    }
    for (int k = 0; k < NODES; k++) {
      for (int i = 0; i < NODES; i++) {
        for (int j = 0; j < NODES; j++) {
          if (distance[i][k] != INF && distance[k][j] != INF) {
            distance[i][j] = Math.min(
                distance[i][j],
                distance[i][k] + distance[k][j]
            );
          }
        }
      }
    }
    return distance;
  }
//
//  public static List<Edge> prima(){
//    long minLength = Long.MAX_VALUE;
//    int curNode = 0;
//    Edge curEdge;
//    boolean addeed[] = new boolean[NODES];
//
//
//    for (Edge edge:edges){
//      if((edge.from == curNode)&&(edge.weight<minLength)){
//        minLength = edge.weight;
//        curEdge = edge;
//      }
//    }
//    curNode = curEdge.to;
//  }

  private static void solve() throws IOException {
    NODES = rInt();
    marked = new long[NODES];
    connected = new int[NODES];
    graph = new int[NODES][NODES];
    minfrom = new int[NODES];
    Arrays.fill(minfrom, -1);
    final int n = rInt();
    edges = new ArrayList<Edge>();

    for (int i = 0; i < n; i++) {
      int a = rInt();
      int b = rInt();
      int c = rInt();
      graph[a][b] = c;
      graph[b][a] = c;
      edges.add(new Edge(a, b, c));
//      edges.add(new Edge(b, a, c));
    }

//    System.out.println(n_connected());
//    System.out.println(Arrays.toString(connected));
    final long[][] floyd = floyd();
    printPath(0, 2);
    System.out.println(Arrays.toString(minfrom));
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
