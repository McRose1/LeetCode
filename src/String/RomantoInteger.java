package String;

/*  13. Roman to Integer
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    For example, two is written as II in Roman numeral, just two one's added together.
    Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

    Roman numerals are usually written largest to smallest from left to right.
    However, the numeral for four is not IIII. Instead, the number four is written as IV.
    Because the one is before the five we subtract it making four.
    The same principle applies to the number nine, which is written as IX.

    There are six instances where subtraction is used:
    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given an integer, convert it to a roman numeral.
    Input is guaranteed to be within the range from 1 to 3999.

    Example 1:
    Input: "III"
    Output: 3

    Example 2:
    Input: "IV"
    Output: 4

    Example 3:
    Input: "IX"
    Output: 9

    Example 4:
    Input: "LVIII"
    Output: 58

    Example 5:
    Input: "MCMXCIV"
    Output: 1994
 */
/*  Time = O(n) Space = O(1)
    "ab" pattern: b is larger than a
    The value changes from a + b to b - a
    Sums all symbols up, when encounter "ab" pattern, minus 2*a (4 -> IV, 6 -> VI)
 */
public class RomantoInteger {
    public int romanToInt(String s) {
        int res = 0;
        // 从右往左
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':                               // 1
                    res += (res >= 5 ? -1 : 1);         // 4, 6
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':                               // 10
                    res += 10 * (res >= 50 ? -1 : 1);   // 40, 60
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':                               // 100
                    res += 100 * (res >= 500 ? -1 : 1); // 400, 600
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }
}

/*  从左往右

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') res+= 1;
            if (s.charAt(i) == 'V') res+= 5;
            if (s.charAt(i) == 'X') res+= 10;
            if (s.charAt(i) == 'L') res+= 50;
            if (s.charAt(i) == 'C') res+= 100;
            if (s.charAt(i) == 'D') res+= 500;
            if (s.charAt(i) == 'M') res+= 1000;
        }
        // 6 subtraction
        if (s.indexOf("IV") != -1) res-= 2;
        if (s.indexOf("IX") != -1) res-= 2;
        if (s.indexOf("XL") != -1) res-= 20;
        if (s.indexOf("XC") != -1) res-= 20;
        if (s.indexOf("CD") != -1) res-= 200;
        if (s.indexOf("CM") != -1) res-= 200;

        return res;
 */

