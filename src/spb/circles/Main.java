/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spb.circles;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author spb
 */
public class Main {
  public static class Point {
    public double x, y;

    public Point(double sx, double sy) {
      x = sx;
      y = sy;
    }
  }

  public static final int G_SIZE = 5000;

  public static double len(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }

  public static double pLen(Point p1, Point p2) {
    return len(p1.x, p1.y, p2.x, p2.y);
  }

  public static double radius(Point p1, Point p2, Point p3) {
    double a = pLen(p1, p2);
    double b = pLen(p2, p3);
    double c = pLen(p1, p3);
    double hp = (a + b + c) / 2;
    double s = Math.sqrt(hp * (hp - a) * (hp - b) * (hp - c));
    double sinc = s / (a * b);
    return c / sinc;
  }


  private static void solve() throws IOException {
    Point[] stones = new Point[rInt()];
    for (int i = 0; i < stones.length; i++) {
      stones[i] = new Point(rInt(), rInt());
    }
    boolean noGroup = true;
    boolean inGroup = true;
    int groups[][] = new int[G_SIZE][G_SIZE];
    double groupRad[] = new double[G_SIZE];
    int groupSize[] = new int[G_SIZE];
    int gsize = 1;
    groups[0][0] = 0;
    groups[0][1] = 1;
    groups[0][2] = 2;
    groupRad[0] = radius(stones[0], stones[1], stones[2]);
    groupSize[0] = 3;
    for (int i = 0; i < stones.length; i++) {
      noGroup = true;
      for (int g = 0; g < gsize; g++) {
        if (radius(stones[groups[g][0]], stones[groups[g][1]], stones[i]) == groupRad[g]) {
          inGroup = true;
          for (int m = 1; m < groupSize[g]; m++) {
            if (radius(
                stones[groups[g][m]],
                stones[groups[g][m - 1]],
                stones[i]
            ) != groupRad[g]) {
              inGroup = false;
            }
            if (radius(
                stones[groups[g][0]],
                stones[groups[g][groupSize[g] - 1]],
                stones[i]
            ) != groupRad[g]) {
              inGroup = false;
            }
          }
          if (inGroup) {
            groups[g][groupSize[g]++] = i;
            noGroup = false;
          }
        }
      }
      if (noGroup) {
        for (int j = 0; j < stones.length; j++) {
          for (int k = j + 1; k < stones.length; k++) {
            boolean created = false;
            for (int p = 0; p < gsize; p++) {
              if (groupSize[p] >= 3) {
                int[] tmp = Arrays.copyOf(groups[p], gsize);
                Arrays.sort(tmp);
                int a, b, c;
                a = Math.min(i, Math.min(j, k));
                c = Math.max(Math.max(i, j), k);
                b = i + j + k - a - c;
                if ((tmp[0] == a) && (tmp[1] == b) && (tmp[2] == c)) {
                  created = true;
                }
              }
            }
            if (!created) {
              int g = gsize++;
              groups[g][0] = i;
              groups[g][1] = j;
              groups[g][2] = k;
              groupRad[g] = radius(stones[i], stones[j], stones[k]);
              groupSize[g] = 3;
            }
//                        created = false;
          }
        }
      }
    }
    int max1 = 0, maxi1 = 0, max2 = 0, maxi2 = 0;
    for (int i = 0; i < gsize; i++) {
      if (groupSize[i] >= max1) {
        max2 = max1;
        maxi2 = maxi1;
        max1 = groupSize[i];
        maxi1 = i;
      }
    }
    for (int i = 0; i < groupSize[maxi1]; i++) {
      out.print(groups[maxi1][i] + " ");
    }
    out.println();
    for (int i = 0; i < groupSize[maxi2]; i++) {
      out.print(groups[maxi2][i] + " ");
    }
  }

  public static BufferedReader br;
  public static StringTokenizer st;
  public static PrintWriter out;

  public static void main(String[] args) throws IOException {
    final String filename = "";
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
