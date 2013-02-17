package task2;

/**
 * User: Roman
 * Date: 30.09.12
 */
public class Fib {
  public static final int N = 40;
  public static final int MAX_N = 1000;

  public static long fib(int n) {
    if (n < 3) return 1;
    return fib(n - 1) + fib(n - 2);
  }

  public static long[] fibs = new long[MAX_N];

  public static long fib2(int n) {
    if (fibs[n] != 0) {
      return fibs[n];
    }
    if (n < 3) {
      return 1;
    }
    long a = fibs[n - 1] != 0 ? fibs[n - 1] : fib2(n - 1);
    fibs[n] = a + fibs[n - 2];
    return fibs[n];
  }

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    long fib = fib2(N);
    long time = System.currentTimeMillis() - startTime;
    System.out.println(fib);
    System.out.println(time);


  }
}
