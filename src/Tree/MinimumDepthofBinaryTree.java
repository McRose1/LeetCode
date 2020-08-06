package Tree;

/*  111. Minimum Depth of Binary Tree
    Given a binary tree, find its minimum depth.
    The minimum depth is the number of nodes along the shortest path
    from the root node down to the nearest leaf node.
    Note: A leaf is a node with no children.
    Example:
    Given binary tree [3, 9, 20, null, null, 15, 7],
            3
           / \
          9  20
            /  \
           15   7
    return its minimum depth = 2
 */
//  Recursion: Time = O(n) Space = O(n)
public class MinimumDepthofBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        // 没有左叶子节点，答案一定在右边
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        // 没有右叶子节点，答案一定在左边
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        // 左右子树都不为空，就可以照搬 104 的做法
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

/*  BFS: Time = O(n) Space = O(n)

        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
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

/*  Recursion 2 (more concise, less readable)

        if (root == null) return 0;

        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
        return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
 */
