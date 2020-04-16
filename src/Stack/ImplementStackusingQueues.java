package Stack;

/*  225. Implement Stack using Queues
    Implement the following operations of a stack using queues.
    push(x) - Push element x onto the stack.
    pop() - Remove the element on top of the stack.
    top() - Get the top element.
    empty() - Return whether the stack is empty.

    Example:
    MyStack stack = new MyStack();

    stack.push(1);
    stack.push(2);
    stack.top();   // returns 2
    stack.pop();   // returns 2
    stack.empty(); // returns false

    Notes:
    You must use only standard operations of a queue --
    which means only push to back, peek/pop from front, size and is empty operations are valid.

    Depending on your language, queue may not be supported natively.
    You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.

    You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack.)
 */

/*
    如果 push 设计得复杂，那么 pop 和 peek 的设计就会变得简单
    如果 push 设计得简单，那么 pop 和 peek 的设计就会变得复杂
 */
import java.util.LinkedList;
import java.util.Queue;
//  One Queue: push - O(n), pop - O(1)
public class ImplementStackusingQueues {

    Queue<Integer> queue;

    public ImplementStackusingQueues() {
        queue = new LinkedList<>();
    }

    // Invert the order of queue elements when pushing a new element.
    // 每次 push 的时候维护 queue 变成 stack
    public void push(int x) {
        queue.offer(x);
        // poll 出除了新 offer 进来的元素以外的所有旧元素
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

/*  Two Queues: push - O(n), pop - O(1)

    public void push(int x) {       // 1,2,3
        q2.add(x);                  // q2 = 1; q2 = 2
        while (!q1.isEmpty()) {     //         q1 = 1
            q2.add(q1.poll());      //         q2 = 2,1 q1 = null
        }
        Queue<Integer> temp = q1;   // temp = null
        q1 = q2;                    // q1 = 1; q1 = 2,1
        q2 = temp;                  // q2 = null; q2 = null
    }
    public int pop(int x) {         // 3,2,1
        return q1.poll();        // 3
    }
    public int top() {              // 2,1
        return q1.peek();           // 2
    }
    public boolean empty() {
        return q1.isEmpty();
    }
 */

/*  Two Queues: push - O(1), pop - O(n)

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void push(int x) {
        q1.offer(x);
    }
    public int pop() {              // 1,2,3
        while (q1.size() > 1) {
            q2.offer(q1.poll());      // q2 = 1,2
        }
        int pop = q1.poll();                   // 3
        // 还原
        Queue<Integer> temp = q1;
        q1 = q2;                    // q1 = 1,2
        q2 = temp;                  // q2 = null
        return pop;
    }
    public int top() {              // 1,2
        while (q1.size() > 1) {
            q2.offer(q1.poll());      // q2 = 1
        }
        int top = q1.poll();           // 2, q1 = null
        q2.offer(top);                    // q2 = 1,2
        // 还原
        Queue<Integer> temp = q1;
        q1 = q2;                    // q1 = 1,2
        q2 = temp;                  // q2 = null
        return top;
    }
    public boolean empty() {
        return q1.isEmpty();
    }
 */