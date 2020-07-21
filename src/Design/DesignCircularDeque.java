package Design;

/*  641. Design Circular Deque
    Design your implementation of the circular double-ended queue (deque).
    Your implementation should support following operations:
    o MyCircularDeque(k): Constructor, set the size of the deque to be k.
    o insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
    o insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
    o deleteFront(): Deletes an item at the front of Deque. Return true if the operation is successful.
    o deleteLast(): Deletes an item at the rear of Deque. Return true if the operation is successful.
    o getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
    o getRear(): Gets the last item from the Deque. If the deque is empty, return -1.
    o isEmpty(): Checks whether Deque is empty or not.
    o isFull(): Checks whether Deque is full or not.

    Example:
    MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
    circularDeque.insertLast(1);			// return true
    circularDeque.insertLast(2);			// return true
    circularDeque.insertFront(3);			// return true
    circularDeque.insertFront(4);			// return false, the queue is full
    circularDeque.getRear();  			// return 2
    circularDeque.isFull();				// return true
    circularDeque.deleteLast();			// return true
    circularDeque.insertFront(4);			// return true
    circularDeque.getFront();			// return 4

    Note:
    All values will be in the range of [0, 1000].
    The number of operations will be in the range of [1, 1000].
    Please do not use the built-in Deque library.
 */

/*  Array

 */
public class DesignCircularDeque {

    private int capacity;
    private int[] deque;
    private int front;
    private int rear;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public DesignCircularDeque(int k) {
        this.capacity = k + 1;
        this.deque = new int[this.capacity];
        this.front = 0;
        this.rear = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        this.front = (this.front + this.capacity - 1) % this.capacity;
        this.deque[this.front] = value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        this.deque[this.rear] = value;
        this.rear = (this.rear + 1) % this.capacity;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        this.front = (this.front + 1) % this.capacity;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        this.rear = (this.rear + this.capacity - 1) % this.capacity;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return this.deque[this.front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return this.deque[(this.rear + this.capacity - 1) % this.capacity];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return (this.front == this.rear);
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return ((this.rear + 1) % this.capacity == this.front);
    }
}
