package Trie;

/*  208. Implement Trie (Prefix Tree)
    Implement a trie with insert, search, and startsWith methods.

    Example:
    Trie trie = new Trie();

    trie.insert("apple");
    trie.search("apple");   // returns true
    trie.search("app");     // returns false
    trie.startsWith("app"); // returns true
    trie.insert("app");
    trie.search("app");     // returns true

    Note:
    You may assume that all inputs are consist of lowercase letters a-z.
    All inputs are guaranteed to be non-empty strings.
 */

public class ImplementTrie_PrefixTree {
    private TrieNode root;

    public ImplementTrie_PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            // 得到当前层的 TrieNode，以进入下一层
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    // search a prefix or whole key in trie and returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            // 没有找到该 key
            } else {
                return null;
            }
        }
        return node;
    }

    // returns if the word is in the trie
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        // 找到 key 并且这个 key 还是结尾
        return node != null && node.isEnd();
    }

    // returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}
