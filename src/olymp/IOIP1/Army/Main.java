package olymp.IOIP1.Army;

import java.io.*;
import java.util.StringTokenizer;


/**
 * @author Shchedrin Roman
 * @date 18.01.2013
 */
public class Main {
  public static int nWrong;
  public static int[] arr;

  public static void change(int n, int x) {
    int pWrong = 0;
    int aWrong = 0;
    if (n > 0) {
      if (arr[n - 1] > arr[n]) nWrong--;
      if (arr[n - 1] > x) nWrong++;
    }
    if (n + 1 < arr.length) {
      if (arr[n + 1] < arr[n]) nWrong--;
      if (arr[n + 1] < x) nWrong++;
    }
    arr[n] = x;
  }

  public static void ansIsOk() {
    if (nWrong == 0) {
      out.println("YES");
    } else {
      out.println("NO");
    }
  }

  private static void solve() throws IOException {
    int arrSize = rInt();
    int commands = rInt();
    arr = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      arr[i] = rInt();
      if (i > 0) {
        if (arr[i] < arr[i - 1]) {
          nWrong++;
        }
      }
    }
    for (int i = 0; i < commands; i++) {
      String cmd = rNext();
      if (cmd.charAt(0) == '!') {
        change(rInt() - 1, rInt());
      } else {
        ansIsOk();
      }
    }

  }

  public static BufferedReader br;
  private static StringTokenizer st;
  private static PrintWriter out;

  public static void main(String[] args) throws IOException {
    try {
      final String fileName = "army";
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