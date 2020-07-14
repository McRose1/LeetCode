package Tree;

/*  226. Invert Binary Tree
    Invert a binary tree.

    Example:
    Input:
            4
           / \
          2   7
         / \ / \
        1  3 6  9
    Output:
            4
           / \
          7   2
         / \ / \
        9  6 3  1
 */

// Recursion: Time = O(n) Space = O(n)
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

/*  Iteration: Time = O(n) Space = O(n)
    Using Queue similar to BFS

        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            // 加到 queue 之前需要先判断是否不为 null，否则会影响后续 cur = queue.poll() -> 会出现 cur = null 的情况，导致 cur.left 空指针异常
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return root;
 */

/*  Iteration: Time = O(n) Space = O(n)
    Using Stack similar to DFS

        if (root == null) return null;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
               stack.push(cur.left);
           }
        }
        return root;
 */
