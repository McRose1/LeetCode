package Tree;

/*  Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
    Given a binary tree where each path going from the root to any leaf from a valid sequence,
    check if a given string is a valid sequence in such binary tree.
    We get the given string from the concatenation of an array of integers arr and the concatenation of all
    values of the nodes along a path results in a sequence in the given binary tree.

    Example 1:
    Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
    Output: true
    Explanation:
    The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
    Other valid sequences are:
    0 -> 1 -> 1 -> 0
    0 -> 0 -> 0

    Example 2:
    Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
    Output: false
    Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.

    Example 3:
    Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
    Output: false
    Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.

    Constraints:
    1 <= arr.length <= 5000
    0 <= arr[i] <= 9
    Each node's value is between [0 - 9].

    Hint:
    1. DFS with the parameters: current node in the binary tree and current position in the array of integers.
    2. When reaching at final position check if it is a leaf node.
 */

//  DFS
public class CheckIfaStringIsaValidSequencefromRoottoLeavesPathinaBinaryTree {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    private boolean dfs(TreeNode root, int[] a, int depth) {
        if (root == null || depth >= a.length || a[depth] != root.val) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return depth + 1 == a.length;
        }
        return dfs(root.left, a, depth + 1) || dfs(root.right, a, depth + 1);
    }
}

/*  BFS

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // search depth level control
        for (int depth = 0; !queue.isEmpty() && depth < arr.length; depth++) {
            // search breadth control
            for (int size = queue.size(); size > 0; size--) {
                TreeNode cur = queue.poll();
                if (cur != null && cur.val == arr[depth]) {
                    if (depth + 1 == arr.length && cur.left == null && cur.right == null) {
                        return true;
                    }
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        return false;
 */

/*  my version（丑陋版）

    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null) return false;
        boolean left;
        boolean right;

        if (arr.length == 1) {
            if (root.left == null && root.right == null) {
                return true;
            } else {
                return false;
            }
        }

        if (root.val != arr[0]) {
            return false;
        } else {
            left = isValid(root.left, arr, 1);
            right = isValid(root.right, arr, 1);
        }
        return (left || right);
    }

    private boolean isValid(TreeNode root, int[] arr, int index) {

        if (index == arr.length - 1) {
            if (root == null) return false;
             else if (root.val == arr[index] && root.left == null && root.right == null) {
                return true;
            } else {
                return false;
            }
        }
        if (root == null) return false;
        if (root.val != arr[index]) {
            return false;
        } else {
            return isValid(root.left, arr, index + 1) || isValid(root.right, arr, index + 1);
        }
    }
 */
