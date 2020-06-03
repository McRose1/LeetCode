package Tree;

/*  117. Populating Next Right Pointers in Each Node 2
    Given a binary tree
    struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
    }
    Populate each next pointer to point to its next right node.
    If there is no next right node, the next pointer should be set to NULL.
    Initially, all next pointers are set to NULL.

    Follow up:
    You may only use constant extra space.
    Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

    Example:
    Input: root = [1,2,3,4,5,null,7]
    Output: [1,#,2,3,#,4,5,7,#]
    Explanation: Given the above binary tree (Figure A),
    your function should populate each next pointer to point to its next right node, just like in Figure B.
    The serialized output is in level order as connected by the next pointers,
    with '#' signifying the end of each level.

    Constraints:
    The number of nodes in the given tree is less than 6000.
    -1000 <= node.val <= 1000
 */

/*  BFS: Time = O(n) Space = O(1)
    Level by level using next pointer
    *leftmost: 每一层的最左节点。找到每层的最左节点作为链表首部，然后从该节点开始访问该层的所有节点。
    *cur：用来遍历当前层的所有节点。从该层的最左节点一直移动到该层最后一个节点。
    *prev：指向下一层的节点。每次更新 cur 指针时，需要令 prev.next = cur.left，同时更新 prev，有 4 种情况：
    1. prev 指针初始化。开始时 prev 指针为空，找到下一层的最左节点时，将该节点赋值给 prev 指针。
    2. 当前节点没有左子节点，将 prev 指向当前节点的右子节点。
    3. 下一个节点没有孩子节点。此时不需要更新 prev 指针。
    4. 下一个节点同时拥有左子节点和右子节点。首先将 prev 指向左子节点。在完成必要处理之后，令 prev 指针指向右子节点。
 */
public class PopulatingNextRightPointersinEachNode2 {
    public Node connect(Node root) {
        // 每层的最左结点
        Node leftmost = null;
        // 保存还未找到 next 的节点，找到非空节点以后可以进行连接
        Node prev = null;
        // 当前层节点
        Node cur = root;
        while (cur != null) {
            // 遍历 cur 当前层
            while (cur != null) {           // cur=1; 2; 3
                // 当前节点的左子节点
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        leftmost = cur.left;      // head=2; 4
                    }
                    prev = cur.left;            // pre=2; 4
                }
                // 当前节点的右子节点
                if (cur.right != null) {
                    if (prev != null) {          // 2; 4; 5
                        prev.next = cur.right;   // 2->3; 4->5; 5->7
                    } else {
                        leftmost = cur.right;
                    }
                    prev = cur.right;            // pre=3; 5; 7
                }
                cur = cur.next;                 // cur=null; 3; null
            }
            // 更新 cur 到下一层
            cur = leftmost;                         // cur=2; 4
            prev = null;
            leftmost = null;
        }
        return root;
    }
}

/*  BFS (Using queue): Time = O(n) Space = O(n)

        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.size() > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (i < size - 1) {
                    cur.next = queue.peek();
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
 */