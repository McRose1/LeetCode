package Design;

/*  146. LRU Cache
    Design and implement a data structure for Least Recently Used (LRU) cache.
    It should support the following operations: get and put.
    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    put(key, value) - Set or insert the value if the key is not already present.
    When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
    The cache is initialized with a positive capacity.

    Follow up: Could you do both operations in O(1) time complexity?

    Example:
    LRUCache cache = new LRUCache(2);

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);        // returns 1
    cache.put(3, 3);     // evicts key 2
    cache.get(2);        // returns -1 (not found)
    cache.put(4, 4);     // evicts key 1
    cache.get(1);        // returns -1 (not found)
    cache.get(3);        // returns 3
    cache.get(4);        // returns 4
 */
import java.util.HashMap;
import java.util.Map;
/*  HashMap + Doubly LinkedList: Time = O(1) Space = O(capacity)
    获取键/检查键是否存在 -> HashMap O(1)
    设置键 -> HashMap O(1)
    删除最先插入的键 -> Doubly LinkedList O(1)
 */
public class LRUCache {
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
    }

    DLinkedNode dummyHead;
    DLinkedNode dummyTail;
    // 记住 map 存的 value 是 Node
    Map<Integer, DLinkedNode> cache = new HashMap<>();
    int size;
    int capacity;

    // Always add the new node to the head of doubly LinkedList.
    private void addNode(DLinkedNode node) {
        // dummyHead <- node
        node.prev = dummyHead;
        // node -> formerHead
        node.next = dummyHead.next;

        // node <- formerHead
        dummyHead.next.prev = node;
        // dummyHead -> node
        dummyHead.next = node;
    }

    // Remove an existing node form the tail of doubly LinkedList.
    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // Move certain node in between to the head of doubly LikedList.
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    // Pop the current tail
    private DLinkedNode popTail() {
        DLinkedNode res = dummyTail.prev;
        removeNode(res);
        return res;
    }

    public LRUCache(int capacity) {
        dummyHead = new DLinkedNode();
        dummyTail = new DLinkedNode();
        // 初始化 Doubly LinkedList
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

        size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        } else {
            DLinkedNode node = cache.get(key);
            // hit 就把该 node 移到双向链表的头部
            moveToHead(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        // 修改原有的值，再把带有新 value 的 node 移到头部
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.val = value;
            moveToHead(node);
        // 直接在头部加一个新的 node
        } else {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.val = value;

            cache.put(key, newNode);
            addNode(newNode);

            size++;
            if (size > capacity) {
                // pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        }
    }
}
