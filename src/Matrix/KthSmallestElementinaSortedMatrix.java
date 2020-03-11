package Matrix;

/*  378. Kth Smallest Element in a Sorted Matrix
    Given a n x n matrix where each of the rows and columns are sorted in ascending order,
    find the kth smallest element in the matrix.

    Note that it is the kth smallest element in the sorted order, not the kth distinct element.

    Example:
    matrix = [
               [ 1,  5,  9],
               [10, 11, 13],
               [12, 13, 15]
             ],
    k = 8,

    return 13.

    Note: You may assume k is always valid, 1 ≤ k ≤ n^2.
 */

//  Binary Search: Time = O(n * log(max - min)) Space = O(1)
public class KthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            int num = count(matrix, mid);
            if (num >= k) right = mid;
            else left = mid;
        }
        if (count(matrix, right) <= k - 1) return right;
        return left;
    }

    private int count(int[][] matrix, int target) {
        int n = matrix.length;
        int res = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] < target) {
                res += i + 1;
                j++;
            } else i--;
        }
        return res;
    }
}

/*  minHeap: Time = O(nlogn) Space = O(n)

    class Pos {
        int i, j;
        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        Queue<Pos> minHeap = new PriorityQueue<>((o1, o2) -> matrix[o1.i][o1.j] - matrix[o2.i][o2.j]);
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            minHeap.offer(new Pos(i, 0));
        }

        for (int i = 0; i < k - 1; i++) {
            Pos top = minHeap.poll();

            if (top.j + 1 < n) {
                top.j++;
                minHeap.offer(top);
            }
        }
        Pos kth = minHeap.peek();
        return matrix[kth.i][kth.j];
    }
 */
