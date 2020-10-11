package Trie;

/*  720. Longest Word in Dictionary
    Given a list of strings words representing an English Dictionary, find the longest word in words that
    can be built one character at a time by other words in words.
    If there is more than one possible answer, return the longest word with the smallest lexicographical order.
    If there is no answer, return the empty string.

    Example 1:
    Input:
    words = ["w","wo","wor","worl", "world"]
    Output: "world"
    Explanation:
    The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

    Example 2:
    Input:
    words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
    Output: "apple"
    Explanation:
    Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".

    Note:
    o All the strings in the input will only contain lowercase letters.
    o The length of words will be in the range [1, 1000].
    o The length of words[i] will be in the range [1, 30].

    Hint: For every word in the input list, we can check whether all prefixes of that word are in the input list by using a Set.
 */

import java.util.*;

/*  Trie + DFS: Time = O(n) Space = O(26*n*w)
    空间换时间
 */
public class LongestWordinDictionary {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        // 构建 Trie
        for (String word : words) {
            trie.insert(word, ++index);     // indexed by 1
        }
        trie.words = words;
        return trie.dfs();
    }

    class Node {
        char c;
        Map<Character, Node> children = new HashMap<>();
        int end;
        public Node(char c) {
            this.c = c;
        }
    }

    class Trie {
        Node root;
        String[] words;
        public Trie() {
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }

        public String dfs() {
            String res = "";
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                            res = word;
                        }
                    }
                    for (Node nei : node.children.values()) {
                        stack.push(nei);
                    }
                }
            }
            return res;
        }
    }
}

/*  HashSet + Sort: Time = O(n^2) Space = O(n*w)

        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);
        // 从最长的单词开始找
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String word : words) {
            boolean good = true;
            for (int k = 1; k < word.length(); k++) {
                if (!set.contains(word.substring(0, k))) {
                    good = false;
                    break;
                }
            }
            if (good) {
                return word;
            }
        }
        return "";

        String res = "";
        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);
        for (String word : words) {
            // pruning
            if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                boolean good = true;
                for (int k = 1; k < word.length(); k++) {
                    if (!set.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    res = word;
                }
            }
        }
        return res;
 */