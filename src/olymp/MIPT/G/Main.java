package olymp.MIPT.G;

import java.io.*;
import java.util.Arrays;

/**
 * User: Roman
 * Date: 07.01.13
 */
public class Main {

  public static long[][] graph;
  public static long[][] areaGraph;
  public static int[] citisArea;
  public static int[] areasCity;
  public static int NODES;
  public static final long INF = Long.MAX_VALUE;


  public static long[][] floyd(long[][] graph, int NODES) {
    long[][] distance = new long[Main.NODES][Main.NODES];
    for (int i = 0; i < Main.NODES; i++) {
      for (int j = 0; j < Main.NODES; j++) {
        distance[i][j] = Main.graph[i][j] == -1 ? INF : Main.graph[i][j];
      }
    }
    for (int k = 0; k < Main.NODES; k++) {
      for (int i = 0; i < Main.NODES; i++) {
        for (int j = 0; j < Main.NODES; j++) {
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

  public static void dfs(int start, int counter) {
    int[] d = new int[NODES];
    Arrays.fill(d, -1);
    int[] stack = new int[NODES * 10];
    int head = 0;
    d[start] = 0;
    stack[head++] = start;
    while (head != 0) {
      int v = stack[head--];
      citisArea[v] = counter;
      areasCity[counter] = v;
      for (int i = 0; i < NODES; i++) {
        if ((graph[i][v] == 0) && (graph[v][i] == 0) && (d[i] == -1)) {
          d[i] = d[v] + 1;
          stack[head++] = i;
          citisArea[v] = counter;
          areasCity[counter] = v;
        }
      }
    }
  }

  private static int unmarked() {
    for (int i = 0; i < NODES; i++) {
      if (citisArea[i] == -1) return i + 1;
    }
    return -1;
  }


  public static void buildAreas() {
    citisArea = new int[NODES + 1];
    Arrays.fill(citisArea, -1);
    areasCity = new int[NODES + 1];
    int u, counter = 0;
    while ((u = unmarked()) != -1) {
      dfs(u, ++counter);
    }
    areaGraph = new long[counter][counter];
    for (int i = 0; i < counter; i++) {
      for (int j = 0; j < counter; j++) {
        areaGraph[i][j] = graph[areasCity[i]][areasCity[j]];
      }
    }
    areaGraph = floyd(areaGraph, counter);
  }

  public static long getDistance(int from, int to) {
    return areaGraph[citisArea[from]][citisArea[to]];
  }

  private static void solve() throws IOException {
    NODES = rInt();
    int n = rInt();
    graph = new long[NODES][NODES];
    for (int i = 0; i < NODES; i++) {
      Arrays.fill(graph[i], INF);
    }
    for (int i = 0; i < n; i++) {
      int a = rInt() - 1;
      int b = rInt() - 1;
      graph[a][b] = 0;
      if (graph[b][a] != 0) {
        graph[b][a] = 1;
      }
    }
//    long[][] distance = floyd(graph, NODES);
    buildAreas();
    n = rInt();
    for (int i = 0; i < n; i++) {
      int a = rInt() - 1;
      int b = rInt() - 1;
      if (a == b) {
        out.println(0);
      } else
        out.println(getDistance(a, b) == INF ? -1 : getDistance(a, b));
    }
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
    st.nextToken();
    return (long) st.nval;
  }

  static double rDouble() throws IOException {
    return Double.parseDouble(rNext());
  }
}
