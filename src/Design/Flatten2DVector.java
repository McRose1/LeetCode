package Design;

/*  Design and implement an iterator to flatten a 2d vector.
    It should support the following operations: next and hasNext.

    Example:
    Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

    iterator.next(); // return 1
    iterator.next(); // return 2
    iterator.next(); // return 3
    iterator.hasNext(); // return true
    iterator.hasNext(); // return true
    iterator.next(); // return 4
    iterator.hasNext(); // return false

    Notes:
    1. Please remember to RESET your class variables declared in Vector2D,
    as static/class variables are persisted across multiple test cases. Please see here for more details.
    2. You may assume that next() call will always be valid, that is,
    there will be at least a next element in the 2d vector when next() is called.
 */

import java.util.Iterator;
import java.util.List;
/*  Two Pointers
    col 到头了需要换行：row++, col = 0
 */
public class Flatten2DVector implements Iterator<Integer> {
    private int row, col;
    private List<List<Integer>> list;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        list = vec2d;
        row = 0;
        col = 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        // 每次调用完 next，col 指针指向下一位
        return list.get(row).get(col++);
    }

    @Override
    public boolean hasNext() {
        // 当到达一行的末列，就要进入下一行，col 置零，因为下一行可能存在为空的情况，所以用 while 可以跳过空行
        while (row < list.size() && col == list.get(row).size()) {
            row++;
            col = 0;
        }
        return row < list.size();
    }

    @Override
    public void remove() {
        if (col - 1 < 0) {
            return;
        }
        if (row < list.size() && col <= list.get(row).size()) {
            list.get(row).remove(--col);
        }
    }
}

/*  Iterator

    Iterator<Integer> q;
    Iterator<List<Integer> > i;
    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return q.next();
    }

    @Override
    public boolean hasNext() {
        while((q==null || !q.hasNext()) && i.hasNext())
            q = i.next().iterator();
        return q==null ? false :q.hasNext();
    }

    @Override
    public void remove() {}
 */
