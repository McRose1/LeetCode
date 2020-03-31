package BFS_DFS;

/*  127. Word Ladder
    Given two words (beginWord and endWord), and a dictionary's word list,
    find the length of shortest transformation sequence from beginWord to endWord, such that:
    1. Only one letter can be changed at a time.
    2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

    Note:
    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

    Example 1:
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
    Output: 5
    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.

    Example 2:
    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    Output: 0
    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

import java.util.*;

//  BFS using Queue: Time = O(n*(26^l)) Space = O(n)
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        // 直接把 wordList dump 到 set 中
        Set<String> set = new HashSet<>(wordList);    // "hit" -> "hot" -> "dot" -> "dog" -> "cog"

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);                     // hit

        int l = beginWord.length();
        int len = 0;

        while (!queue.isEmpty()) {
            // 当前层数
            len++;                                      // len = 1;
            // 每一层的 candidates 个数就是 queue 的 size
            for (int s = queue.size(); s > 0; s--) {
                String str = queue.poll();              // hit
                char[] chs = str.toCharArray();         // [h,i,t]
                // BFS 体现在这儿，每个字母都替换一遍
                for (int i = 0; i < l; i++) {
                    // 保存当前值，为了后面还原
                    char old = chs[i];
                    // a-z 替换
                    for (char c = 'a'; c <= 'z'; c++) {
                        // 跳过当前单词
                        if (c == old) continue;
                        chs[i] = c;
                        // 当前替换完成的单词
                        String temp = new String(chs);
                        if (temp.equals(endWord)) return len + 1;
                        // 替换完成的单词不在 wordList 中，也可跳过
                        if (!set.contains(temp)) continue;
                        // 从 set 中删去命中词，以防止重复使用，这样就不会倒退
                        set.remove(temp);                      // [dot,dog,lot,log]
                        // queue 里存的是这一层candidates替换合法的，准备下一层替换的 candidates
                        queue.offer(temp);                      // hot
                    }
                    // 每个位置的字母替换以后进行下一个位置的字母替换之前，需要先还原当前单词
                    chs[i] = old;
                }
            }
        }
        return 0;
    }
}

/*  Bidirectional BFS: Time = O(n*(26^(l/2))) Space = O(n)
    We can considerably cut down the search space of the standard BFS if we launch two simultaneous BFS.
    One from the beginWord and one from the endWord.

        if (!wordList.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        int len = 0;
        int strLen = beginWord.length();
        HashSet<String> dict = new HashSet<>(wordList);

        beginSet.add(beginWord);        // "hit"
        endSet.add(endWord);            // "cog"
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            len++;
            if (beginSet.size() > endSet.size()) {      // beginSet: {hit, hot}  endSet: {cog}
                // beginSet 和 endSet 互换，实现双向
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;                           // beginSet: {cog}  endSet: {hit, hot}
            }
            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {          // "hit"; "cog"
                char[] chs = word.toCharArray();    // chs = [hit]

                for (int i = 0; i < chs.length; i++) {
                    char old = chs[i];          // old = chs[0] = 'h'
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;                 // [hit] -> [ait]
                        String target = new String(chs);    // target = "ait"

                        // 终止条件
                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!dict.contains(target)) continue;
                        dict.remove(target);
                        temp.add(target);
                    }
                    // backtracking
                    chs[i] = old;
                }
            }
            beginSet = temp;
        }
        return 0;
    }
 */