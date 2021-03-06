/*
    Given an n-ary tree, return the preorder traversal of its nodes' values.
    Nary-Tree input serialization is represented in their level order traversal,
    each group of children is separated by the null value (See examples).

    Follow up: Recursive solution is trivial, could you do it iteratively.

    Example 1:
                 1
               / | \
              3  2  4
             / \
            5   6
    Input: root = [1,null,3,2,4,null,5,6]
    Output: [1,3,5,6,2,4]

    Example 2:
                            1
                     /   |    |   \
                    2    3    4    5
                        / \   |   / \
                       6   7  8  9  10
                           |  |  |
                           11 12 13
                           |
                           14
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]

    Constraints:
    The height of the n-ary tree is less than or equal to 1000
    The total number of nodes is between [0, 10^4]
*/
// Recursive
import java.util.ArrayList;
import java.util.List;

public class N_aryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }

    public void helper(List<Integer> res, Node root) {
        if (root == null) return;
        res.add(root.val);
        List<Node> childs = root.children;
        for (int i = 0; i < childs.size(); i++) {
            helper(res, childs.get(i));
        }
    }
}
/*
    Iterative

        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.empty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i--)
                stack.add(root.children.get(i));
        }

        return list;
 */
