package LinkedList;

/*  160. Intersection of Two Linked Lists
    Write a program to find the node at which the intersection of two singly linked lists begins.
    For example, the following two linked lists:
    A: a1 -> a2
                 \
                  c1->c2->c3
                 /
    B: b1->b->b3
    begin to intersect at node c1.

    Example 1:
    A:      4 -> 1
                  \
                   8 -> 4 -> 5
                  /
    B: 5 -> 0 -> 1
    Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
    Output: Reference of the node with value = 8

    Example 2:
    A: 0 -> 9 -> 1
                  \
                   2 -> 4
                  /
    B:           3
    Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
    Output: Reference of the node with value = 2

    Example 3:
    A: 2 -> 6 -> 4
    B:      1 -> 5
    Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
    Output: null

    Note:
    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) times ad use only O(1) memory.
 */

/*  Two Pointers: Time = O(m + n) Space = O(1)
    A: a1->a2->c1->c2->c3->b1->b2->b3->c1->c2->c3
    B: b1->b2->b3->c1->c2->c3->a1->a2->c1->c2->c3
    When pA reaches the end of a list, then redirect it to the head of B;
    similarly when pB reaches the end of a list, redirect it the head of A.
    If at any point pA meets pB, then pA/pB is the intersection node.
 */
public class IntersectionofTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null? headA : b.next;
        }
        return a;
    }
}

/*  HashTable: Time = O(m + n) Space = O(m) or O(n)
    Traverse list A and store the address / reference to each node in a hash set.
    Then check every node bi in list B: if bi appears in the hash set, then bi is the intersection node.

        Set visited = new HashSet<>();
        while (headA != null) {
            visited.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (visited.contains(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }
        }
        return null;
 */
