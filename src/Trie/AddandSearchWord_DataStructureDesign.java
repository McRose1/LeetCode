package Trie;

/*  211. Add and Search Word - Data Structure Design
    Design a data structure that supports the following two operations:

    void addWord(word)
    bool search(word)

    search(word) can search a literal word or a regular expression string containing only letters a-z or .
    A . means it can represent any one letter.

    Example:
    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true

    Note: You may assume that all words are consist of lowercase letters a-z.
 */

//  Trie
public class AddandSearchWord_DataStructureDesign {
    private TrieNode root;

    public AddandSearchWord_DataStructureDesign() {
        root = new TrieNode();
    }

    public void addwWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int j = word.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new TrieNode();
            }
            node = node.children[j];
        }
        // 给这个 Word 的结尾 TrieNode 做上标记
        node.isWord = true;
        node.word = word;
    }

    public boolean search(String word) {
        return find(word, root, 0);
    }

    private boolean find(String word, TrieNode node, int index) {
        // 递归的出口
        // 当字符串遍历完毕，并且 Trie Tree 里也满足已经遍历到最后一层（TrieNode 为这个单词的结尾）
        if (index == word.length()) return node.isWord;

        // 匹配到万能符，用这一层 TrieNode 数组里存在的元素（代表 26 个字母，有些元素是 null) 依次替代它调用递归
        if (word.charAt(index) == '.') {
            for (TrieNode temp : node.children) {
                if (temp != null && find(word, temp, index + 1)) return true;
            }
            return false;
        }
        // 匹配到普通字母
        else {
            int j = word.charAt(index) - 'a';
            // 如果匹配不上则返回 null
            TrieNode temp = node.children[j];
            return temp != null && find(word, temp, index + 1);
        }
    }
}
