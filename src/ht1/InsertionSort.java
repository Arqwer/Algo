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

  public static void straitIns(long[] m, int sorted) {
    long tmp, last;
    last = m[sorted + 1];
    for (int i = sorted; i >= 0; i--) {
      if (last < m[i]) {
        m[i + 1] = m[i];
        m[i] = last;
      } else break;
    }
  }

  public static void straitBinIns(long[] m, int sorted) {
    System.out.println("SBI entered");                 //mbd
    System.out.println("   m:" + Arrays.toString(m) + "s:" + sorted);
    int k;
    long last;
    last = m[sorted + 1];
    if (last < m[sorted]) {
      k = binSearch(m, m[sorted + 1], 0, sorted);
      mShift(m, k);
      m[k] = last;
    }
    System.out.println("SBI closed");    //mbd
  }

  public static void mShift(long[] m, int from) {
    long tmp;
    for (int i = from; i < m.length - 1; i++) {
      tmp = m[i + 1];
      m[i + 1] = m[i];
      m[i] = tmp;
    }
  }

  public static int binSearch(long[] m, long val, int from, int to) {
    int mid;
    for (mid = (from + to) / 2; (from < to) && (m[mid] != val); ) {
      mid = (from + to) / 2;
      if (m[mid] < val) {
        from = mid;
      } else to = mid;

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
    System.out.println("k" + binSearch(mass, 6, 0, 9));
    //insertionBinSort(mass);
    System.out.println(Arrays.toString(mass));
  }
}
