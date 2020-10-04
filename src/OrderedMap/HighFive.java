package OrderedMap;

/*  1086. High Five
    Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
    Each entry items[i] has items[i][0] the student's id, and item[i][1] the student's score.
    The average score is calculated using integer division.

    Example:
    Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
    Output: [[1,87],[2,88]]
    Explanation:
    The average of the student with id = 1 is 87.
    The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.

    Note:
        1. 1 <= items.length <= 1000
        2. items[i].length == 2
        3. The IDs of the students is between 1 to 1000
        4. The score of the students is between 1 to 100
        5. For each student, there are at least 5 scores
 */

import java.util.PriorityQueue;
import java.util.TreeMap;

/*  TreeMap + PriorityQueue
    TreeMap -> order of each student's id
    PriorityQueue -> minHeap to maintain top 5 scores
 */
public class HighFive {
    public int[][] highFive(int[][] items) {
        TreeMap<Integer, PriorityQueue<Integer>> treeMap = new TreeMap<>();
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            if (treeMap.containsKey(id)) {
                minHeap = treeMap.get(id);
                minHeap.offer(score);
                if (minHeap.size() > 5) {
                    minHeap.poll();
                }
            } else {
                minHeap.offer(score);
                treeMap.put(id, minHeap);
            }
        }
        int[][] res = new int[treeMap.size()][2];
        for (int id : treeMap.keySet()) {
            PriorityQueue<Integer> minHeap = treeMap.get(id);
            int sum = 0;
            while (!minHeap.isEmpty()) {
                sum += minHeap.poll();
            }
            res[id - 1][0] = id;
            res[id - 1][1] = sum / 5;
        }
        return res;
    }
}
