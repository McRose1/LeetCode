package Trie;

/*  212. Word Search 2
    Given a 2D board and a list of words from the dictionary, find all words in the board.
    Each word must be constructed from letters of sequentially adjacent cell,
    where "adjacent" cells are those horizontally or vertically neighboring.
    The same letter cell may not be used more than once in a word.

    Example:
    Input:
    board = [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]
    words = ["oath","pea","eat","rain"]
    Output: ["eat","oath"]

    Note:
    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.

    Hint 1:
    You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

    Hint 2:
    If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
    What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not?
    How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 */

import java.util.ArrayList;
import java.util.List;
//  Backtracking + Trie
public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        // 通过给定的输入字符串建立本题的 Trie Tree
        TrieNode root = buildTrie(words);

        // 遍历矩阵，以任意点为起点开始 DFS(backtrack)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 注意一开始传进去的是 Trie Tree 的 root
                backtrack(board, i, j, root, res);
            }
        }
        return res;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            // 一层一层加
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new TrieNode();
                }
                // 进入下一层
                node = node.children[i];
            }
            // 最后一层的 TrieNode 保存额外信息，因为它是单词的结尾，所以保有整个单词的信息
            node.word = word;
        }
        return root;
    }

    public void backtrack(char[][] board, int i, int j, TrieNode node, List<String> res) {
        // 考虑越界问题
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;

        char c = board[i][j];
        // 如果已经访问过，或者在 Trie Tree 里当前 TrieNode 的下一层元素中没有当前字母
        if (c == '#' || node.children[c - 'a'] == null) return;

        // 进入 Trie Tree 的下一层
        node = node.children[c - 'a'];
        if (node.word != null) {
            res.add(node.word);
            // 用过了就清空，防止反复添加,比如：car, card，如果遍历过 car 以后不清空，遍历 card 的时候就会又加一次 car
            node.word = null;
        }
        // 标记已经 visited
        board[i][j] = '#';
        // 往四个方向递归
        backtrack(board, i - 1, j, node, res);
        backtrack(board, i + 1, j, node, res);
        backtrack(board, i, j + 1, node, res);
        backtrack(board, i, j - 1, node, res);
        // backtracking
        board[i][j] = c;
    }
}
