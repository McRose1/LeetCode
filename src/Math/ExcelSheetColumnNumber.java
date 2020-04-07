package Math;

/*  171. Excel Sheet Column Number
    Given a column title as appear in an Excel sheet,
    return its corresponding column number.
    For example:
        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...
 */
//  26 进制 -> 10 进制
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {  // "AB"
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // 0 * 26 + 1 = 1; 1 * 26 + 2 = 28
            res = res * 26 + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }
}

/*  my version（从右往左）

        int res = 0;
        int digit = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            // 'B'
            res += (s.charAt(i) - 'A' + 1) * digit;
            digit *= 26;
        }
        return res;
 */
