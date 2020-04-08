package Tree;

/*  173. Binary Search Tree Iterator
    Implement an iterator over a binary search tree (BST).
    Your iterator will be initialized with the root node of a BST.
    Calling next() will return the next smallest number in the BST.

    Example:
    BSTIterator iterator = new BSTIterator(root);
    iterator.next();    // return 3
    iterator.next();    // return 7
    iterator.hasNext(); // return true
    iterator.next();    // return 9
    iterator.hasNext(); // return true
    iterator.next();    // return 15
    iterator.hasNext(); // return true
    iterator.next();    // return 20
    iterator.hasNext(); // return false

    Note:
    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is,
    there will be at least a next smallest number in the BST when next() is called.
 */
import java.util.Stack;

/*  Controlled Recursion: Time = O(n) Space = O(h)
    We can use stack for our recursion simulation.
    We will be taking an iterative approach to inorder traversal rather than recursion.
 */
public class BinarySearchTreeIterator {

    Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {

        // Stack for the recursion simulation
        stack = new Stack();

        // Remember that the algorithm starts with a call to the helper function with the root node as the input
        leftmostInorder(root);
    }

    private void leftmostInorder(TreeNode root) {

        // For a given node, and all the elements in the leftmost branch of the tree under it to the stack.
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        // Node at the top of the stack is the next smallest element
        TreeNode topmostNode = stack.pop();

        // Need to maintain the invariant.
        // If the node has a right child, call the helper function for the right child.
        if (topmostNode.right != null) {
            leftmostInorder(topmostNode.right);
        }

        return topmostNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }
}

/*  Flattening the BST
    Naturally, the easiest way to implement an iterator would be on an array like container interface.
    If we had an array, all we would need is a pointer or an index.
    We will be using additional memory and we will flatten the BST into an array.

    ArrayList<Integer> nodesSorted;
    int index;

    public BinarySearchTreeIterator(TreeNode root) {

        // Array containing all the nodes in the sorted order
        nodesSorted = new ArrayList<>();

        // Pointer to the next smallest element in the BST
        index = -1;

        // Call to flatten the input BST
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        nodesSorted.add(root.val);
        inorder(root.right);
    }

    // @return the next smallest number //
    public int next() {
        return nodesSorted.get(++index);
    }

    // @return whether we have a next smallest number //
    public boolean hasNext() {
        return index + 1 < nodesSorted.size();
    }
 */
