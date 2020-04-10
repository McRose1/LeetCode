package TwoPointers;

/*  844. Backspace String Compare
    Given two strings S and T, return if they are equal when both are typed into empty text editors.
    # means a backspace character.

    Example 1:
    Input: S = "ab#c", T = "ad#c"
    Output: true
    Explanation: Both S and T become "ac".

    Example 2:
    Input: S = "ab##", T = "c#d#"
    Output: true
    Explanation: Both S and T become "".

    Example 3:
    Input: S = "a##c", T = "#a#c"
    Output: true
    Explanation: Both S and T become "c".

    Example 4:
    Input: S = "a#c", T = "b"
    Output: false
    Explanation: S becomes "c" while T becomes "b".

    Note:
    1 <= S.length <= 200
    1 <= T.length <= 200
    S and T only contain lowercase letters and '#' characters.

    Follow up: Can you solve it in O(N) time and O(1) space?
 */

/*  Two Pointers: Time = O(n) Space = O(1)
    Iterate through the string in reverse.
    If we see a backspace character, the next non-backspace character is skipped.
    If a character isn't skipped, it is part of the final answer.
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        // 从右往左遍历
        int i = S.length() - 1;
        int j = T.length() - 1;

        // 记录连续 # 的个数
        int skipS = 0;
        int skipT = 0;

        // While there may be chars in S or T
        while (i >= 0 || j >= 0) {
            // Find position of next possible char in S
            while (i >= 0) {
                // 碰到 #
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                // i-- 跳过 # 左边的字母
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                // # 都已经用完，再碰到字母的时候就跳出循环
                } else {
                    break;
                }
            }
            // Find position of next possible char in T
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
                return false;
            }
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }
}

/*  Build String: Time = O(n) Space = O(n)

    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack<>();
        for (char c : S.toCharArray()) {
            if (c != '#') {
                ans.push(c);
            } else if (!ans.isEmpty()) {
                ans.pop();
            }
        }
        return String.valueOf(ans);
    }
 */

/*  my version (Stack): Time = O(n) Space = O(n)

        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        for (char ch1 : S.toCharArray()) {
            if (ch1 == '#' && s1.isEmpty()) {
                continue;
            } else if (ch1 == '#' && !s1.isEmpty()) {
                s1.pop();
            } else {
                s1.push(ch1);
            }
        }

        for (char ch2 : T.toCharArray()) {
            if (ch2 == '#' && s2.isEmpty()) {
                continue;
            } else if (ch2 == '#' && !s2.isEmpty()) {
                s2.pop();
            } else {
                s2.push(ch2);
            }
        }
        if (s1.isEmpty() && s2.isEmpty()) {
            return true;
        } else if (s1.size() != s2.size()) {
            return false;
        } else {
            while (!s1.isEmpty()) {
                if (s1.pop() != s2.pop()) {
                    return false;
                }
            }
        }
        return true;
 */