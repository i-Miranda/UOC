package edu.uoc.pac2;

public class RomanConverter {

    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static int romanCharToInt(char c) {
        for (int i = 0; i < SYMBOLS.length; i++) {
            if (SYMBOLS[i].length() == 1 && SYMBOLS[i].equals(String.valueOf(c))) {
                return VALUES[i];
            }
        }
        return -1;
    }

    public static String intToRomanChar(int n) {
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i] == n) {
                return SYMBOLS[i];
            }
        }
        return null;
    }

    public static int romanToDecimal(String roman) throws IllegalArgumentException {
        if (roman == null || roman.isBlank()) {
            throw new IllegalArgumentException("Invalid Roman numeral format.");
        }
        StringBuilder romanBuilder = new StringBuilder();
        char current;
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            current = roman.charAt(i);
            if (current == romanBuilder.charAt(romanBuilder.length() - 1)) {
                romanBuilder.append(current);
            }
        }
        if (romanBuilder.isEmpty()) {
            throw new IllegalArgumentException("Invalid Roman numeral format.");
        }
        return result;
    }

    public static String decimalToRoman(int number) throws IllegalArgumentException {
        String result = "";
        int currentValue = 0;
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999.");
        }
        for (int i = 1; number > 0; i *= 10) {
            currentValue = (number % 10) * i;
            result.concat(intToRomanChar(currentValue));
            result += SYMBOLS[currentValue];
        }
        return result;
    }

}
