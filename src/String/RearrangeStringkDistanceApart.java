package String;

/*  358. Rearrange String k Distance Apart
    Given a non-empty string s and and integer k,
    rearrange the string such that the same characters are at least distance k from each other.

    All input strings are given in lowercase letters.
    If it is not possible to rearrange the string, return an empty string "".

    Example 1:
    Input: s = "aabbcc", k = 3
    Output: "abcabc"

    Example 2:
    Input: s = "aaabc", k = 3
    Output: ""

    Example 3:
    Input: s = "aaadbbcc", k = 2
    Output: "abacabcd"
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*  Greedy + HashTable
    哈希表建立字符和其出现次数之间的映射
    堆来保存这一堆映射，按照出现次数来排序
    如果堆不为空我们就开始循环，找出k和str长度之间的较小值，然后从 0 遍历到这个较小值
    对于每个遍历到的值，如果此时 heap 为空，说明此位置没法填入字符了，返回空字符串
    否则我们从堆顶取出这一对映射，然后把字母加入结果sb中，此时映射的个数减 1
    如果减 1 后的个数仍然大于 0，我们将此映射加入临时集合 ch 中，同时str的个数减1
    遍历完一次，我们把临时集合中的映射加入 heap 中
 */
public class RearrangeStringkDistanceApart {
    public String rearrangeString(String str, int k) {
        if (str == null || k <= 1) return str;
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[1] = a[1];
            }
        });
        Queue<int[]> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int[] array = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            array[c - 'a']++;
        }
        for (int j = 0; j < 26; j++) {
            if (array[j] != 0) {
                heap.offer(new int[]{j, array[j]});
            }
        }
        while (!heap.isEmpty()) {
            int[] ch = heap.poll();
            sb.append((char)ch[0] + 'a');
            if (--ch[1] > 0) {
                heap.offer(queue.poll());
            }
        }
        while (!queue.isEmpty()) {
            if (sb.length() < k) {
                return "";
            }
            int[] ch = queue.poll();
            if (ch[1] == 1) {
                sb.append((char)(ch[0] + 'a'));
            } else {
                return "";
            }
        }
        return sb.toString();
    }
}
