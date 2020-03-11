package Backtracking;

/*  93. Restore IP Address
    Given a string contain only digits, restore it by returning all possible valid IP address combinations.

    Example:
    Input: "25525511135"
    Output: ["255.255.11.135", "255.255.111.35"]
 */

import java.util.ArrayList;
import java.util.List;
/*  Backtracking
    [0, 255], length [4, 12] 1.1.1.1, 111.111.111.111
    1) 一位数
    2) 两位数
    3) 三位数（不超过 255）
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return res;
        backtrack(res, s,"", 0);
        return res;
    }

    private void backtrack(List<String> res, String s, String sub, int index) {
        if (index == 4 && s.length() == 0) res.add(sub.substring(1));     // remove the prefixed '.'
        if (index == 4 || s.length() == 0) return;
        // 加入一位数
        backtrack(res, s.substring(1), sub + "." + s.substring(0, 1), index + 1);
        // 加入两位数（第一个数不能是 0：‘01’，剩余的 string 长度大于 1）
        if (s.charAt(0) != '0' && s.length() > 1) {
            backtrack(res, s.substring(2), sub + "." + s.substring(0, 2), index + 1);
            // 加入三位数（第一个数不能是 0，剩余 string 长度大于 2，'xxx' <= 255）
            if (s.length() > 2 && Integer.valueOf(s.substring(0, 3)) <= 255) {
                backtrack(res, s.substring(3), sub + "." + s.substring(0, 3), index + 1);
            }
        }
    }
}
