package Tree;

/*  230. Kth Smallest Element in a BST
    Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

    Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

    Example 1:
    Input: root = [3,1,4,null,2], k = 1
            3
          /   \
         1     4
          \
           2
    Output: 1

    Example 2:
    Input: root = [5,3,6,2,4,null,null,1], k = 3
             5
           /   \
          3     6
        /   \
       2     4
      /
     1
    Output: 3

    Follow up:
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
    How would you optimize the kthSmallest routine?
    Answer: BST + LinkedList
    The overall time complexity for insert/delete + search of kth smallest is O(H + k) instead of O(2H + k)

    Hint:
    Try to utilize the property of BST.
    Try in-order traversal.
    What if you could modify the BST node's structure?
    The optimal runtime complexity is O(height of BST).
 */
import java.util.Stack;

/*  Iteration + Stack: Time = O(H + k), H is a tree Height Space = O(H + k)
    This complexity is defined by the stack, which contains at least H + k elements,
    since before starting to pop out one has to go down to a leaf.
    This result in O(logN + k) for the balanced tree and O(N + k) for completely unbalanced tree with all the nodes in the left subtree.

    Iteration could speed up the solution because there is no need to build the entire inorder traversal
    Because it could stop after the kth element.
 */
public class KthSmallestElementinaBST {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int res = -1;
        int count = 0;

        // 当前遍历 node 不为空或者 stack 还有 root 没有被 pop 出来
        while (root != null || !stack.isEmpty()) {
            // 左子树节点不为空就一直压栈
            while (root != null) {
                stack.push(root);
                // 先找左子树，因为 BST 的特性是左根右升序
                root = root.left;
            }
            // 一直到向下压栈到 leaf node，开始向上出栈
            root = stack.pop();
            count++;
            // 剪枝
            if (count == k) {
                res = root.val;
                break;
            }
            // 考虑右子树
            root = root.right;
        }
        return res;
    }
}

/*  Recursion（剪枝）: Time = O(H + k) Space = O(H + k)

    int count = 0;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    private void inorder(TreeNode root, int k) {
        if (root == null) return;
        inorder(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        inorder(root.right, k);
    }
 */

/*  LC: Recursion + inOrder: Time = O(n) Space = O(n)

    public int kthSmallest(TreeNode root, int k) {
        // build an inorder traversal of BST which is an array sorted in the ascending order
        // [3,1,4,null,2] -> [1,2,3,4]
        List<Integer> nums = inorder(root, new ArrayList<>());
        // kth smallest is the kth element in the list, whose index is k - 1
        return nums.get(k - 1);
    }

    public List<Integer> inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return list;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
        return list;
    }
*/