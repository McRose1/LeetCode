package Matrix;

/*  370. Range Addition
    Assume you have an array of length n initialized with all 0's and are given k update operations.
    Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element
    of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
    Return the modified array after all k operations were executed.

    Example:
    Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
    Output: [-2,0,3,5,3]
    Explanation:

    Initial state:
    [0,0,0,0,0]

    After applying operation [1,3,2]:
    [0,2,2,2,0]

    After applying operation [2,4,3]:
    [0,2,5,5,3]

    After applying operation [0,2,-2]:
    [-2,0,3,5,3]
 */

/*  Time = O(k + n ) Space = O(1)
    只对每个区间的 start 加上值，在 end+1 减去值，start 开始每个坐标都累加这个值，而到了 end+1 开始这个值就要减去
 */
public class RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int value = update[2];
            int start = update[0];
            int end = update[1];
            res[start] += value;
            if (end + 1 < length) {
                res[end + 1] -= value;
            }
        }
        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
