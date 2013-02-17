package olymp.MIPT.M;

import java.io.*;

/**
 * User: Roman
 * Date: 11.01.13
 */
public class Main {
  public static class Figure {

    public static int x, y;
    public static String type;

    Figure(int a, int b, String s) {
      x = a;
      y = b;
      type = s;
    }

    public void setDXY(int dx, int dy) {
      x += dx;
      y += dy;
    }

    public void walk(int wx, int wy) {
      x += wx;
      y += wy;
      System.out.println(type + " " + wx + " " + wy);
    }

    public void walkTo(int wx, int wy) {
      walk(wx - x, wy - y);
    }

    public boolean isAttackedBy(Figure a) {
      if (a.type.toCharArray()[0] == 'K') {
        return ((Math.abs(x - a.x) <= 1) && (Math.abs(x - a.y) <= 1));
      } else if (a.type.toCharArray()[0] == 'R') {
        return ((a.x == x) || (a.y == y));  //TODO: rewrite with jumps;
      }
      return false;
    }

  }

  public static Figure bk;
  public static Figure r1;
  public static Figure r2;
  public static Figure k;

  public static void buildTunnel() {

  }

  public static void outFromAttack() {

  }

//  public static char updTunnelDir(){
//
//  }

  private static void solve() throws IOException {
    bk = new Figure(rInt(), rInt(), "KB");
    r1 = new Figure(rInt(), rInt(), "R1");
    r2 = new Figure(rInt(), rInt(), "R2");
    k = new Figure(rInt(), rInt(), "K");
//    char tunnelDir = updTunnelDir();
    outFromAttack();
    buildTunnel();
//    mat();
    if (r1.isAttackedBy(bk)) {
      if (!((r1.isAttackedBy(r2)) || (r1.isAttackedBy(k)))) {

      }
    }


  }

  public static void main(String[] args) throws IOException {


    BufferedReader br;
//    try {
//      final String fileName = "";
//      br = new BufferedReader(new FileReader(fileName + ".in"));
//      out = new PrintWriter(new FileWriter(fileName + ".out"));
//    } catch (Exception e) {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
//    }
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
