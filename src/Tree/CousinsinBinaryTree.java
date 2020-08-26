package Tree;

/*  993. Cousins in Binary Tree
    In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
    Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
    We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
    Return true if and only if the nodes corresponding to the values x and y are cousins.

    Example 1:
    Input: root = [1,2,3,4], x = 4, y = 3
    Output: false

    Example 2:
    Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
    Output: true

    Example 3:
    Input: root = [1,2,3,null,4], x = 2, y = 3
    Output: false

    Constraints:
        o The number of nodes in the tree will be between 2 and 100.
        o Each node has a unique integer value from 1 to 100.
 */

import java.util.LinkedList;
import java.util.Queue;

/*  BFS

 */
public class CousinsinBinaryTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int x_parent;
        int y_parent;
        while (!queue.isEmpty()) {
            int size = queue.size();
            x_parent = -1;
            y_parent = -1;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (node.left.val == x) {
                        x_parent = node.val;
                    } else if (node.left.val == y) {
                        y_parent = node.val;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        x_parent = node.val;
                    } else if (node.right.val == y) {
                        y_parent = node.val;
                    }
                    queue.offer(node.right);
                }
            }
            if (x_parent != - 1 && y_parent != -1 && x_parent != y_parent) {
                return true;
            }
        }
        return false;
    }
}

/*  DFS: Time = O(n) Space = O(n)

    HashMap<Integer, Integer> depth;
    HashMap<Integer, TreeNode> parent;

    public boolean isCousins(TreeNode root, int x, int y) {
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
 */

/*  my version

        int[] X = new int[2];
        boolean xFound = false;
        int[] Y = new int[2];
        boolean yFound = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty() && !xFound && !yFound) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (node.left.val == x) {
                        xFound = true;
                        X[0] = node.val;
                        X[1] = depth;
                    } else if (node.left.val == y) {
                        yFound = true;
                        Y[0] = node.val;
                        Y[1] = depth;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        xFound = true;
                        X[0] = node.val;
                        X[1] = depth;
                    } else if (node.right.val == y) {
                        yFound = true;
                        Y[0] = node.val;
                        Y[1] = depth;
                    }
                    queue.offer(node.right);
                }
            }
        }
        return X[0] != Y[0] && X[1] == Y[1];
 */