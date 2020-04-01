package LinkedList;

/*  138. Copy List with Random Pointer
    A linked list is given such that each node contains an additional random pointer
    which could point to any node in the list or null.

    Return a deep copy of the list.

    The Linked List is represented in the input/output as a list of n nodes.
    Each node is represented as a pair of [val, random_index] where:
    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) where random pointer points to,
    or null if it does not point to any node.

    Example 1:
    Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

    Example 2:
    Input: head = [[1,1],[2,1]]
    Output: [[1,1],[2,1]]

    Example 3:
    Input: head = [[3,null],[3,0],[3,null]]
    Output: [[3,null],[3,0],[3,null]]

    Example 4:
    Input: head = []
    Output: []
    Explanation: Given linked list is empty (null pointer), so return null.

    Constraints:
    -10000 <= Node.val <= 10000
    Node.random is null or pointing to a node in the linked list.
    Number of Nodes will not exceed 1000.
 */
import java.util.HashMap;
import java.util.Map;

/*  HashMap: Time = O(n) Space = O(n)
    模仿 clone graph 的解题思路，遍历两次链表
 */
public class CopyListwithRandomPointer {
    public Node copyRandomListNode(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();

        // loop1. copy all the nodes
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        // loop2. assign next and random pointers
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}

/*  HashMap: Time = O(n) Space = O(n)
    遍历一次链表
    Building mapping between node in the original list and the corresponding node in the new list.
    Two pointers:
    Head -> current list
    Cur -> new list
    HashMap:
    key: original node
    value: new node

    Steps:
    1. 检查当前 node 是否已经被 copy 过，如果没有，先 copy 一份当前的 node，存入 hashmap
    2. 将 copy 的 node 加入到 new list 中，即 copy next pointer
    3. copy 当前 node 的 random pointer
    4. 对于 original list 中的每个节点都做以上这 3 个操作

        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();

        // Sentinel node to help construct the deep copy
        // new list head
        Node dummy = new Node(0);
        // new list head (searching)
        Node cur = dummy;

        while (head != null) {
            // 判断当前 node 是否已经产生过，因为在算 random 的时候可能已经 copy 过
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }
            // Connect the copied node to the deep copy list.
            cur.next = map.get(head);
            // Copy the random node if necessary.
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }
                // Connect the copied node to the random pointer.
                cur.next.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
 */

/*  No HashMap: Time = O(n) Space = O(1)
    Steps:
    1. For each node in the original list, insert a copied node between the node and the node.next
    2. Link the random pointer for the copied node
    3. Extract te copied node (copy next pointer)

        if (head == null) return null;
        // First pass, for each node in the original list, insert a copied node between the node and node.next.
        Node cur = head;
        while (cur != null) {
            // Make a copy of cur node, insert it to the middle of cur and cur.next.
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = cur.next.next;
        }
        // Second pass, link the random pointer for the copied node.
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // Third pass, extract the copied node
        cur = head;
        Node dummy = new Node(0);
        // searching pointer
        Node copyPrev = dummy;
        while (cur != null) {
            copyPrev.next = cur.next;
            cur.next = cur.next.next;
            copyPrev = copyPrev.next;
            cur = cur.next;
        }
        return dummy.next;
 */
