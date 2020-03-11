package Trie;

public class TrieNode {
    // R links to node children
    TrieNode[] links;

    String word;

    private final int R = 26;

    private boolean isEnd;

    boolean isWord;

    public TrieNode() {
        links = new TrieNode[R];
        boolean isWord;
        word = "";
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
