package Design;

import java.util.HashMap;
import java.util.Map;

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
//  HashMap + Doubly LinkedList: Time = O(1)
public class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        // 构造函数
        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }
    Node dummyHead;
    Node dummyTail;
    // 记住 map 存的 value 是 Node
    Map<Integer, Node> map;
    int size;
    int capacity;

    public LRUCache(int capacity) {
        dummyHead = new Node(0, 0);
        dummyTail = new Node(0, 0);
        // 初始化 Doubly LinkedList
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

        map = new HashMap<>();
        size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            // hit 就把该 node 移到双向链表的头部
            Node node = map.get(key);
            remove(key);
            addHead(key, node.val);
            return node.val;
        }
    }

    public void put(int key, int value) {
        // 修改原有的值，在这里体现为先删去 node，在把带有新 value 的 node 加到头部
        if (map.containsKey(key)) {
            remove(key);
            addHead(key, value);
        // 直接在头部加一个新的 node
        } else {
            addHead(key, value);
        }
    }
    // remove from tail
    private void remove(int key) {
        Node cur = map.get(key);
        Node next = cur.next;
        Node prev = cur.prev;       // prev <-> cur <-> next

        prev.next = next;           // prev -> next
        next.prev = prev;           // prev <- next
        size--;
        map.remove(key);
    }

    private void addHead(int key, int val) {
        Node node = new Node(key, val);
        Node next = dummyHead.next;         // next -> head
        dummyHead.next = node;              // dummyHead -> node
        node.next = next;                   // dummyHead -> node -> next
        next.prev = node;                   // dummyHead -> node <-> next
        node.prev = dummyHead;              // dummyHead <-> node <-> next
        map.put(key, node);
        size++;
        if (size > capacity) {
            // dummyHead 就用在这儿，尾部 node 表示最长时间没更新的 node
            Node preTail = dummyTail.prev;
            remove(preTail.key);
        }
    }
}
