package BFS_DFS;

/*  126. Word Ladder 2
    Given two words (beginWord and endWord), and a dictionary's word list,
    find all shortest transformation sequence(s) from beginWord to endWord, such that:
    1. Only one letter can be changed at a time.
    2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

    Note:
    Return an empty list if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

    Example 1:
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
    Output:
    [
      ["hit","hot","dot","dog","cog"],
      ["hit","hot","lot","log","cog"]
    ]

    Example 2:
    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    Output: []
    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

import java.util.*;

/*  BFS + DFS
    无向图 -> BFS -> 有向图（树）-> DFS -> res
    1. 使用 BFS 把 start 到 end 最短路径 level 中的 graph 建立起来
    2. 使用 DFS 从 end 到 start 找出所有的 ladders
 */
public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList.size() == 0) return res;
        // candidates set
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        // 构建 graph，代表从起始 string 可以到达的一串路径
        HashMap<String, List<String>> map = new HashMap<>();
        // 当前层的 beginWord candidates
        HashSet<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        bfs(startSet, endWord, map, dict);

        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(res, list, beginWord, endWord, map);
        return res;
    }

    private void bfs(HashSet<String> startSet, String endWord, HashMap<String, List<String>> map, HashSet<String> dict) {
        if (startSet.size() == 0) return;

        HashSet<String> tmp = new HashSet<>();
        // 保证不会倒退遍历
        dict.removeAll(startSet);
        boolean finish = false;

        // 从 candidates 遍历
        for (String s : startSet) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char old = chs[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == chs[i]) {
                        continue;
                    }
                    chs[i] = c;
                    String word = new String(chs);

                    // 替换单词成功
                    if (dict.contains(word)) {
                        if (word.equals(endWord)) {
                            finish = true;
                        } else {
                            // 还没到达结尾，下一次递归用 tmp 做起点
                            tmp.add(word);
                        }
                        // 更新 graph
                        if (!map.containsKey(s)) {
                            map.put(s, new ArrayList<>());
                        }
                        map.get(s).add(word);
                    }
                }
                chs[i] = old;
            }
        }
        // 还没到达 endWord
        if (!finish) {
            bfs(tmp, endWord, map, dict);
        }
    }

    private void dfs(List<List<String>> res, List<String> list, String word, String endWord, HashMap<String, List<String>> map) {
        if (word.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (map.get(word) == null) return;
        for (String next : map.get(word)) {
            list.add(next);
            dfs(res, list, next, endWord, map);
            // backtrack
            list.remove(list.size() - 1);
        }
    }
}

/*  bi-direction BFS + DFS

    public List<List<String>> findLadders(String beginWord,
    String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        Map<String, List<String>> map = new HashMap<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        startSet.add(beginWord);

        bfs(startSet, endSet, map, dict, false);

        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(res, list, beginWord, endWord, map);
        return res;
    }


    private void dfs(List<List<String>> res, List<String> list,
         String word, String endWord, Map<String, List<String>> map) {
        if (word.equals(endWord)) {
            res.add(new ArrayList(list));
            return;
        }

        if (map.get(word) == null) return;
        for (String next : map.get(word)) {
            list.add(next);
            dfs(res, list, next, endWord, map);
            list.remove(list.size() - 1);
        }
    }

    private void bfs(Set<String> startSet, Set<String> endSet,
        Map<String, List<String>> map, Set<String> dict, boolean reverse) {
        if (startSet.size() == 0) return;

        if (startSet.size() > endSet.size()) {
            bfs(endSet, startSet, map, dict, !reverse);
            return;
        }

        Set<String> tmp = new HashSet<>();
        dict.removeAll(startSet);
        boolean finish = false;

        for (String s : startSet) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char old = chs[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chs[i] = c;
                    String word = new String(chs);

                    if (dict.contains(word)) {
                        if (endSet.contains(word)) {
                            finish = true;
                        } else {
                            tmp.add(word);
                        }

                        String key = reverse ? word : s;
                        String val = reverse ? s : word;

                        if (map.get(key) == null) {
                            map.put(key, new ArrayList<>());
                        }

                        map.get(key).add(val);
                    }
                }

                chs[i] = old;
            }
        }

        if (!finish) {
            bfs(tmp, endSet, map, dict, reverse);
        }
    }
 */

/*  BFS + Backtracking

        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) return res;

        Set<String> set = new HashSet<>(wordList);
        Queue<List<String>> queue = new LinkedList<>();

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.offer(path);

        while (!queue.isEmpty()) {
            if (!res.isEmpty()) {
                return res;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<String> curList = queue.poll();
                // curList 的末尾就是当前遍历的起点
                String curWord = curList.get(curList.size() - 1);

                // important!!! 当一个单词已经是起点的时候，我们就可以安心将其删去
                set.remove(curWord);

                if (curWord.equals(endWord)) {
                    res.add(new ArrayList<>(curList));
                } else {
                    StringBuilder sb = new StringBuilder(curWord);
                    for (int j = 0; j < curWord.length(); j++) {
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == curWord.charAt(j)) {
                                continue;
                            }
                            sb.setCharAt(j, c);
                            if (set.contains(sb.toString())) {
                                curList.add(sb.toString());
                                // 需要 deep copy，让这个状态定格
                                queue.offer(new ArrayList<>(curList));
                                // backtrack
                                curList.remove(curList.size() - 1);
                            }
                        }
                        sb.setCharAt(j, curWord.charAt(j));
                    }
                }
            }
        }
        return res;
 */