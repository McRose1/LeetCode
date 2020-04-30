package Tree;

/*  124. Binary Tree Maximum Path Sum
    Given a non-empty binary tree, find the maximum path sum.
    For this problem, a path is defined as any sequence of nodes from some starting node to any node in
    the tree along the parent-child connections.
    The path must contain at least one node and does not need to go through the root.

    Example 1:
    Input: [1,2,3]
           1
          / \
         2   3
    Output: 6

    Example 2:
    Input: [-10,9,20,null,null,15,7]
       -10
       / \
      9  20
        /  \
       15   7
    Output: 42
 */
/*  Recursion: Time = O(n) Space = O(h)
    每一步都有两种选择：
    1. 继续当前路径
    2. 以当前节点作为最高节点计算新的路径
 */
public class BinaryTreeMaximumPathSum {
    int max_sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        max_gain(root);                             // max_gain(-10)
        return max_sum;
    }

    private int max_gain(TreeNode root) {           // -10; 9; 20; 15; 7
        if (root == null) return 0;

        // max sum on the left and right sun-trees of node
        int left_gain = Math.max(max_gain(root.left), 0);       // max_gain(9)->9; max_gain(15)->15
        int right_gain = Math.max(max_gain(root.right), 0);     // max_gain(20);   max_gain(7)->7; max_gain(20)->35

        // the price to start a new path where 'root' is a highest node
        int price_newpath = root.val + left_gain + right_gain;  // 9; 15; 7; 20+15+7=42; -10+9+35=34

        // update max_Sum if it's better to start a new path
        max_sum = Math.max(max_sum, price_newpath);             // max(MIN,9)=9; max(9,15)=15; max(15,7)=15; max(15,42)=42; max(42,34)=42

        // for recursion:
        // return the max gain if continue the same path
        return root.val + Math.max(left_gain, right_gain);      // 9+0=9; 15+0=15; 7+0=7; 20+15=35; -10+35=25
    }
}
