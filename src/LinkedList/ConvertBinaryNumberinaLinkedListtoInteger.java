package LinkedList;

/*  1290. Convert Binary Number in a Linked List to Integer
    Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1.
    The linked list holds the binary representation of a number.
    Return the decimal value of the number in the linked list.

    Example 1:
    Input: head = [1,0,1]
    Output: 5
    Explanation: (101) in base 2 = (5) in base 10

    Example 2:
    Input: head = [0]
    Output: 0

    Example 3:
    Input: head = [1]
    Output: 1

    Example 4:
    Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
    Output: 18880

    Example 5:
    Input: head = [0,0]
    Output: 0

    Constraints:
        o The Linked List is not empty.
        o Number of nodes will not exceed 30.
        o Each node's value is either 0 or 1.

    Hint:
    1. Traverse the linked list and store all values in a string or array. convert the values obtained to decimal value.
    2. You can solve the problem in O(1) memory using bits operation. use shift left operation (<<) and or operation (|) to get the decimal value in one operation.
 */

/*  Simulation

 */
public class ConvertBinaryNumberinaLinkedListtoInteger {
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }
}

/*  Bit Manipulation（和上面一样，表示方法不同）
    1 -> 0 -> 1:
    - 1 << 1 -> 10
    - 10 << 1 -> 100; 100 | 1(001) -> 101

        int res = 0;
        while (head != null) {
            res = res << 1 | head.val;
            head = head.next;
        }
        return res;
 */

/*  Brute Force

        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        sb.reverse();
        int res = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) - '0' == 1) {
                int times = i;
                if (times == 0) res += 1;
                else {
                    int mul = 1;
                    while (times-- > 0) {
                        mul *= 2;
                    }
                    res += mul;
                }
            }
        }
        return res;
 */

