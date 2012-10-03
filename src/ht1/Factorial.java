package ht1;

/**
 * User: Roman
 * Date: 01.10.12
 */


public class Factorial {

  public static final int N = 20, N_MAX = 40;

  public static long factorialLoop(int n) {
    long res = 1;
    for (long i = 1; i <= n; i++) {
      res *= i;
    }
    return res;
  }

  public static long factorialRec(int n) {
    if (n < 2) return 1;
    return factorialRec(n - 1) * n;
  }

  public static long[] factorials = new long[N_MAX];

  public static long factorialDyn(int n) {
    if (n < 2) return 1;
    if (factorials[n] == 0) factorials[n] = factorialDyn(n - 1) * n;
    return factorials[n];
  }

  public static void main(String[] args) {
    long factorial;
    long startTime = System.nanoTime(), time;
    factorial = factorialRec(N);
    time = System.nanoTime() - startTime;
    System.out.println(factorial);
    System.out.println("Time: " + time + " ns");
  }
}
