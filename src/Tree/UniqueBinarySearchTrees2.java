package Tree;

/*  95. Unique Binary Search Trees 2
    Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

    Example:
    Input: 3
    Output:
    [
      [1,null,3,2],
      [3,2,null,1],
      [3,1,null,null,2],
      [2,1,3],
      [1,null,2,null,3]
    ]
    Explanation:
    The above output corresponds to the 5 unique BST's shown below:

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3

    Constraints:
        o 0 <= n <= 8
 */

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees2 {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    private List<TreeNode> helper(int min, int max) {
        List<TreeNode> res = new ArrayList<>();
        // 递归的出口
        if (min > max) return res;
        // 遍历所有的节点作为 root
        for (int rt = min; rt <= max; rt++) {
            // 左子树
            List<TreeNode> left = helper(min, rt - 1);
            // 右子树
            List<TreeNode> right = helper(rt + 1, max);

            if (left.size() == 0 && right.size() == 0) {
                TreeNode root = new TreeNode(rt);
                res.add(root);
            } else if (left.size() == 0) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(rt);
                    root.right = r;
                    res.add(root);
                }
            } else if (right.size() == 0) {
                for (TreeNode l : left) {
                    TreeNode root = new TreeNode(rt);
                    root.left = l;
                    res.add(root);
                }
            } else {
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(rt);
                        root.left = l;
                        root.right = r;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }
}

/*  LC

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = helper(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = helper(i + 1, end);

            // 从左子树集合中选出一颗左子树，从右子树集合中选出一颗右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = left;
                    curTree.right = right;
                    res.add(curTree);
                }
            }
        }
        return res;
    }
 */