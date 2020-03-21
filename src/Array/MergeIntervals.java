package Array;

/*  56. Merge Intervals
    Given a collection of intervals, merge all overlapping intervals.

    Example 1:
    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

    Example 2:
    Input: [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//  Sorting: Time = O(nlogn) Space = O(n)
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return new int[0][0];

        Arrays.sort(intervals, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];

            if (prev[1] >= cur[0]) {
                cur[0] = prev[0];
                cur[1] = Math.max(prev[1], cur[1]);
            } else {
                list.add(prev);
            }
            prev = cur;
        }
        list.add(prev);

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

/*  Connected Components(graph): Time = O(n^2) Space = O(n^2)

    private Map<int[], List<int[]>> graph;
    private Map<Integer, List<int[]>> nodesInComp;
    private Set<int[]> visited;

    public int[][] merge(int[][] intervals) {
        buildGraph(intervals);
        buildComponents(intervals);

        // for each component, merge all intervals into one interval.
        List<int[]> merged = new LinkedList
        for (int comp = 0; comp < nodeInComp.size(); comp++) {
            merged.add(mergeNodes(nodesInComp.get(comp)));
        }
        return merged.toArray(new int[merged.size()][]);
    }

    // build a graph where an undirected edge between intervals u and v exists iff u and v overlap.
    private void buildGraph(int[][] intervals) {
        graph = new HashMap<>();
        for (int[] interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }

        for (int[] interval1 : intervals) {
            for (int[] interval2 : intervals) {
                if (overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

    // gets the connected components of the interval overlap graph.
    private void buildComponents(int[][] intervals) {
        nodeInComp = new HashMap<>();
        visited = new HashSet<>();
        int compNumber = 0;

        for (int[] interval : intervals) {
            if (!visited.contains(interval)) {
                markComponentsDFS(interval, compNumber);
                compNumber++;
            }
        }
    }

    // use depth-first search to mark all nodes in the same connected component with the same integer.
    private void markComponentsDFS(int[] start, int compNumber) {
        Stack<int[]> stack = new Stack<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            int[] node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);

                if (nodesInComp.get(compNumber) == null) {
                    nodeInComp.put(compNumber, new LinkedList<>());
                }
                nodesInComp.get(compNumber).add(node);

                for (int[] child : graph.get(node)) {
                    stack.add(child);
                }
            }
        }
    }

    // return whether two intervals overlap (inclusive)
    private boolean overlap(int[] a, int[] b) {
        return a[0] <= b[1] && b[0] <= a[1];
    }

    // merge all of the nodes in this connected component into one interval.
    private int[] mergeNodes(List<int[]> nodes) {
        int minStart = nodes.get(0)[0];
        for (int[] node : nodes) {
            minStart = Math.min(minStart, node[0]);
        }
        int maxEnd = nodes.get(0)[1];
        for (int[] node : nodes) {
            maxEnd = Math.max(maxEnd, node[1]);
        }
        return new int[] {minStart, maxEnd};
    }
 */
