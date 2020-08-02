package OrderedMap;

/*  352. Data Stream as Disjoint Intervals
    Given a data stream input of non-negative integers a1, a2, ..., an,..., summarize the numbers seen so far as a list of disjoint intervals.
    For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
    [1, 1]
    [1, 1], [3, 3]
    [1, 1], [3, 3], [7, 7]
    [1, 3], [7, 7]
    [1, 3], [6, 7]

    Follow up:
    What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */

import java.util.TreeMap;

/*  TreeMap

 */
public class DataStreamasDisjointIntervals {
    // <Interval起始点，Interval>
    TreeMap<Integer, int[]> treeMap;
    /** Initialize your data structure here. */
    public DataStreamasDisjointIntervals() {
        treeMap = new TreeMap<>();
    }

    public void addNum(int val) {
        if (treeMap.containsKey(val)) return;

        Integer lowerKey = treeMap.lowerKey(val);
        Integer higherKey = treeMap.higherKey(val);

        // [1, 1] [3, 3] + 2 -> [1, 3]
        if (lowerKey != null && higherKey != null && val == treeMap.get(lowerKey)[1] + 1 && val == treeMap.get(higherKey)[0] - 1) {
            // [1, 1] -> [1, 3]
            treeMap.get(lowerKey)[1] = treeMap.get(higherKey)[1];
            treeMap.remove(higherKey);
        }
        // [7, 7] + 8 -> [7, 8]
        else if (lowerKey != null && val <= treeMap.get(lowerKey)[1] + 1) {
            treeMap.get(lowerKey)[1] = Math.max(val, treeMap.get(lowerKey)[1]);
        }
        // [7, 7] + 6 -> [6, 7]
        else if (higherKey != null && val == treeMap.get(higherKey)[0] - 1) {
            treeMap.put(val, new int[] {val, treeMap.get(higherKey)[1]});
            treeMap.remove(higherKey);
        }
        // [1, 1] [7, 7] + 4 -> [1, 1] [4, 4] [7, 7]
        else {
            treeMap.put(val, new int[] {val, val});
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[treeMap.size()][2];
        int i = 0;
        for (int[] interval : treeMap.values()) {
            res[i++] = interval;
        }
        return res;
    }
}
