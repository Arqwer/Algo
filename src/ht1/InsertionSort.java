package ht1;

import java.util.Arrays;
import java.util.Random;

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

  public static void insertionSort(long[] m) {
    for (int i = 0; i < M_SIZE; i++) {
      straitIns(m, i);
    }
  }

  public static void main(String[] args) {
    Random random = new Random();
    for (int i = 0; i < M_SIZE; i++) {
      mass[i] = random.nextInt();
    }
    insertionSort(mass);
    System.out.println(Arrays.toString(mass));
  }
}
