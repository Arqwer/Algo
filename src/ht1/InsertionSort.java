package ht1;

import java.util.Arrays;

/**
 * User: Roman
 * Date: 01.10.12
 */
public class InsertionSort {
  public static final int M_SIZE = 100;
  public static long[] mass = new long[M_SIZE];

  public static void straitIns(long[] m, int sorted) {
    long tmp, last;
    last = m[sorted + 1];
    for (int i = sorted; i > 0; i--) {
      if (last < m[i]) {
        m[i + 1] = m[i];
        m[i] = last;
      } else break;
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      mass[i] = i;
    }
    mass[10] = 0;
    straitIns(mass, 9);
    System.out.println(Arrays.toString(mass));
  }
}
