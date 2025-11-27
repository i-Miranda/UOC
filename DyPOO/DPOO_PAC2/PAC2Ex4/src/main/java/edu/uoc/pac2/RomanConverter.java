package edu.uoc.pac2;

public class RomanConverter {
    private static final int[] VALUES   = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public static int romanCharToInt(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    public static int romanToDecimal(String roman) throws IllegalArgumentException {
        if (roman == null || roman.isBlank())
            throw new IllegalArgumentException("Invalid Roman numeral format.");

        int total = 0;
        int i = 0;
        while (i < roman.length()) {
            int current = romanCharToInt(roman.charAt(i));
            if (current < 0)
                throw new IllegalArgumentException("Invalid Roman numeral format.");

            if (i + 1 < roman.length()) {
                int next = romanCharToInt(roman.charAt(i + 1));
                if (next < 0)
                    throw new IllegalArgumentException("Invalid Roman numeral format.");

                if (current < next) {
                    String pair = "" + roman.charAt(i) + roman.charAt(i + 1);
                    if (!pair.equals("IV") && !pair.equals("IX") && !pair.equals("XL")
                            && !pair.equals("XC") && !pair.equals("CD") && !pair.equals("CM"))
                        throw new IllegalArgumentException("Invalid Roman numeral format.");
                    total += (next - current);
                    i += 2;
                    continue;
                }
            }
            total += current;
            i++;
        }

        if (!decimalToRoman(total).equals(roman))
            throw new IllegalArgumentException("Invalid Roman numeral format.");

        return total;
    }

    public static String decimalToRoman(int number) throws IllegalArgumentException {
        if (number < 1 || number > 3999)
            throw new IllegalArgumentException("Number must be between 1 and 3999.");

        StringBuilder sb = new StringBuilder();
        int n = number;

        for (int i = 0; i < VALUES.length && n > 0; i++) {
            while (n >= VALUES[i]) {
                sb.append(SYMBOLS[i]);
                n -= VALUES[i];
            }
        }
        return sb.toString();
    }
}
