package task1;

import java.util.Arrays;
import java.util.Random;

/**
 * User: Roman
 * Date: 01.10.12
 */

public class InsertionSort {
  public static final int M_SIZE = 10, MAX_RAND = 10;
  public static long[] arr = new long[M_SIZE];
  public static long[] arr2 = new long[M_SIZE];

  public static void mShift(long[] m, int from, int to) {

    System.arraycopy(m, from + 1, m, from + 1 + 1, to - 1 - from);
  }

  public static int binSearch(long[] m, long val, int from, int to) {
    int mid = (from + to) / 2;
    while (to - from > 1) {
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
    for (int i = 0; i < m.length - 1; i++) {
      long last;
      last = m[i + 1];
      for (int i1 = i; i1 >= 0; i1--) {
        if (last < m[i1]) {
          m[i1 + 1] = m[i1];
          m[i1] = last;
        } else break;
      }
    }
  }

  public static void insertionBinSort(long[] m) {
    for (int i = 0; i < M_SIZE - 1; i++) {
      int key;
      long last = m[i + 1];
      if (last < m[i]) {
        key = binSearch(m, m[i + 1], 0, i);
        mShift(m, key, i + 1);
        m[key] = last;
      }
    }
  }

  public static void main(String[] args) {
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(MAX_RAND + 1);
    }
    arr2 = arr.clone();
    System.out.println(Arrays.toString(arr));
    insertionBinSort(arr);
    insertionSort(arr2);
    System.out.println(Arrays.toString(arr));
    if (arr.equals(arr2)) System.out.println("Right!");
    else System.out.println("Wrong:\n" + Arrays.toString(arr) + "\n" + Arrays.toString(arr2));
  }
}
