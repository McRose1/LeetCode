package Tree;

/*  1110. Delete Nodes And Return Forest
    Given the root of a binary tree, each node in the tree has a distinct value.
    After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
    Return the roots of the trees in the remaining forest. You may return the result in any order.

    Example 1:
    Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
    Output: [[1,2,null,4],[6],[7]]

    Constraints:
    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//  DFS
public class DeleteNodesAndReturnsForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        HashSet<Integer> toDelete = new HashSet<>();
        for (int i : to_delete) {
            toDelete.add(i);
        }

        helper(root, toDelete, res);
        if (!toDelete.contains(root.val)) {
            res.add(root);
        }
        return res;
    }

    private TreeNode helper(TreeNode root, HashSet<Integer> toDelete, List<TreeNode> res) {
        if (root == null) {
            return null;
        }

        root.left = helper(root.left, toDelete, res);
        root.right = helper(root.right, toDelete, res);

        if (toDelete.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
            return null;
        }
        return root;
    }
}

/*  Iteration

        List<TreeNode> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        res.add(root);
        HashSet<Integer> toDelete = new HashSet<>();
        for (int d : to_delete) {
            toDelete.add(d);
        }

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (toDelete.contains(cur.val)) {
                res.remove(cur);
                if (cur.left != null) res.add(cur.left);
                if (cur.right != null) res.add(cur.right);
            }
            if (cur.left!= null) {
                queue.offer(cur.left);
                if (toDelete.contains(cur.left.val)) {
                    cur.left = null;
                }
            }
            if (cur.right!= null) {
                queue.offer(cur.right);
                if (toDelete.contains(cur.right.val)) {
                    cur.right = null;
                }
            }
        }
        return res;
 */