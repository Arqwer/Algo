package string;

/**
 * User: Roman
 * Date: 24.01.13
 */
public class Hash {
  public static final long p = 31;

  public static long hash(String s) {
    char[] arr = s.toCharArray();
    long res = 0;
    long mul = 1;
    for (int i = 0; i < arr.length; i++) {
      res += (arr[i] - 'a' + 1) * mul;
      mul *= p;
    }
    return res;
  }

  private static boolean compareSubStrings(String s, int a, int b, int len) {
    buildPrefix(s);
    long h1 = prefixHashes[a + len] - prefixHashes[a];
    long h2 = prefixHashes[b + len] - prefixHashes[b];
    return (b > a && h1 * pows[b - a] == h2) || (b < a && h2 * pows[a - b] == h1);
  }

  public static long pows[];
  public static long prefixHashes[];

  private static void buildPrefix(String s) {
    final int length = s.length();
    prefixHashes = new long[length];
    pows = new long[length + 1];
    char[] arr = s.toCharArray();
    long res = 0;
    pows[0] = 1;
    for (int i = 0; i < arr.length; i++) {
      res += (arr[i] - 'a' + 1) * pows[i];
      prefixHashes[i] = res;
      pows[i + 1] = pows[i] * p;
    }
  }


  private static boolean subStrCmp(String s, int i, int j, int len) {
    char[] arr = s.toCharArray();
    long res = 0;
    long mul = 1;

    return true;
  }

  public static void main(String[] args) {

  }
}
