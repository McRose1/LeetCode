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
/*  Backtracking + Trie: Time = O(m*n*4^l) Space = O(sum(l))
    Build a trie of the dictionary and use DFS to traverse the board, the path must also exist in trie.
 */
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
            TrieNode cur = root;
            // 一层一层加
            for (char c : word.toCharArray()) {
                // 如果 Trie 里还没有这个字母，在 children 里创建
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                // 如果 Trie 里已经存在这个字母，顺着 children 这条链进入下一层
                cur = cur.children[c - 'a'];
            }
            // 最后一层的 TrieNode 保存额外信息，标记该 word；因为它是单词的结尾，所以保有整个单词的信息
            cur.word = word;
        }
        return root;
    }

    public void backtrack(char[][] board, int i, int j, TrieNode cur, List<String> res) {
        // 考虑越界问题
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;

        // 存下当前位置的字母
        char temp = board[i][j];
        // 如果已经访问过，或者在 Trie Tree 里当前 TrieNode 的下一层元素中没有当前字母
        if (temp == '#' || cur.children[temp - 'a'] == null) return;

        // 进入 Trie Tree 的下一层
        cur = cur.children[temp - 'a'];

        // 如果 word 不为空，说明已经找到 1 个单词，且已遍历到该单词的结尾
        if (cur.word != null) {
            res.add(cur.word);
            // 用过了就清空，防止反复添加,比如：car, card，如果遍历过 car 以后不清空，遍历 card 的时候就会又加一次 car
            // 这一步非常重要！！！
            cur.word = null;
        }

        // 标记已经 visited
        board[i][j] = '#';
        // 往四个方向递归
        backtrack(board, i - 1, j, cur, res);
        backtrack(board, i + 1, j, cur, res);
        backtrack(board, i, j + 1, cur, res);
        backtrack(board, i, j - 1, cur, res);
        // backtracking
        board[i][j] = temp;
    }
}

/*  LC (TLE)

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        String word = null;

        public TrieNode() {
        }
    }

    class Solution {
        char[][] _board = null;
        List<String> _res = new ArrayList<>();

        public List<String> findWords(char[][] board, String[] words) {

            // Step 1. Construct the Trie
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
                        node = newNode;
                    }
                }
                node.word = word; // store words in Trie;

                this._board = board;

                // Step 2. Backtracking starting for each cell in the board
                for (int row = 0; row < board.length; row++) {
                    for (int col = 0; col < board[row].length; col++) {
                        if (root.children.containsKey(board[row][col])) {
                            backtrack(row, col, root);
                        }
                    }
                }
                return this._res;
            }

            private void backtrack(int row, int col, TrieNode parent) {
                Character letter = this._board[row][col];
                TrieNode currNode = parent.children.get(letter);

                // check if there is any match
                if (currNode.word != null) {
                    this._res.add(currNode.word);
                    currNode.word = null;
                }

                // mark the current letter before EXPLORATION
                this._board[row][col] = '#';

                // explore neighbor cells in around-clock directions: up, right, down, left
                int[] rowOffset = {-1, 0, 1, 0};
                int[] colOffset = {0, 1, 0, -1};
                for (int i = 0; i < 4; i++) {
                    int newRow = row + rowOffset[i];
                    int newCol = col + colOffset[i];
                    if (newRow < 0 || newRow >= this._board.length || newCol < 0 || newCol >= this._board[0].length) {
                        continue;
                    }
                    if (currNode.children.containsKey(this._board[newRow][newCol])) {
                        backtrack(newRow, newCol, currNode);
                    }
                }

                // End of EXPLORATION, restore the original letter in the board.
                this._board[row][col] = letter;

                // Optimization: incrementally remove the leaf nodes
                if (currNode.children.isEmpty()) {
                    parent.children.remove(letter);
                }
            }
        }
    }
 */