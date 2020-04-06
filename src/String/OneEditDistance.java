package String;

/*  161. One Edit Distance
    Given two strings s and t, determine if they are both one edit distance apart.

    Note: There are 3 possibilities to satisfy one edit distance apart:
    1. Insert a character into s to get t
    2. Delete a character from s to get t
    3. Replace a character of s to get t

    Example 1:
    Input: s = "ab", t = "abc"
    Output: true

    Example 2:
    Input: s = "cab", t = "ad"
    Output: false

    Example 3:
    Input: s = "1203", t = "1213"
    Output: true
 */

/*  分别处理 Delete(Insert) 和 Replace 的情况: Time = O(n) Space = O(1)
    isOneRemoveDistance：
    1. 设置一个 boolean flag，第一次遇到s和t的字母不同时，设置flag为true，让t往回走一步；当再次遇到s和t的字母不同时,return false
    2. 最后 return true
    isOneChangeDistance：
    1. 设置一个 boolean flag，第一次遇到s和t的字母不同时，设置flag为true，当再次遇到s和t的字母不同时,return false
    2. 最后 return flag（避免s和t完全相同的情况）
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        int m = s.length();
        int n = t.length();

        // 长度相等
        if (n == m) {
            return isOneChangeDistance(s, t);
        // s 比 t 长 1
        } else if (m - n == 1) {
            return isOneRemoveDistance(s, t);
        // t 比 s 长 1
        } else if (n - m == 1) {
            return isOneRemoveDistance(t, s);
        }
        return false;
    }
    public boolean isOneChangeDistance(String s, String t) {
        int m = s.length();
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (flag) {
                    return false;
                } else {
                    flag = true;
                }
            }
        }
        return flag;
    }
    public boolean isOneRemoveDistance(String s, String t) {
        int n = t.length();
        boolean flag = false;
        int fast = 0;
        for (int slow = 0; slow < n; slow++) {
            if (s.charAt(fast) != t.charAt(slow)) {
                if (flag) {
                    return false;
                }
                flag = true;
                slow--;
            }
            fast++;
        }
        return true;
    }
}

/*  AndroidBaby

        int m = s.length();
        int n = t.length();
        // 把 s 当做较短的 string
        if (m > n) return isOneEditDistance(t, s);

        // 长度相等
        if (m == n) {
            int dif = 0;
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    dif++;
                    if (dif > 1) return false;
                }
            }
            return dif == 1;
        }
        // 长度相差为 1
        else if (m == n - 1) {
            int i = 0;
            while (i < m) { // ab, acb
                if (s.charAt(i) != t.charAt(i)) {
                    break;
                }
                i++;
            }
            for (int j = i; j < m; j++) {
                if (s.charAt(j) != t.charAt(j + 1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
 */
