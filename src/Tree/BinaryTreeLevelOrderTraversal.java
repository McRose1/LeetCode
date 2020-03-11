package Tree;

/*  102. Binary Tree Order Traversal
    Given an binary tree, return the level order traversal of its nodes' values.
    (i.e., from left to right, level by level).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
 */

/*  BFS: Time = O(n) Space
    二叉树的层次遍历通常实现方式为使用队列不断压入节点遍历，每次取出队列首个元素遍历左右子节点，继续压入子节点即可。
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                currentLevel.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            res.add(currentLevel);
        }
        return res;
    }
}

/*  DFS: Time = O(n) Space = O(n)
    相当于pre-order遍历，一层一层向下走，level==res.size()的时候初始化一下内层的列表
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }
    public static void helper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        // 当前层数还没有元素，先 new 一个空的列表
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        helper(res, root.left, level + 1);
        helper(res, root.right, level + 1);
    }
 */
