package Stack;

/*  155. Min Stack
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

    Example:
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();  -> Returns -3.
    minStack.pop();
    minStack.top();     -> Returns 0.
    minStack.getMin();  -> Returns -2.
 */

import java.util.Stack;
//  One Stack
public class MinStack {

    Stack<Integer> stack;
    int min;

    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        // only push the old minimum value when the current minimum value changes after pushing the new value x
        if (x <= min) {         // x = -2; -3
            stack.push(min);    // MAX; -2 -> -2, 0, -3
            min = x;            // min = -2; min = -3
        }
        stack.push(x);          // -2, 0
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/*  Two Stacks
    stack 存储压入的数据，minStack 存储最小值

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);              // -2; 0; -3
        if (minStack.isEmpty()) {
            minStack.push(x);       // -2
        } else {
            minStack.push(Math.min(minStack.peek(), x));   // -2, -2, -3
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
 */