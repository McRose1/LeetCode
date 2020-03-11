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

import Stack.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator implements Iterator<Integer>{
    Stack<NestedInteger> stack;

    public void NestedIterator(List<NestedInteger> nestedList)  {
        stack = new Stack<>();
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
}
