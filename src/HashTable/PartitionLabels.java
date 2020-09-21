package HashTable;

/*  763. Partition Labels
    A string s of lowercase English letters is given.
    We want to partition this string into as many parts as possible so that each letter appears in at most one part,
    and return a list of integers representing the size of these parts.

    Example 1:
    Input: S = "ababcbacadefegdehijhklij"
    Output: [9,7,8]
    Explanation:
    The partition is "ababcbaca", "defegde", "hijhklij".
    This is a partition so that each letter appears in at most one part.
    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

    Note:
        o S will have length in range [1, 500].
        o S will consist of lowercase English letters ('a' to 'z') only.

    Hint:
    Try to greedily choose the smallest partition that includes the first letter.
    If you have something like "abaccbdeffed", then you might need to add b.
    You can use an map like "last['b'] = 5" to help you expand the width of your partition.
 */

import java.util.ArrayList;
import java.util.List;

/*  HashTable: Time = O(n) Space = O(1)
    不断地选择从最左边起最小的区间
    对于遇到的每一个字母，去找这个字母最后一次出现的位置，用来更新当前的最小区间
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();

        // 存储 26 个小写字母在字符串中最后出现的 index
        int[] lastIdx = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIdx[S.charAt(i) - 'a'] = i;
        }

        // Two pointers
        int start = 0;
        int end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, lastIdx[S.charAt(i) - 'a']);
            // 需要 partition
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}
