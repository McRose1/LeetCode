package Tree;

/*  108. Convert Sorted Array to Binary Search Tree
    Given an array where elements are sorted in ascending order, covert it to a height balanced BST.
    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
    of the two subtrees of every node never differ by more than 1
    Example:
    Given the sorted array: [-10, -3, 0, 5, 9],
    One possible answer is: [0, -3, 9, -10, null, 5], which represents the following height balanced BST:
            0
           / \
         -3   9
         /    /
       -10   5
 */
//  Recursion: Time = O(n) Space = O(n)->O(logn)
public class CovertedSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return constructBST(nums, 0, nums.length - 1);
    }
    private TreeNode constructBST(int[] nums, int left, int right) {
        // 递归出口
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = constructBST(nums, left, mid - 1);
        cur.right = constructBST(nums, mid + 1, right);
        // 把 TreeNode 连接起来
        return cur;
    }
}
