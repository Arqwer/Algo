/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spb;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author spb
 */
public class dices {
  public static long tS(long a) {

    return a == 0 ? 0 : 7 - a;
  }

  ;

  public static long minPoss(long n) {
    long res;
    res = n / 6 + tS(n % 6);
    return res;
  }

  private static void solve() throws IOException {
    long n = rInt();
    long max = 6 * n;
    long kMin = (long) Math.ceil((double) n / 6);
//        if(kMin == 1){
//            out.println(tS(n) + " " + n*6);
//            return;
//        }
    out.println(minPoss(n) + " " + n * 6);

  }

  public static BufferedReader br;
  public static StringTokenizer st;
  public static PrintWriter out;

  public static void main(String[] args) throws IOException {
    final String filename = "dices";
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

  private static long rInt() throws IOException {
    return Long.parseLong(rNext());
  }
}
