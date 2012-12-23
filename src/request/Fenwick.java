package request;

import java.util.Arrays;

/**
 * User: Roman
 * Date: 23.12.12
 */
public class Fenwick {
  public int[] t;

  public Fenwick(int[] a) {
    t = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      inc(i, a[i]);
    }
  }

  private void inc(int i, int delta) {
    for (; i < t.length; i |= i + 1) {
      t[i] += delta;
    }
  }

  private int sum(int n) {
    int res = 0;
    for (; n >= 0; n = (n & (n + 1)) - 1) {
      res += t[n];
    }
    return res;
  }

  public int sum(int l, int r) {
    return l > 0 ? sum(r) - sum(l - 1) : sum(r);
  }

  public static void main(String[] args) {
    final int[] a = {1, 2, 3, 5, 4, 6};
    System.out.println(Arrays.toString(a));
    final Fenwick fenwick = new Fenwick(a);
    System.out.println(fenwick.sum(1, 1));
    System.out.println(fenwick.sum(1, 5));
    System.out.println(fenwick.sum(3, 5));
    fenwick.inc(1, 5);
    System.out.println(fenwick.sum(1, 3));
  }
}
