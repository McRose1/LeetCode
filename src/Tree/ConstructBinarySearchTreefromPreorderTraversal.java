package Tree;

/*  1008. Construct Binary Search Tree from Preorder Traversal
    Return the root node of a binary search tree that matches the given preorder traversal.
    (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val,
    and any descendant of node.right has a value > node.val. Also recall that a preorder traversal displays the value of the node first,
    then traverses node.left, then traverses node.right.)

    Example 1:
    Input: [8,5,1,7,10,12]
    Output: [8,5,10,1,7,null,12]

    Note:
    1 <= preorder.length <= 100
    The values of preorder are distinct.
 */

import com.sun.source.tree.Tree;

import java.util.Arrays;

/*  Recursion: Time = O(n) Space = O(n)
    我们可以直接跳过生成 inorder 的步骤，直接根据 preorder 构造出二叉树（前提是必须是 BST，根据 preorder 构造出的二叉树才是唯一的）
    递归时维护一个（lower, upper）二元组，表示当前位置可以插入的节点的值的上下界。
    如果此时遍历到的值处于上下界中，就将这个值作为新的 node 插入当前位置，并递归地处理当前 node 的左右孩子。
    否则回溯到当前位置的 root
 */
public class ConstructBinarySearchTreefromPreorderTraversal {
    int idx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return dfs(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode dfs(int[] preorder, int lower, int upper) {
        // if all elements from preorder are used
        // then the tree is constructed
        if (idx == preorder.length) return null;

        int val = preorder[idx];
        // if the current element couldn't be placed here to meet BST requirements
        if (val < lower || val > upper) return null;

        // place the current element and recursively construct subtrees
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = dfs(preorder, lower, val);
        root.right = dfs(preorder, val, upper);
        return root;
    }
}

/*  Iteration using Stack: Time = O(n) Space = O(n)
    将栈顶的元素作为 root，当前遍历的元素作为 child。
    如果栈顶的元素小于 child 的值，则将栈顶的元素弹出并作为新的 root，直到栈空或者栈顶的元素大于 child 的值。
    注意，这里作为 root 的是最后一个被弹出栈的元素，而不是此时栈顶的元素

        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int n = preorder.length;

        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (int i = 1; i < n; i++) {
            // take the last element of the stack as a parent
            TreeNode node = stack.peek();
            // create a child from the next preorder element
            TreeNode child = new TreeNode(preorder[i]);
            // adjust the parent
            // 回溯考虑右子树
            while (!stack.isEmpty() && stack.peek().val < child.val) {
                node = stack.pop();
            }

            // follow BST logic to create a parent-child link
            if (node.val < child.val) {
                node.right = child;
            } else {
                node.left = child;
            }
            // add the child into stack
            stack.push(child);
        }
        return root;
 */

/*  Based on LC 105: Time = O(nlogn) Space = O(n)
    根据 binary tree 的 preorder 和 inorder，可以唯一确定并构造出这个 binary tree
    如果是 binary search tree，那么它的 inorder 就是树中所有元素的值的升序排序

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        return dfs(preorder, inorder, 0, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int pre_st, int in_st, int in_end) {
        if (pre_st > preorder.length - 1 || in_st > in_end)  {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre_st]);     // 3
        int i = in_st;                                      // i = 0
        while (i <= in_end) {
            if (inorder[i] == preorder[pre_st]) {           // i = 1
                break;
            }
            i++;
        }
        root.left = dfs(preorder, inorder, pre_st + 1, in_st, i - 1); // (1,0,0)
        root.right = dfs(preorder, inorder, pre_st + 1 + (i - in_st), i + 1, in_end); // (2,2,4)

        return root;
    }
 */
