package Tree;

/*  257. Binary Tree Paths
    Given a binary tree, return all root-to-leaf paths.

    Example:
    Input:
            1
           / \
          2   3
           \
            5
    Output: ["1->2->5", "1->3"]
 */

import java.util.ArrayList;
import java.util.List;
//  DFS
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        dfs(root, "", res);
        return res;
    }

    public void dfs(TreeNode root, String path, List<String> res) {
        path += root.val;
        // 递归出口，到达 leaf node
        if (root.left == null && root.right == null) {
            res.add(path);
            return;
        }
        if (root.left != null) {
            dfs(root.left, path + "->", res);
        }
        if (root.right != null) {
            dfs(root.right, path + "->", res);
        }
    }
}
