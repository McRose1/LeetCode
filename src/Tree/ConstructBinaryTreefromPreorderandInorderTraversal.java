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

//  DFS + Recursion: Time = O(n) Space = O(n)
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return dfs(preorder, inorder, 0, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int preLeft, int inLeft, int inRight) {
        if (preLeft > preorder.length - 1 || inLeft > inRight)  {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);     // 3

        // 从 inorder 中寻找当前 root 的 index
        int rootIndex = inLeft;                                      // i = 0
        // 利用 while 循环不断尝试，可以使用 HashMap 优化
        while (rootIndex <= inRight) {
            // 找到当前根结点
            if (inorder[rootIndex] == preorder[preLeft]) {           // i = 1
                break;
            }
            rootIndex++;
        }
        // 左子树：preLeft 前移一位，inLeft 不变，inRight 变为 rootIndex - 1
        root.left = dfs(preorder, inorder, preLeft + 1, inLeft, rootIndex - 1); // (1,0,0)
        // 右子树：(rootIndex - inLeft) 为左子树的结点个数，preLeft 此时加上该个数再加 1, inLeft 变为 rootIndex + 1, inRight 不变
        root.right = dfs(preorder, inorder, preLeft + (rootIndex - inLeft) + 1, rootIndex + 1, inRight); // (2,2,4)

        return root;
    }
}

/*  Recursion LC (HashMap)

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        // 题目前提没有重复的元素，所以结点的值可以和它在中序遍历序列的坐标一一对应
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, map, 0, inorder.length - 1);
    }

    *
    @param preorder 前序遍历序列
    @param preLeft  前序遍历序列子区间的左边界
    @param preRight 前序遍历序列子区间的右边界
    @param map      在中序遍历序列里，数值与下标的对应关系
    @param inLeft   中序遍历序列子区间的左边界
    @param inRight  中序遍历序列子区间的右边界
    *
    private TreeNode dfs(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> map, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);

        root.left = dfs(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1);
        root.right = dfs(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
        return root;
    }
 */

/*  DFS + Iteration: Time = O(n) Space = O(n)

        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
 */