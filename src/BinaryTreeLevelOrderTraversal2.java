/*
    Given a binary tree, return the bottom-up level order traversal of its nodes' values.
    (i.e. from left to right, level by level from leaf to root).

    For example:
    Given binary tree [3, 9, 20, null, null, 15, 7]
            3
           / \
          9  20
             / \
            15  7
    return its bottom-up level order traversal as:
    [
        [15, 7],
        [9, 20],
        [3]
    ]
 */

import java.util.*;

public class BinaryTreeLevelOrderTraversal2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();    // List res 里面存的是：存整型的 List
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();     // Queue 存每一层的 TreeNode
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                list.add(cur.val);                      // // List list 里面存每一层的 List
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }
}
