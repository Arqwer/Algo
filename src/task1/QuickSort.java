package task1;

import java.util.Random;

/**
 * User: Roman
 * Date: 14.10.12
 */

public class QuickSort {

  public static final int M_SIZE = 100;
  public static final int MAX_RAND = 100;
  public static final int MIN_QUICK = 4;
  public static final int N_TESTS = 199999;

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

  private static void swap(long[] arr, int i, int j) {
    long tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private static int partition(long[] arr, int left, int right) {
    int i = left - 1;
    int j = right + 1;
    long bar = arr[(int) (left + right) / 2];
    while (true) {
      do {
        i++;
      } while (arr[i] < bar);
      do {
        j--;
      } while (bar < arr[j]);
      if (i < j) {
        swap(arr, i, j);
      } else break;
    }
    return j;
  }

  public static void trueQuickSort(long[] m, final int left, final int right) {
    int bar;
    if (left < right) {
      bar = partition(m, left, right);
      trueQuickSort(m, left, bar);
      trueQuickSort(m, bar + 1, right);
    }
  }

  public static void quickSort(long[] arr, int low, int high) {
    if (high - low > MIN_QUICK) {
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

  public static void quickSort(long[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  public static void trueQuickSort(long[] arr) {
    trueQuickSort(arr, 0, arr.length - 1);
  }

  public static long[] worstCase(int n) {
    long[] res = new long[n];
    for (int i = 0, j = n / 2; i < n; i++, j++) {
      res[i] = j % (n / 2);
    }
    return res;
  }

  public static long[] worstCase2(int n) {
    long[] res = new long[n];
    for (int i = 0; i < n; i++) {
      res[i] = 1;
    }
    return res;
  }

  public static long[] randCase(int n) {
    long[] arr = new long[n];
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(MAX_RAND + 1);
    }
    return arr;
  }


  public static void main(String[] args) {
    long[] arr = new long[M_SIZE];
    /*Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(MAX_RAND + 1);
    } */
    long startTime, time, avTime, avTime2, avTime3;
    avTime = 0;

    for (int i = 1; i < N_TESTS; i++) {   //разогрев
      arr = worstCase(M_SIZE);
      startTime = System.nanoTime();
      trueQuickSort(arr);
      time = System.nanoTime();
      avTime += ((time - startTime) - avTime) / i;
    }

    avTime = 0;
    avTime2 = 0;
    avTime3 = 0;
    for (int i = 1; i < N_TESTS; i++) {
      arr = worstCase(M_SIZE);
      startTime = System.nanoTime();
      trueQuickSort(arr);
      time = System.nanoTime();
      avTime += ((time - startTime) - avTime) / i;
      arr = worstCase2(M_SIZE);
      startTime = System.nanoTime();
      trueQuickSort(arr);
      time = System.nanoTime();
      avTime2 += ((time - startTime) - avTime2) / i;
      arr = randCase(M_SIZE);
      startTime = System.nanoTime();
      trueQuickSort(arr);
      time = System.nanoTime();
      avTime3 += ((time - startTime) - avTime3) / i;
    }
    System.out.println("case 1: " + avTime + "\ncase 2: " + avTime2 + "\nrandom: " + avTime3);

    /*
 avTime=0;
 arr = worstCase(M_SIZE);
 System.out.println("In: " + Arrays.toString(arr));
 for (int i = 1; i < N_TESTS; i++) {
   arr = worstCase(M_SIZE);
   startTime = System.nanoTime();
   trueQuickSort(arr);
   time=System.nanoTime();
   avTime+=((time-startTime)-avTime)/i;
 }
 System.out.println("Out:" + Arrays.toString(arr)+"\nTime:"+avTime);
 avTime = 0;
 arr = worstCase2(M_SIZE);
 System.out.println("In: " + Arrays.toString(arr));
 for (int i = 1; i < N_TESTS; i++) {
   arr = worstCase2(M_SIZE);
   startTime = System.nanoTime();
   trueQuickSort(arr);
   time=System.nanoTime();
   avTime+=((time-startTime)-avTime)/i;
 }
 System.out.println("Out:" + Arrays.toString(arr)+"\nTime:"+avTime);
 avTime = 0;
 arr = randCase(M_SIZE);
 System.out.println("In: " + Arrays.toString(arr));
 for (int i = 1; i < N_TESTS; i++) {
   arr = randCase(M_SIZE);
   startTime = System.nanoTime();
   trueQuickSort(arr);
   time=System.nanoTime();
   avTime+=((time-startTime)-avTime)/i;
 }
 System.out.println("Out:" + Arrays.toString(arr)+"\nTime:"+avTime); */
  }
}
