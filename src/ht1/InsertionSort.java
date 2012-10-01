package ht1;

import java.util.Arrays;
import java.util.Random;

/**
 * User: Roman
 * Date: 01.10.12
 */
public class InsertionSort {
  public static final int M_SIZE = 10, MAX_RAND = 10;
  public static long[] mass = new long[M_SIZE];
  public static long[] mass2 = new long[M_SIZE];

  public static void straitIns(long[] m, int sorted) {
    long last;
    last = m[sorted + 1];
    for (int i = sorted; i >= 0; i--) {
      if (last < m[i]) {
        m[i + 1] = m[i];
        m[i] = last;
      } else break;
    }
  }

  public static void straitBinIns(long[] m, int sorted) {
    int k;
    long last = m[sorted + 1];
    if (last < m[sorted]) {
      k = binSearch(m, m[sorted + 1], 0, sorted);
      mShift(m, k, sorted + 1);
      m[k] = last;
    }
  }

  public static void mShift(long[] m, int from, int to) {

    for (int i = to - 1; i > from; i--) {
      m[i + 1] = m[i];
    }
  }

  public static int binSearch(long[] m, long val, int from, int to) {
    int mid = (from + to) / 2;
    for (; to - from > 1; ) {
      mid = (from + to) / 2;
      if (m[mid] < val) {
        from = mid;
      } else to = mid;
      if (m[mid] == val) {
        break;
      }
    }
    return mid;
  }

  public static void insertionSort(long[] m) {
    for (int i = 0; i < M_SIZE - 1; i++) {
      straitIns(m, i);
    }
  }

  public static void insertionBinSort(long[] m) {
    for (int i = 0; i < M_SIZE - 1; i++) {
      straitBinIns(m, i);
    }
  }

  public static void main(String[] args) {
    Random random = new Random();
    for (int i = 0; i < M_SIZE; i++) {
      mass[i] = random.nextInt(MAX_RAND + 1);
    }
    mass2 = mass.clone();
    System.out.println(Arrays.toString(mass));
    insertionBinSort(mass);
    insertionSort(mass2);
    System.out.println(Arrays.toString(mass));
    if (mass.equals(mass2)) System.out.println("True!");
    else System.out.println("False:\n" + Arrays.toString(mass) + "\n" + Arrays.toString(mass2));
  }
}
