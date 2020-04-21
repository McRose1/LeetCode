package Tree;

/*  105. Construct Binary Search Tree from Preorder and Inorder Traversal
    Given preorder and inorder Traversal of a tree, construct the binary tree.
    Note:
    You may assume that duplicates do not exist in the tree.

    For example, given
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]

    Return the following binary tree:
        3
       / \
      9  20
        /  \
       15   7
 */
//  Recursion
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
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
}
