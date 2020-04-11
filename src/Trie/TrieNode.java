package Trie;

public class TrieNode {
    // R links to node children
    TrieNode[] children;

    String word;

    private final int R = 26;

    private boolean isEnd;

    boolean isWord;

    public TrieNode() {
        children = new TrieNode[R];
        isWord = false;
        word = "";
    }

    //  以下三个操作可以看作是 HashMap

    public boolean containsKey(char ch) {
        return children[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return children[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        children[ch -'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
