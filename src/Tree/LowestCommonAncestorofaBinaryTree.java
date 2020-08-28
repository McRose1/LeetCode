package Tree;

/*  236. Lowest Common Ancestor of a Binary Tree
    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
    According to the definition of LCA on Wikipedia:"The lowest common ancestor is defined between two nodes
    p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself.)"

    Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4]
                    3
                   / \
                  5   1
                 / \ / \
                6  2 0  8
                  / \
                 7   4
    Example 1:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    Output: 3

    Example 2:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    Output: 5

    Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.
 */

/*  Recursion (DFS): Time = O(n) Space = O(n)
    两个节点在子树的 LCA 就是两个节点在整个树的 LCA
 */
public class LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归的出口
        if (root == null || root == p || root == q) {
            return root;
        }
        // look for LCA in both sub-trees
        TreeNode leftCommonAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightCommonAncestor = lowestCommonAncestor(root.right, p, q);

        // 左子树中没找到 LCA，那么一定在右子树
        if (leftCommonAncestor == null) {
            return rightCommonAncestor;
        }

        // 右子树中没找到 LCA，那么一定在左子树
        if (rightCommonAncestor == null) {
            return leftCommonAncestor;
        }

        // LCA 既不在左子树也不在右子树，说明出现在当前 root
        return root;
    }
}

/*  力扣
    (flson && frson) || ((x == p || x == q) && (flson || frson))

    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }
 */

/*  力扣：存储父节点 (Recursion)

    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
 */

/*  Iteration using parent pointers: Time = O(n) Space = O(n)
    可以从 p 和 q 回溯以获取它们的公共祖先，得到的第一个公共节点就是 LCA

        // Stack for tree traversal
        Stack<TreeNode> stack = new Stack<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            root = stack.pop();

            // While traversing the tree, keep saving the parent pointers
            if (root.left != null) {
                parent.put(root.left, root);
                stack.push(root.left);
            }
            if (root.right != null) {
                parent.put(root.right, root);
                stack.push(root.right);
            }
        }
        // Ancestors set for node p
        Set<TreeNode> ancestors = new HashSet<>();

        // 自下向上

        // Process all ancestors for node p using parent pointers
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        // The first ancestor of q which appears in p's ancestor set is their LCA
        // 如果 q 是 p 的 parents 路径中的 parent，直接返回 q
        // 否则，从 q 不断往上继续搜索 q 的 parent 是否和 p 的 parent 相同
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
 */

/*  Iterative without parent pointers

 */