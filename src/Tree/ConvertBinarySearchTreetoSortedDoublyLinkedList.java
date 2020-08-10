package Tree;

/*  426. Convert Binary Search Tree to Sorted Doubly Linked List
    Convert a BST to a sorted circular doubly-linked list in-place.
    Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
    Let's take the following BST as an example, it may help you understand the problem better:

    We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor.
    For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

    The figure below shows the circular doubly linked list for the BST above.
    The "head" symbol means the node it points to is the smallest element of the linked list.

    Specifically, we want to do the transformation in place.
    After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor.
    We should return the pointer to the first element of the linked list.

    The figure below shows the transformed BST. The solid line indicates the successor relationship,
    while the dashed line means the predecessor relationship.

    Example 1:
    输入: {4,2,5,1,3}
            4
           /  \
          2   5
         / \
        1   3
    输出: "left:1->5->4->3->2  right:1->2->3->4->5"
    解释:
    left：逆序输出
    right：正序输出

    Example 2:
    输入: {2,1,3}
            2
           /  \
          1   3
    输出: "left:1->3->2  right:1->2->3"
 */

public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
    private Node prev = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        // 头尾相连
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private void helper(Node cur) {
        if (cur == null) return;
        // left - mid - right
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
    }
}
