package task1;

import java.util.Arrays;
import java.util.Random;

/**
 * User: Roman
 * Date: 14.10.12
 */

public class QuickSort {

  public static final int M_SIZE = 10;
  public static final int MAX_RAND = 20;

  public static void insertionSort(long[] m, int low, int high) {
    if (low < high) {
      for (int i = low; i < high; i++) {
        long last;
        last = m[i + 1];
        for (int j = i; j >= low; j--) {
          if (last < m[j]) {
            m[j + 1] = m[j];
            m[j] = last;
          } else break;
        }
      }
    }
  }

  public static void quickSort(long[] arr, int low, int high) {
    if (high - low > 4) {
      int i = low;
      int j = high;
      long bar = arr[low];
      do {
        while (arr[i] < bar) {
          i++;
        }
        while (arr[j] > bar) {
          j--;
        }
        if (i <= j) {
          long temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          i++;
          j--;
        }
      } while (i <= j);
      quickSort(arr, low, j);
      quickSort(arr, i, high);
    } else insertionSort(arr, low, high);
  }


  public static void main(String[] args) {
    long[] arr = new long[M_SIZE];
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(MAX_RAND + 1);
    }
    System.out.println("In: " + Arrays.toString(arr));
    quickSort(arr, 0, arr.length - 1);
    System.out.println("Out:" + Arrays.toString(arr));
  }
}
