package Tree;

/*  543. Diameter of Binary Tree
    Given a binary tree, you need to compute the length of the diameter of the tree.
    The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
    This path may or may not pass through the root.

    Example:
    Given a binary tree
          1
         / \
        2   3
       / \
      4   5
    Returns 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

    Note: The length of path between two nodes is represented by the number of edges between them.
 */

/*  Recursion (DFS): Time = O(n) Space = O(h)
    两个叶子结点之间路径 = 根节点左右儿子的高度之和
 */
public class DiameterofBinaryTree {
    // 全局变量 max 在每个 node 都更新一遍，实现了最大值可以不通过 root 的这种情况
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        // 左子树高度
        int lh = dfs(root.left);
        // 右子树高度
        int rh = dfs(root.right);

        // 更新最大值
        max = Math.max(max, lh + rh);
        // 从下往上返回当前 node 的高度
        return Math.max(lh, rh) + 1;
    }
}
