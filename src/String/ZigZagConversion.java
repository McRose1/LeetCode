package String;

/*  6. Zigzag Conversion
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
    (you may want to display this pattern in a fixed font for better legibility)
    P   A   H   N
    A P L S I I G
    Y   I   R
    And then read line by line: "PAHNAPLSIIGYIR"
    Write the code that will take a string and make this conversion given a number of rows:
    string convert(string s, int numRows);

    Example 1:
    Input: s = "PAYPALISHIRING", numRows = 3
    Output: "PAHNAPLSIIGYIR"

    Example 2:
    Input: s = "PAYPALISHIRING", numRows = 4
    Output: "PINALSIGYAHRPI"
 */

/*  Visit by Row: Time = O(n) Space = O(n)
    Visit the characters in the same order as reading the Zig-Zag pattern line by line
    Visit all characters in row 0 first, then row 1, then row 2, and so on...
    For all whole numbers k,
    Characters in row 0 are located at indexes k(2*numRows - 2)
    Characters in row numRows - 1 are located at indexes k(2*numRows - 2) + numRows - 1
    Characters in inner row i are located at indexes k(2*numRows - 2) + i and (k + 1)(2*numRows - 2) - i
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;     // row = 3, cycleLen = 4; row = 4, cycleLen = 6

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) { // 每次加一个周期
                sb.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {   // 除了第一行和最后一行中间的构成斜线的元素
                    sb.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return sb.toString();
    }
}

/*  Sort by Row: Time = O(n) Space = O(n)
    By iterating through the string from left to right, we can easily determine which row in the Zig-Zag
    pattern that a character belongs to

        if (numRows == 1) return s;

        List<stringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 遍历到两端
            if (curRow == 0 || curRow == numRow - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
 */