package TopologicalSort;

/*  269. Alien Dictionary
    There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
    You receive a list of non-empty works from the dictionary, where words are sorted lexicographically by the rules of this new language.
    Derive the order of letters in this language.

    Example 1:
    Input:
    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]
    Output: "wertf"

    Example 2:
    Input:
    [
      "z",
      "x"
    ]
    Output: "zx"

    Example 3:
    Input:
    [
      "z",
      "x",
      "z"
    ]
    Output: ""
    Explanation: The order is invalid, so return "".

    Note:
    You may assume all letters are in lowercase.
    You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.
 */

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        // 容器
        // 构建图：对于每个 char,它有一堆后续的 char
        Map<Character, Set<Character>> graph = new HashMap<>();
        // 统计入度
        int[] indegree= new int[26];
        // 两步走：1.构建图+统计入度；2.用 bfs 去更新入度
        buildGraph(words, graph, indegree);
        return bfs(words, graph, indegree);
    }
    // 1.构建图+统计入度
    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] indegree) {
        // 为每个 char 建好空图
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        // 填图+统计入度
        for (int i = 1; i < words.length; i++) {
            // 取出前后两个 String 作比较，比如 first-wrt, second-wrff
            String first = words[i - 1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for (int j = 0; j < len; j++) {
                // 取出相同 index 的 char
                char key = first.charAt(j);
                char value = second.charAt(j);
                // 不相等的时候就能排出先后
                if (key != value) {
                    if (!graph.get(key).contains(value)) {
                        graph.get(key).add(value);
                        indegree[value - 'a']++;
                    }
                    // 完成->已经有差异，不再往下比
                    break;
                }
            }
        }
    }
    // 2.用 bfs 去更新入度
    private String bfs(String[] words, Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        // bfs套路：queue, offer, poll, offer, empty
        Queue<Character> q = new LinkedList<>();
        // bfs 起步->从图里的 char 去遍历，入度 + sb更新：入度为 0 的放进 q
        for (char c : graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                q.offer(c);
                sb.append(c);
            }
        }
        // while loop 后续->吐出来，吃进去，入度，sb
        while (!q.isEmpty()) {
            char c = q.poll();
            // 遍历 c 的 hashset
            for (char nextC : graph.get(c)) {
                indegree[nextC - 'a']--;
                if (indegree[nextC - 'a'] == 0) {
                    q.offer(nextC);
                    sb.append(nextC);
                }
            }
        }
        // 判断是否 graph 里的每一个 char 都已经被处理过了
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}
