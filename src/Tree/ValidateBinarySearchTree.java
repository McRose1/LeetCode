package Tree;

/*  98. Validate Binary Search Tree
    Given a binary tree, determine if it is a valid binary search tree (BST).
    Assume a BST is defined as follows:
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees..

    Example 1:
        2
       / \
      1   3

    Input: [2,1,3]
    Output: true

    Example 2:
        5
       / \
      1   4
         / \
        3   6

    Input: [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.
 */
/*  DFS: Time = O(n) Space = O(n)
    Traverse the tree and limit the range of each subtree and check whether root's value is in the range.
    In order to cover the range of -2^31 ~ 2^31-1, we need to use long or nullable integer.
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long min, long max) {
        // 出口
        if (root == null) return true;
        // value 不在（min, max）之间
        if (root.val <= min || root.val >= max) return false;
        return dfs(root.left, min, Math.min(max, root.val)) &&
                dfs(root.right, Math.max(min, root.val), max);
    }
}
/*  null integer version

    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min
            || max!= null && root.val >= max) return false;
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
 */

/*  In-order traversal: Time = O(n) Space = O(n)
    the numbers should be sorted, thus we only need to compare with the previous number.

    private TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inOrder(root);
    }

    private boolean inOrder(TreeNode root) {
        if (root == null) return true;
        if (!inOrder(root.left)) return false;
        if (prev != null && root.val <= prev.val) return false;
        prev = root;
        return inOrder(root.right);
    }
 */

/*  Iteration using stack: Time = O(n) Space = O(n)

    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();
    public boolean isValidBST(TreeNode root) {
        Integer lower = null, upper = nul, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (low != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }
 */