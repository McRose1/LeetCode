package LinkedList;

/*  237. Delete Node in a Linked List
    Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
    Given linked list -- head = [4,5,1,9], which looks like following:
    4 -> 5 -> 1 -> 9

    Example 1:
    Input: head = [4,5,1,9], node = 5
    Output: [4,1,9]

    Example 2:
    Input: head = [4,5,1,9], node = 1
    Output: [4,5,9]

    Note:
    The linked list will have at least two elements.
    All of the nodes' values will be unique.
    The given node will not be the tail and it will always be a valid node of the linked list.
    Do not return anything from your function.
 */

/*  Swap with Next Node: Time = O(1) Space = O(1)
    The usual way of deleting a node from a linked list is to
    modify the next pointer of the node before it, to point to the node after it.
    Since we do not have access to the node before the one we want to delete,
    we cannot modify the next pointer to the next pointer of that node in any way.
    Instead, we have to replace the value of the node we want to delete with the value in the node after it,
    and delete the node after it.
 */
public class DeleteNodeinaLinkedList {
    public void deleteNode(ListNode node) { // node = 5
        node.val = node.next.val;           // node.val = 1 --> 4->1->1->9
        node.next = node.next.next;         // 1->9
    }
}
