package Tree;

/*  101. Symmetric Tree
    Given a binary tree, check whether it is a mirror of itself
    (i.e. symmetric around its center)
    For example, this binary tree [1, 2, 2, 3, 4, 4, 3] is symmetric
            1
           / \
          2   2
         / \  / \
        3  4  4  3
    But the following [1, 2, 2, null, 3, null, 3] is not:
            1
           / \
          2   2
           \   \
           3    3
    Note:
    Bonus points if you could solve it both recursively and iteratively
 */
/*  Recursion: Time = O(n) Space = O(n)
    A tree is symmetric if the left subtree is a mirror reflection of the right subtree
    Therefore, the question is: when are two trees a mirror reflection of each other?
    Two trees are a mirror reflection of each other if:
        1. Their two roots have the same value.
        2. The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.val == left.val) && isMirror(left.right, right.left) && isMirror(left.left, right.right);
    }
}

/*  Iteration using Queue or Stack: Time = O(n) Space = O(n)
    Each two consecutive nodes in the queue should be equal, and their subtrees a mirror of each other.
    initially, the queue contains root and root.
    Then the algorithm works similarly to BFS, with some key differences.
    Each time, two nodes are extracted and their values compared.
    Then, the right children and left children of the two nodes are inserted in the queue in opposite order.
    The algorithm is done when either the queue is empty, or we detect that the tree is not symmetric.

        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
 */