package olymp;

/**
 * User: Roman
 * Date: 09.12.12
 */
public class Task7 {
  public static boolean fun(boolean a, boolean b, boolean c) {
    return (a == c) == (b == c);
//    return !(a && b) || c;
//    return !c || (a && b);
//    return (a && b) == (a && c);
//    return !(a || b && c);
  }

  public static void main(String[] args) {
    boolean a = false;
    boolean b = false;
    boolean c = false;
    int n = 0;
    n += fun(a, b, c) ? 0 : 1;
    n += fun(a, !b, c) ? 0 : 1;
    n += fun(a, b, !c) ? 0 : 1;
    n += fun(a, !b, !c) ? 0 : 1;
    n += fun(!a, b, c) ? 0 : 1;
    n += fun(!a, !b, c) ? 0 : 1;
    n += fun(!a, b, !c) ? 0 : 1;
    n += fun(!a, !b, !c) ? 0 : 1;
    System.out.println(n);
  }
}
