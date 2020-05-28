package Tree;

/*  94. Binary Tree Inorder Traversal
    Given an binary tree, return the inorder traversal of its nodes' values.

    Example:
    Input: [1,null,2,3]
        1
         \
         2
         /
        3
    Output: [1,3,2]
    Follow up: Recursive solution is trivial, could you do it iteratively?
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*  Iterating method using Stack: Time = O(n) Space = O(n)
    按照左->根->右的次序遍历二叉树，搜索左子树，存入当前点，搜索右子树
 */
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        // 节点不为空或者栈中还有节点，说明遍历还未完成
        while (root != null || !stack.isEmpty()) {
            // 节点不为空一直压栈
            while (root != null) {
                stack.push(root);
                root = root.left;   // 考虑左子树
            }
            // 节点为空，就出栈
            root = stack.pop();
            // 当前值加入
            res.add(root.val);
            // 考虑右子树
            root = root.right;
        }
        return res;
    }
}
/*  Recursion:
    Time = O(n): recursive function is T(n)=2*T(n/2)+1
    Space = O(n): the worst case space required is O(n), and in the average case it's O(logn)树的高度

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }
    public void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }
 */
/*  Iteration Version 2:

        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) {
                TreeNode node = curr.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
 */

/*  Morris Traversal: Time = O(n) Space = O(1)
    use a new data structure - Threaded Binary Tree
    Step 1: Initialize current as root
    Step 2: While current is not NULL,
    If current does not have left child
    a. Add current's value
    b. Go to the right, i.e., current = current.right
    Else
    a. In current's left subtree, make current the right child of the rightmost node
    b. Go to this left child, i.e., current = current.left

        List<Integer> res= new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;  // move to next right node
            } else {    // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr;   // put cur after the pre node
                TreeNode temp = curr;   // store cur node
                curr = curr.left;   // move cur to the top of the new tree
                temp.left = null;   // original cur left be null, avoid infinite loops
            }
              // while (pre.right != null && pre.right != curr) {
                      pre = pre.right;
                 }
                 if (pre.right == null) {
                      pre.right = curr;
                  curr = curr.left;
                 } else {
                      pre.right = null;
                      res.add(curr.val);
                      curr = curr.right;
                 }
               //
        }
        return res;
 */