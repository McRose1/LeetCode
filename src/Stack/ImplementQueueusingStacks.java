package Stack;

/*  232. Implement Queue using Stacks
    Implement the following operations of a stack using stacks.
    push(x) - Push element x to the back of queue.
    pop() - Remove the element from in front of queue.
    top() - Get the front element.
    empty() - Return whether the queue is empty.

    Example:
    MyQueue queue = new MyQueue();

    queue.push(1);
    queue.push(2);
    queue.peek();   // returns 1
    queue.pop();   // returns 1
    queue.empty(); // returns false

    Notes:
    You must use only standard operations of a stack --
    which means only push to top, peek/pop from top and is empty operations are valid.

    Depending on your language, stack may not be supported natively.
    You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.

    You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue.)
 */

import java.util.Stack;
//  Two Stacks (1 for push&pop, 1 for temp use): Push - O(n), Pop - O(1)
public class ImplementQueueusingStacks {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public ImplementQueueusingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack2.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
    public int pop() {
        return stack1.pop();
    }
    public int peek() {
        return stack1.peek();
    }
    public boolean empty() {
        return stack1.isEmpty();
    }

}

/*  Two Stacks (1 for push, 1 for pop): Push - O(1), Amortized Pop - O(1) Space = O(1)

    Stack<Integer> s1;
    Stack<Integer> s2;
    public ImplementQueueusingStacks() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);             // 1,2,3,4,5
    }

    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());  // 5,4,3,2,1
            }
        }
        return s2.pop();            // 5->4->3->2->1
    }

    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
 */