package by.babanin.genesis.gui.util;

public class AppUtils {

    public static boolean isInteger(String s) throws NumberFormatException {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) throws NumberFormatException {
        if(s == null) {
            return false;
        }

        if(radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }

        if(radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;

        if(len > 0) {
            char firstChar = s.charAt(0);
            if(firstChar < '0') { // Possible leading "+" or "-"
                if(firstChar == '-') {
                    limit = Integer.MIN_VALUE;
                }
                else if(firstChar != '+') {
                    return false;
                }

                if(len == 1) { // Cannot have lone "+" or "-"
                    return false;
                }
                i++;
            }
            int multmin = limit / radix;
            int result = 0;
            while(i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int digit = Character.digit(s.charAt(i++), radix);
                if(digit < 0 || result < multmin) {
                    return false;
                }
                result *= radix;
                if(result < limit + digit) {
                    return false;
                }
                result -= digit;
            }
            return true;
        }
        else {
            return false;
        }
    }
}
