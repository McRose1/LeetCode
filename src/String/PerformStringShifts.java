package String;

/*  Perform String Shifts
    You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:
    o direction can be 0 (for left shift) or 1 (for right shift).
    o amount is the amount by which string s is to be shifted.
    o A left shift by 1 means remove the first character of s and append it to the end.
    o Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
    Return the final string after all operations.

    Example 1:
    Input: s = "abc", shift = [[0,1],[1,2]]
    Output: "cab"
    Explanation:
    [0,1] means shift to left by 1. "abc" -> "bca"
    [1,2] means shift to right by 2. "bca" -> "cab"

    Example 2:
    Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
    Output: "efgabcd"
    Explanation:
    [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
    [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
    [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
    [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"

    Constraints:
    1 <= s.length <= 100
    s only contains lower case English letters.
    1 <= shift.length <= 100
    shift[i].length == 2
    0 <= shift[i][0] <= 1
    0 <= shift[i][1] <= 100

    Hint: You may notice that left shift cancels the right shift, so count the total left shift times,
    (may be negative if the final result is right shift), and perform it once.
 */
//  String.substring()
public class PerformStringShifts {
    public String stringShift(String s, int[][] shift) {
        int pos = 0, len = s.length();
        for (int[] sh : shift) {
            pos += sh[0] == 0 ? sh[1] : -sh[1];
        }
        // use pos % len + len to avoid negative index and () % len to avoid being out of bound
        // -3 % 7 = -3 -> -3+7 = 4 => 向左移 x 相当于向右移 len-x
        pos = (pos % len + len) % len;
        return s.substring(pos) + s.substring(0, pos);
    }
}

/*  my version

        int len = s.length();
        int count = 0;
        for (int[] sh : shift) {
            if (sh[0] == 0) {
                count -= sh[1];
            } else {
                count += sh[1];
            }
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        if (count == 0) {
            return s;
        } else if (count > 0) {
            count = count % len;
            index = len - count;
            for (int i = 0; i < count; i++) {
                sb.append(s.charAt(index++));
            }
            index = 0;
            for (int i = count; i < len; i++) {
                sb.append(s.charAt(index++));
            }
            return sb.toString();
        } else {
            count = (-count % len) * (-1);
            index = -count;
            for (int i = 0; i < len + count; i++) {
                sb.append(s.charAt(index++));
            }
            index = 0;
            for (int i = len + count; i < len; i++) {
                sb.append(s.charAt(index++));
            }
            return sb.toString();
        }
 */
