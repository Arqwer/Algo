//package olymp.MIPT.H;

import java.io.*;
import java.util.Arrays;

/**
 * User: Roman
 * Date: 07.01.13
 */
public class Main {

  public static int[][] distance;
  public static int[][] graph;
  public static int NODES;
  public static final long INF = Long.MAX_VALUE;


  public static void floyd() {
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
//    return distance;
  }

  public static class Event implements Comparable<Event> {
    int city, start, end;

    public Event(int a, int b, int c) {
      city = a;
      start = b;
      end = c;
    }


    @Override
    public int compareTo(Event o) {
      return this.end - o.end;
    }
  }

  public static boolean reachable(Event first, Event second) {
    return (first.end + distance[first.city][second.city] <= second.start);
  }

  private static void solve() throws IOException {
    int days = rInt();
    int cities = rInt();
    int nEvents = rInt();
    Event[] events = new Event[nEvents];
    distance = new int[cities][cities];
    graph = new int[cities][cities];
    boolean[] excluded = new boolean[nEvents];
    for (int i = 0; i < cities; i++) {
      for (int j = 0; j < cities; j++) {
        graph[i][j] = rInt();
      }
    }
    NODES = cities;
    for (int i = 0; i < NODES; i++) {
      for (int j = 0; j < NODES; j++) {
        distance[i][j] = graph[i][j];
      }
    }
    floyd();   //TODO: remove if time limit
    for (int i = 0; i < nEvents; i++) {
      events[i] = new Event(rInt() - 1, rInt(), rInt());
    }
    Arrays.sort(events);
    for (int i = 0; i < nEvents; i++) {
      if (events[i].end + distance[events[i].city][0] > days) {
        excluded[i] = true;
      }
    }
    int[] counter = new int[nEvents];
    for (int i = 0; i < nEvents; i++) {
      if (reachable(new Event(0, 0, 1), events[i])) {
        counter[i] = 1;
      }
    }

    for (int i = 0; i < nEvents; i++) {
      for (int j = 0; j < i; j++) {
        if ((counter[i] < counter[j] + 1) && (counter[j] != 0) && (!excluded[i])) {
          if (reachable(events[j], events[i])) {
            counter[i] = counter[j] + 1;
          }
        }
      }
    }
    int max = 0;
    for (int i = 0; i < nEvents; i++) {
      if ((counter[i] > max) && (!excluded[i])) {
        max = counter[i];
      }
    }
    System.out.println(max);
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
