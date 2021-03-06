package Tree;

import java.util.List;

// Definition for a Node.
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

    public Node(int _val, Node _left, Node _right, Node _next, List<Node> _children) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
        children = _children;
    }
}
