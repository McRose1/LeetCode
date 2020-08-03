package BinarySearch;

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

/*  Binary Search: Time = O(n * log(r - l)) Space = O(1)
    矩阵内元素从左上到右下递增，matrix[0][0] 为最小值，matrix[n-1][n-1] 为最大值，分别记作 l 和 r
    可以发现一个性质：任取一个数 mid 满足 l <= mid <= r，那么矩阵中不大于 mid 的数，肯定全部分布在矩阵的左上角。
    矩阵中大于 mid 的数就和不大于 mid 的数分别形成了 2 个板块，沿着一条锯齿线将矩阵分开。
    只要沿着这条锯齿线走一遍即可计算出这两个板块的大小，也自然就统计出了这个矩阵中不大于 mid 的数的个数
 */
public class KthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(matrix, mid, k, n)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        // 以左下角为起点
        int i = n - 1, j = 0;
        int count = 0;
        // 在界内
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                // 这一列的当前行往上都是小于当前数
                count += i + 1;
                // 向右移动
                j++;
            } else {
                // 向上移动
                i--;
            }
        }
        return count >= k;
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
        int m = matrix.length, n = matrix[0].length;

        PriorityQueue<Pos> minHeap = new PriorityQueue<>((o1, o2) -> matrix[o1.i][o1.j] - matrix[o2.i][o2.j]);
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

/*  Merge Sort: Time = O(klogn) Space = O(n)
    每一行均为一个有序数组，问题即转化为从这 n 个有序数组中找第 k 大的数

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> () {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            // [数字，行坐标，列坐标]，每行的第一个元素
            pq.offer(new int[] {matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            // 不是最后一列
            if (now[2] != n - 1) {
                pq.offer(new int[] {matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
 */

/*  Brute Force: Time = O(n^2logn) Space = O(n^2)
    将二维数组另存为一维数组，并对该一维数组进行排序。

        int rows = matrix.length, cols = matrix[0].length;
        int[] sorted = new int[rows * cols];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        Arrays.sort(sorted);
        return sorted[k - 1];
 */