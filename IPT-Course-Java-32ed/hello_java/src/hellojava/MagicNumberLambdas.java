package hellojava;

import java.util.stream.IntStream;

public class MagicNumberLambdas {

	public static void main(String[] args) {
		int begin = 10, end = 100;
        IntStream.range(begin, end).mapToObj(i -> i).
            flatMap(i -> IntStream.range(i, end).mapToObj(j -> new int[] { i, j })).
            filter(a -> {
                String s1 = String.valueOf(a[0] * a[1]);
                String s2 = String.valueOf(a[0]) + a[1];
                if (s1.length() != s2.length()) {
                    return false;
                }
                return s1.chars().distinct().
                    allMatch(c -> s1.chars().filter(c1 -> c == c1).count() ==
                                  s2.chars().filter(c1 -> c == c1).count());
            }).
            map(a -> String.valueOf(a[0]) + " * " +a[1]+" = " + (a[0] * a[1])).
            forEach(System.out::println);

	}

}
