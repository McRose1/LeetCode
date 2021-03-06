package Tree;

/*  297. Serialize and Deserialize Binary Tree
    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
    or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
    You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

    Example:
    You may serialize the following tree:

        1
       / \
      2   3
         / \
        4   5

    as "[1,2,3,null,null,4,5]"

    Note:Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
//  DFS + Pre-order + Recursion
public class SerializeandDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    // Pre-order Traversal
    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            // null 节点用 # 标记
            sb.append("#").append(",");
        } else {
            // pre-order
            sb.append(root.val).append(",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] strArr = data.split(",");
        Queue<String> queue = new LinkedList<>();
        // 把 string 里的 node 存入 queue
        Collections.addAll(queue, strArr);
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        if (queue.isEmpty()) return null;
        String s = queue.poll();
        if (s.equals("#")) return null;
        // to match serialize pre-order traversal
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }
}

/*  BFS + Iteration

    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("#");
            } else {
                sb.append(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] arr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.offer(root);
        for (int i = 1; i < arr.length; i++) {
            TreeNode parent = queue.poll();
            if (!arr[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(arr[i]));
                parent.left = left;
                queue.offer(left);
            }
            if (!arr[++i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(arr[i]));
                parent.right = right;
                queue.offer(right);
            }
        }
        return root;
    }
 */