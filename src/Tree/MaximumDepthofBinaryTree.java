package Tree;

/*  104. Maximum Depth of Binary Tree
    Given a binary tree, find its maximum depth.
    The maximum depth is the number of nodes along the longest path
    from the root node down to the farthest leaf node.
    Note: A leaf is a node with no children.
    Example:
    Given binary tree [3, 9, 20, null, null, 15, 7]
            3
           / \
          9   20
              / \
             15  7
    return its depth = 3
 */
/*  Recursion: Time = O(n) Space = O(height)
    如果我们知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度即为：max(l, r) + 1
 */
public class MaximumDepthofBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

/*  Iteration: Time = O(n) Space = O(n)

        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return depth;
 */

/*  my version（复杂版）

    public int maxDepth(TreeNode root) {
        return dfs(root, 1);
    }

    private int dfs(TreeNode root, int depth) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return depth;
        return Math.max(dfs(root.left, depth + 1), dfs(root.right, depth + 1));
    }
 */