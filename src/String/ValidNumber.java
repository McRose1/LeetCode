package String;

/*  65. Valid Number
    Validate if a given string can be interpreted as a decimal number.

    Some examples:
    "0" => true
    " 0.1 " => true
    "abc" => false
    "1 a" => false
    "2e10" => true
    " -90e3   " => true
    " 1e" => false
    "e3" => false
    " 6e-1" => true
    " 99e2.5 " => false
    "53.5e93" => true
    " --6 " => false
    "-+3" => false
    "95a54e53" => false

    Note: It is intended for the problem statement to be ambiguous.
    You should gather all requirements up front before implementing one.
    However, here is a list of characters that can be in a valid decimal number:
    Numbers 0-9
    Exponent - "e"
    Positive/negative sign - "+"/"-"
    Decimal point - "."
    Of course, the context of these characters also matters in the input.
 */
//  +1234.567e-89   按顺序往后判断
public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;

        s = s.trim();
        int i = 0;
        int n = s.length();

        // check +/- sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        // check digits until
        boolean isDigits = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isDigits = true;
        }
        // check post . parts
        if (i < n && s.charAt(i) == '.') {
            i++;
            // 这里不能将 isDigits 设为 false，因为在这题 3. 也是 valid number
            // '.' 不在末尾的话后面跟的必须是数字
            while (i < n && Character.isDigit(s.charAt(i))) {
                isDigits = true;
                i++;
            }
        }
        // e 前面需要有数字
        if (i < n && s.charAt(i) == 'e' && isDigits) {
            i++;
            isDigits = false;
            // check +/- sign
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isDigits = true;
            }
        }
        return isDigits && i == s.length();
    }
}

/*
        s = s.trim();

        boolean eSeen = false;
        boolean numSeen = false;
        boolean pointSeen = false;
        boolean numAfterE = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                numSeen = true;
                numAfterE = true;
            } else if (c == 'e') {
                // 每个 valid number 至多一个 e，不能凭空出现，需要前面是数字
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
                numAfterE = false;
            } else if (c == '+' || c == '-') {
                // 只能可以出现在第一位或者e后面
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else if (c == '.') {
                // 只能出现在e前面一次
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else {
                return false;
            }
        }
        return numAfterE && numSeen;
 */
