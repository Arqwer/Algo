package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class SpanningTree {
  /**
   * User: Roman
   * Date: 02.12.12
   */
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

  public static ArrayList<Edge>[] edges;
  public static ArrayList<Edge> allEdges;
  public static int NODES;

  public static ArrayList<Edge> prima() {
    int nAdded = 1;
    boolean addedNodes[] = new boolean[NODES];
    addedNodes[0] = true;
    ArrayList<Edge> res = new ArrayList<Edge>();
    while (nAdded < NODES) {
      Edge minEdge = new Edge(0, 0, Long.MAX_VALUE);
      for (int i = 0; i < NODES; i++) {
        if (addedNodes[i]) {
          for (Edge edge : edges[i]) {
            if (edge.weight < minEdge.weight && !addedNodes[edge.to]) {
              minEdge = edge;
            }
          }
        }
      }
      res.add(minEdge);
      addedNodes[minEdge.to] = true;
      nAdded++;
    }
    return res;
  }

  public static ArrayList<Edge> kruskal() {
    Collections.sort(allEdges, new Comparator<Edge>() {
      @Override
      public int compare(Edge o1, Edge o2) {
        return (int) (o1.weight - o2.weight);
      }
    });
    int index = 0;
    int[] ids = new int[NODES];
    int curMaxId = 1;
    ArrayList<Edge> res = new ArrayList<Edge>();
    while (res.size() < NODES - 1) {
      final Edge edge = allEdges.get(index++);

      if (ids[edge.from] == 0) {
        if (ids[edge.to] == 0) {
          res.add(edge);
          ids[edge.from] = curMaxId;
          ids[edge.to] = curMaxId;
          curMaxId++;
        } else {
          res.add(edge);
          ids[edge.from] = ids[edge.to];
        }
      } else {
        if (ids[edge.to] == 0) {
          res.add(edge);
          ids[edge.to] = ids[edge.from];
        } else {
          if (ids[edge.from] == ids[edge.to]) {
            continue;
          } else {
            res.add(edge);
            for (int j = 0; j < NODES; j++) {
              if (ids[j] == ids[edge.to]) {
                ids[j] = ids[edge.from];
              }
            }
          }
        }
      }
    }
    return res;
  }

  private static void solve() throws IOException {
    NODES = rInt();
    int numEdges = rInt();
    edges = new ArrayList[NODES];
    allEdges = new ArrayList<Edge>();
    for (int i = 0; i < NODES; i++) {
      edges[i] = new ArrayList<Edge>();
    }
    for (int i = 0; i < numEdges; i++) {
      int a = rInt();
      int b = rInt();
      int w = rInt();
      edges[a].add(new Edge(a, b, w));
      edges[b].add(new Edge(b, a, w));
      allEdges.add(new Edge(a, b, w));
    }
    final ArrayList<Edge> prima = kruskal();

    long sum = 0;
    for (Edge edge : prima) {
      sum += edge.weight;
    }
    out.print(sum);
    System.out.println(sum);
  }

  public static void main(String[] args) throws IOException {
    Locale.setDefault(Locale.US);

    BufferedReader br;
    try {
      final String fileName = "span";
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
