package Tree;

/*  144. Binary Tree Preorder Traversal
    Given an binary tree, return the preorder traversal of its nodes' values.

    Example:
    Input: [1,null,2,3]
        1
         \
          2
         /
        3
    Output: [1,2,3]

    Follow up: Recursive solution is trivial, could you do it iteratively?
 */

import java.util.ArrayList;
import java.util.List;
//  Recursion: Time = O(n) Space = O(n)
public class BinaryTreePreorderTravesal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
/*  Iteration: Time = O(n) Space = O(n)

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                res.add(curr.val);
                curr = curr.left;   // 考虑左子树
            } else {
            // 节点为空，就出栈
            curr = stack.pop();
            // 考虑右子树
            curr = curr.right;
            }
        }
        return res;

    // version 2: 将左右子树分别压栈，然后每次从栈里取元素。因为栈是先进后出，所有我们先压右子树。
    // 首先存入当前节点值，然后先将右儿子压入栈中，再将左儿子压入栈中。对栈中元素遍历访问。
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            Treenode node = stack.pop();
            res.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
 */

/*  Morris Traversal: Time = O(n) Space = O(1)

        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            //情况 1
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                //找左子树最右边的节点
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                //情况 2.1
                if (pre.right == null) {
                    list.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                }
                //情况 2.2
                if (pre.right == cur) {
                    pre.right = null; //这里可以恢复为 null
                    cur = cur.right;
                }
            }
        }
        return list;
 */