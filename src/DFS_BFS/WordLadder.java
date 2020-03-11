package DFS_BFS;

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

/*  Bidirectional BFS: Time = O(n*(26^(l/2))) Space = O(n)
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
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
                    chs[i] = old;   // backtracking
                }
            }
            beginSet = temp;
        }
        return 0;
    }
}

/*  BFS: Time = O(n*(26^l)) Space = O(n)

        if (!wordList.contains(endWord)) return 0;
        Set<String> dict = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int l = beginWord.length();
        int len = 0;

        while (!queue.isEmpty()) {
            len++;
            for (int s = queue.size(); s > 0; s--) {
                String str = queue.poll();
                char[] chs = str.toCharArray();
                for (int i = 0; i < l; i++) {
                    char old = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        chs[i] = c;
                        String temp = new String(chs);
                        if (temp.equals(endWord)) return len + 1;
                        if (!dict.contains(temp)) continue;
                        dict.remove(temp);
                        queue.offer(temp);
                    }
                    chs[i] = old;
                }
            }
        }
        return 0;
    }
 */