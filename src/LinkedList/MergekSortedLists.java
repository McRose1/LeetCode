package LinkedList;

/*  23. Merge k Sorted Lists
    Merge k sorted linked lists and return it as one sorted list.
    Analyze and describe its complexity.

    Example:
    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6
 */
import java.util.PriorityQueue;
/*  Compare using PriorityQueue: Time = O(nlogk) Space = O(k)
    use PriorityQueue to get the smallest number every time
    PQ(heap) can return the min/max number in O(logn) time
 */
public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (ListNode listHead : lists) {
            while (listHead != null) {
                minHeap.add(listHead.val);
                listHead = listHead.next;
            }
        }
        // Dummy node
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            cur.next = new ListNode(minHeap.poll());
            cur = cur.next;
        }
        return dummy.next;
    }
}

/*  PQ2

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Dummy node
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode listHead : lists) {
            if (listHead != null) {
                minHeap.add(listHead);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode popNode = minHeap.poll();
            cur.next = popNode;
            cur = cur.next;
            if (popNode.next != null) {
                minHeap.add(popNode.next);
            }
        }
        return dummy.next;
    }
 */

/*  Brute Force: Time = O(nlogn) Space = O(n)

        if (lists == null || lists.length == 0) return null;

        List<Integer> all_nodes = new ArrayList<>();
        for (ListNode node : lists) {
            while (node != null) {
                all_nodes.add(node.val);
                node = node.next;
            }
        }
        Collections.sort(all_nodes);
        // Dummy node
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (Integer node_val : all_nodes) {
            ListNode temp = new ListNode(node_val);
            cur.next = temp;
            cur = cur.next;
        }
        return dummy.next;
 */

/*  Compare one by one: Time = O(kn) Space = O(n)

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode temp = null;
        // temp is for checking whether all lists nodes are traversed.
        do {
            temp = findMinAndMove(lists);
            cur.next = temp;
            cur = cur.next;
        } while (temp != null);
        return dummy.next;
    }

    // Find the smallest node in all of linked list's head/pointer
    private ListNode findMinAndMove(ListNode[] lists) {
        int min_value = Integer.MAX_VALUE;
        int ret_idx = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            if (lists[i].val < min_value) {
                min_value = lists[i].val;
                ret_idx = i;
            }
        }
        ListNode ret_node = null;
        if (ret_idx != -1) {
            // add min
            ret_node = lists[ret_idx];
            // move
            lists[ret_idx] = lists[ret_idx].next;
        }
        return ret_node;
    }
 */