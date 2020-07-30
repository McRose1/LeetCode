package SlidingWindow;

/*  340. Longest Substring with At Most K Distinct Characters
    Given a string, find the length of the longest substring T that contains at most k distinct characters.

    Example 1:
    Input: s = "eceba", k = 2
    Output: 3
    Explanation: T is "ece" which its length is 3.

    Example 2:
    Input: s = "aa", k = 1
    Output: 2
    Explanation: T is "aa" which its length is 2.
 */

import java.util.HashMap;

/*  Sliding Window + HashMap

 */
public class LongestSubstrinwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;

        // 双指针
        int i = 0, j = 0;
        for (; i < n; i++) {
            // j 往右走（没有出界 & map 的 size 不超过 k），window 变大
            while (j < n && map.size() <= k) {
                char right = s.charAt(j);
                // right 在 map 里
                if (map.containsKey(right)) {
                    map.put(right, map.get(right) + 1);
                } else {
                    // 加不了
                    if (map.size() == k) break;
                    // 加的了
                    map.put(right, 1);
                }
                j++;
            }
            max = Math.max(max, j - i);
            // i 往右走，window 变小
            char left = s.charAt(i);
            // left 在 map 里
            if (map.containsKey(left)) {
                int count = map.get(left);
                if (count > 1) {
                    map.put(left, count - 1);
                } else {
                    map.remove(left);
                }
            }
        }
        return max;
    }
}
