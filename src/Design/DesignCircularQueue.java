package Design;

/*  622. Design Circular Queue
    Design your implementation of the circular queue.
    The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and
    the last position is connected back to the first position to make a circle.
    It is also called "Ring Buffer".

    One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
    In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue.
    But using the circular queue, we can use the space to store new values.

    Your implementation should support following operations:
    o MyCircularQueue(k): Constructor, set the size of the queue to be k.
    o Front(): Gets the front item from the queue. If the queue is empty, return -1.
    o Rear(): Gets the last item from the queue. If the queue is empty, return -1.
    o enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
    o deQueue(value): Delete an element into the circular queue. Return true if the operation is successful.
    o isEmpty(): Checks whether the circular queue is empty or not.
    o isFull(): Checks whether the circular queue is full or not.

    Example:
    MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
    circularQueue.enQueue(1);  // return true
    circularQueue.enQueue(2);  // return true
    circularQueue.enQueue(3);  // return true
    circularQueue.enQueue(4);  // return false, the queue is full
    circularQueue.Rear();  // return 3
    circularQueue.isFull();  // return true
    circularQueue.deQueue();  // return true
    circularQueue.enQueue(4);  // return true
    circularQueue.Rear();  // return 4

    Note:
    All values will be in the range of [0, 1000].
    The number of operations will be in the range of [1, 1000].
    Please do not use the built-in Deque library.
 */

/*  Array: Time = O(1) Space = O(N)
    任何数据结构中都不存在环形结构，但是可以使用一维数组模拟，通过操作数组的索引构建一个虚拟的环。
    对于一个固定大小的数组，任何位置都可以是队首，只要知道队列长度，就可以根据下面公式计算出队尾位置：
        tailIndex = (headIndex + count - 1) mod capacity
    其中 capacity 是数组长度，count 是队列长度，headIndex 和 tailIndex 分别是队首 head 和队尾 tail 索引
 */
public class DesignCircularQueue {

    private int[] queue;
    private int headIndex;
    private int count;
    private int capacity;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public DesignCircularQueue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (this.count == this.capacity) {
            return false;
        }
        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (this.count == 0) {
            return false;
        }
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count -= 1;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (this.count == 0) {
            return -1;
        }
        return this.queue[this.headIndex];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (this.count == 0) {
            return -1;
        }
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (this.count == this.capacity);
    }
}

/*  Singly List: Time = O(1) Space = O(N)
    与固定大小的数组相比，单链表不会为未使用的容量预分配内存，因此它的内存效率更高。

class Node {
    public int value;
    public Node nextNode;

    public Node(int value) {
        this.value = value;
        this.nextNode = null;
    }
}

public class DesignCircularQueue {

    private Node head, tail;
    private int count;
    private int capacity;

    public DesignCircularQueue(int k) {
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if (this.count == this.capacity) {
            return false;
        }

        Node newNode = new Node(value);
        if (this.count == 0) {
            head = tail = newNode;
        } else {
            tail.nextNode = newNode;
            tail = newNode;
        }
        this.count += 1;
        return true;
    }

    public boolean deQueue() {
        if (this.count == 0) {
            return false;
        }
        this.head = this.head.nextNode;
        this.count -= 1;
        return true;
    }

    public int Front() {
        if (this.count == 0) {
            return -1;
        } else {
            return this.head.value;
        }
    }

    public int Rear() {
        if (this.count == 0) {
            return -1;
        } else {
            return this.tail.value;
        }
    }

    public boolean isEmpty() {
        return (this.count == 0);
    }

    public boolean isFull() {
        return (this.count == this.capacity);
    }
 */

/* 线程安全版

class MyCircularQueue {

  private Node head, tail;
  private int count;
  private int capacity;
  // Additional variable to secure the access of our queue
  private ReentrantLock queueLock = new ReentrantLock();

    public MyCircularQueue(int k) {
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        // ensure the exclusive access for the following block.
        queueLock.lock();
        try {
            if (this.count == this.capacity)
                return false;

            Node newNode = new Node(value);
            if (this.count == 0) {
                head = tail = newNode;
            } else {
                tail.nextNode = newNode;
                tail = newNode;
            }
            this.count += 1;

        } finally {
            queueLock.unlock();
        }
        return true;
    }
}
 */