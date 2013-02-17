/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spb;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author spb
 */
public class Capitals {


  public static long INF;
  public static int NODES;
//    public static long zA[];
//    public static long fA[];

//    public static long z(int a){
//        if(zA[a] != 0 ){
//            return zA[a];
//        }
//        else{
//            if(a == 2)return 1;
//            if(a < 2)return 0;
//            zA[a] = z(a-1) + a - 1;
//            return zA[a];
//        }
//    }
//
//    public static long f(int a){
//        if(fA[a] != 0 ){
//            return fA[a];
//        }
//        else{
//            if(a == 3)return 1;
//            if(a < 3)return 0;
//            fA[a] = f(a-1) + z(a - 1);
//            return fA[a];
//        }
//    }

  public static long[][] floyd(long[][] graph) {
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

  public static long triangles(long[][] graph) {
    int res = 0;
    for (int i = 0; i < NODES; i++) {
      for (int j = i; j < NODES; j++) {
        for (int k = j; k < NODES; k++) {
          if ((graph[i][j] == 1) && (graph[i][k] == 1) && (graph[j][k] == 1)) res++;
        }
      }
    }
    return res;
  }

  private static void solve() throws IOException {
    NODES = rInt();
//        int edges = rInt();//////////////////////////////////////////////////
    int d = rInt();

//        zA = new long[NODES];
//        fA = new long[NODES];
    long[][] graph = new long[NODES][NODES];
    for (int i = 0; i < NODES; i++) {
      Arrays.fill(graph[i], INF);
    }

    for (int i = 0; i < NODES - 1; i++) {//////////////////////////////////////////////////
      int a = rInt() - 1;
      int b = rInt() - 1;
      graph[a][b] = 1;
      graph[b][a] = 1;
    }////////////////////////////////////////////////////////////////////////////////////////////////////
    long[][] distance = floyd(graph);
    for (int i = 0; i < NODES; i++) {
      for (int j = 0; j < NODES; j++) {
        if (distance[i][j] == d) {
          graph[i][j] = 1;
        } else {
          graph[i][j] = 0;
        }
      }
    }
    out.println(triangles(graph));
  }

  public static BufferedReader br;
  public static StringTokenizer st;
  public static PrintWriter out;

  public static void main(String[] args) throws IOException {
    INF = Long.MAX_VALUE / 2;
    final String filename = "capitals";
    try {
      br = new BufferedReader(new FileReader(filename + ".in"));
      out = new PrintWriter(new FileWriter(filename + ".out"));
    } catch (Exception e) {
      br = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new OutputStreamWriter(System.out));
    }
    st = new StringTokenizer(br.readLine());
    solve();
    br.close();
    out.close();
  }

  private static String rNext() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      st = new StringTokenizer(br.readLine());
    }
    return st.nextToken();
  }

  private static int rInt() throws IOException {
    return Integer.parseInt(rNext());
  }
}
