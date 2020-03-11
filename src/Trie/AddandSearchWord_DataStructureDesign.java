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

/*
class Trie {
    Trie[] children;
    boolean isWord;
    String word;

    public Trie() {
        children = new Trie[26];
        isWord = false;
        word = "";
    }
}

 */

public class AddandSearchWord_DataStructureDesign {
    private TrieNode root;

    public AddandSearchWord_DataStructureDesign() {
        root = new TrieNode();
    }

    public void addwWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int j = word.charAt(i) - 'a';
            if (node.links[j] == null) {
                node.links[j] = new TrieNode();
            }
            node = node.links[j];
        }
        node.isWord = true;
        node.word = word;
    }

    public boolean search(String word) {
        return find(word, root, 0);
    }

    private boolean find(String word, TrieNode node, int index) {
        if (index == word.length()) return node.isWord;
        if (word.charAt(index) == '.') {
            for (TrieNode temp : node.links) {
                if (temp != null && find(word, temp, index + 1)) return true;
            }
            return false;
        } else {
            int j = word.charAt(index) - 'a';
            TrieNode temp = node.links[j];
            return temp != null && find(word, temp, index + 1);
        }
    }
}
