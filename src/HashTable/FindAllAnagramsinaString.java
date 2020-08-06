package HashTable;

/*  438. Find All Anagrams in a String
    Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
    Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
    The order of output does not matter.

    Example 1:
    Input:
    s: "cbaebabacd" p: "abc"

    Output:
    [0, 6]

    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".

    Example 2:
    Input:
    s: "abab" p: "ab"

    Output:
    [0, 1, 2]

    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

import java.util.ArrayList;
import java.util.List;

/*  HashTable + SlidingWindow: Time = O(n) Space = O(n)

 */
public class FindAllAnagramsinaString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        int[] pArr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pArr[p.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;
        // sliding window size
        int count = p.length();

        while (right < s.length()) {
            // 拓展滑动窗口
            if (pArr[s.charAt(right) - 'a'] > 0) {
                count--;
            }
            pArr[s.charAt(right) - 'a']--;
            // 移动右边界
            right++;

            // 找到 valid 的解，输出答案
            if (count == 0) {
                res.add(left);
            }

            // 滑动窗口已满，应该往右移
            if (right - left == p.length()) {
                // 'dabc' 'abc' -> count 不用还原 + 1
                if (pArr[s.charAt(left) - 'a'] >= 0) {
                    count++;
                }
                pArr[s.charAt(left) - 'a']++;
                // 左边界右移
                left++;
            }
        }
        return res;
    }
}
