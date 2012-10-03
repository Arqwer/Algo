package ht1;

import java.util.Arrays;
import java.util.Random;

/**
 * User: Roman
 * Date: 03.10.12
 */
public class BubbleSort {
  public static final int M_SIZE = 10, MAX_RAND = 10;

  public static void bubbleSort(long[] arr) {
    long tmp;
    boolean changed;
    for (int j = 0; j < arr.length; j++) {
      changed = false;
      for (int i = 1; i < arr.length - j; i++) {
        if (arr[i] < arr[i - 1]) {
          tmp = arr[i];
          arr[i] = arr[i - 1];
          arr[i - 1] = tmp;
          changed = true;
        }
      }
      if (!changed) {
        break;
      }
    }
  }

  public static void main(String[] args) {
    long[] arr = new long[M_SIZE];
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(MAX_RAND + 1);
    }
    System.out.println("In: " + Arrays.toString(arr));
    bubbleSort(arr);
    System.out.println("Out:" + Arrays.toString(arr));
  }

}


