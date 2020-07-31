package Stack;

/*  341. Flatten Nested List Iterator
    Given a nested list of integers, implement an iterator to flatten it.
    Each element is either an integer, or a list -- whose elements may also be integers or other lists.

    Example 1:
    Input: [[1,1],2,[1,1]]
    Output: [1,1,2,1,1]
    Explanation: By calling next repeatedly until hasNext returns false,
                 the order of elements returned by next should be: [1,1,2,1,1].

    Example 2:
    Input: [1,[4,[6]]]
    Output: [1,4,6]
    Explanation: By calling next repeatedly until hasNext returns false,
                 the order of elements returned by next should be: [1,4,6].
 */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*  Stack

 */
public class FlattenNestedListIterator implements Iterator<Integer>{
    private Stack<Integer> stack;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        helper(nestedList);
    }

    private void helper(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            if (nestedList.get(i).isInteger()) {
                stack.push(nestedList.get(i).getInteger());
            } else {
                helper(nestedList.get(i).getList());
            }
        }
    }

    @Override
    public Integer next() {
        return stack.isEmpty() ? -1 : stack.pop();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/*  Queue
    和上面 Stack 也是一样的效果

    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        helper(nestedList);
    }

    private void helper(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                stack.push(ni.getInteger());
            } else {
                helper(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return queue.isEmpty() ? -1 : queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
 */

/*  复杂版

    private Stack<NestedInteger> stack;

    public void NestedIterator(List<NestedInteger> nestedList)  {
        stack = new Stack<>();
        // 从后往前 push 以实现正序
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            }
            stack.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                stack.push(cur.getList().get(i));
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }
 */
