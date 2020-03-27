public class FractionSimplifier {

    public static String simplify(String fraction) {
        if (fraction == null || fraction.equals("")) {
            return fraction;
        }

        int numerator = Integer.parseInt(fraction.substring(0, fraction.indexOf('/')));
        int denominator = Integer.parseInt(fraction.substring(fraction.indexOf('/') + 1));

        int bigger = Math.max(numerator, denominator);
        for (int i = bigger / 2; i >= 2; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
            }
        }
        return denominator == 1 ? String.valueOf(numerator) : numerator + "/" + denominator;
    }


    public static void main(String[] args) {

    }
}
