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
    o You may assume all letters are in lowercase.
    o You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    o If the order is invalid, return an empty string.
    o There may be multiple valid order of letters, return any one of them is fine.
 */

import java.util.*;

/*  Topological Sort + BFS
    "wrt", "wrf" => t->f
    "wrf", "er" => w->e
    "er", "ett" => r->t
    "ett", "rftt" => e->r
    这些就是有向图的边，对于有向图中的每个结点，计算其入度，然后从入度为 0 的结点开始 BFS 遍历这个有向图
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        // 构建图：key 对应每个 char，value 是从这个 char 出发可达的其他 chars
        Map<Character, Set<Character>> graph = new HashMap<>();

        // 统计入度
        int[] inDegree= new int[26];

        // 两步走：1.构建图+统计入度；2.用 bfs 去更新入度
        buildGraph(words, graph, inDegree);
        return bfs(words, graph, inDegree);
    }

    // 1.构建图 (graph) + 统计入度 (inDegree)
    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] inDegree) {

        // 为每个 char 建好空图
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        // 填图+统计入度
        for (int i = 1; i < words.length; i++) {
            // 取出前后两个 String 作比较，比如 first-wrt, second-wrff
            String first = words[i - 1];                            // wrt
            String second = words[i];                               // wrff

            int len = Math.min(first.length(), second.length());    // 3
            // 对相邻 String 进行比较
            for (int j = 0; j < len; j++) {
                // 取出相同 index 的 char
                char out = first.charAt(j);                     // t
                char in = second.charAt(j);                     // f
                // 不相等的时候就能排出先后，即 out 指向 in
                if (out != in) {
                    if (!graph.get(out).contains(in)) {
                        graph.get(out).add(in);             // t -> f
                        inDegree[in - 'a']++;               // inDegree[f]++
                    }
                    // 完成->已经有差异，不再往下比
                    break;
                }
            }
        }
    }

    // 2.用 bfs 去更新入度，入度越小说明字典序越小，也就越后面才遍历到
    // Topological Sort
    private String bfs(String[] words, Map<Character, Set<Character>> graph, int[] inDegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        // bfs 起步->从图里的 char 去遍历，入度 + sb更新：入度为 0 的放进 queue
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                queue.offer(c);
                sb.append(c);
            }
        }
        // while loop 后续->吐出来，吃进去，入度，sb
        while (!queue.isEmpty()) {
            char out = queue.poll();
            // 遍历 c 的 hashset
            for (char in : graph.get(out)) {
                inDegree[in - 'a']--;
                if (inDegree[in - 'a'] == 0) {
                    queue.offer(in);
                    sb.append(in);
                }
            }
        }
        // 判断是否 graph 里的每一个 char 都已经被处理过了
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}
