package Tree;

/*  145. Binary Tree Postorder Traversal
    Given an binary tree, return the postorder traversal of its nodes' values.

    Example:
    Input: [1,null,2,3]
        1
         \
          2
         /
        3
    Output: [3,2,1]

    Follow up: Recursive solution is trivial, could you do it iteratively?
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
/*  Iteration: Time = O(n) Space = O(n)
    Postorder: left->right->root
    转换问题，先做（root->right->left）前序遍历，再 reverse
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                res.add(curr.val);
                stack.push(curr);
                curr = curr.right;   // 考虑右子树
            } else {
                // 节点为空，就出栈
                curr = stack.pop();
                // 考虑左子树
                curr = curr.left;
            }
        }
        Collections.reverse(res);
        return res;
    }
}

/*  Recursion: Time = O(n) Space = O(n)

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }
 */

/*  Iteration: Time = O(n) Space = O(n)
    首先对左子树进行遍历压入栈中，直至左子树为空，然后访问右子树。故每个节点会被访问两次，当节点被第二次访问时，该节点出栈
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(0, curr.val);   // add element to the left most node
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return res;
 */

/*  Morris Traversal

        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.right == null) {
                list.add(cur.val);
                cur = cur.left;
            } else {
                TreeNode pre = cur.right;
                while (pre.left != null && pre.left != cur) {
                    pre = pre.left;
                }
                if (pre.left == null) {
                    list.add(cur.val);
                    pre.left = cur;
                    cur = cur.right;
                }
                if (pre.left == cur) {
                    pre.left = null; // 这里可以恢复为 null
                    cur = cur.left;
                }
            }
        }
        Collections.reverse(list);
        return list;
 */
