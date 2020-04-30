package UnionFind;

/*  765. Couples Holding Hands
    N couples sit in 2N seats arranged in a row and want to hold hands.
    We want to know the minimum number of swaps so that every couple is sitting side by side.
    A swap consists of choosing any two people, then they stand up and switch seats.
    The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order,
    the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
    The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

    Example 1:
    Input: row = [0, 2, 1, 3]
    Output: 1
    Explanation: We only need to swap the second (row[1]) and third (row[2]) person.

    Example 2:
    Input: row = [3, 2, 0, 1]
    Output: 0
    Explanation: All couples are already seated side by side.

    Note:
    1. len(row) is even and in the range of [4, 60].
    2. row is guaranteed to be a permutation of 0...len(row)-1.

    Hint:
    Say there are N two-seat couches. For each couple, draw an edge from the couch of one partner to the couch of the other partner.
 */

/*  Union Find: Time = O(n) Space = O(n)
    把一对沙发看作是一个节点，如果已经是一对情侣，那么这个节点就指向本身；
    如果不是，节点指向另一半所在的沙发，这样会形成两条 edge
    每次将一个沙发上凑成一对情侣之后，在图上的变化是多了一个自循环的 edge；
    我们的目标是让图中有 N 个 edge，每个 edge 代表一对情侣；
    问题的答案可以通过 N 减去最开始的情侣图中的指向自己的 edge 的个数来得到。
    快乐交换理论：不论怎么交换，一次交换都不可能增加多于一个指向自己的 edge，所以每次增加一个的交换就是最优解
 */
public class CouplesHoldingHands {
    public int minSwaps(int[] row) {        // row = [0, 2, 1, 3]
        int N = row.length / 2;
        // couples[x] = {i, j} means that couple #x is at couches i and j (1 indexed)
        int[][] couples = new int[N][2];

        for (int i = 0; i < row.length; i++) {
            // row[i]/2 为情侣对#，i/2 + 1为沙发#
            add(couples[row[i] / 2], i / 2 + 1);
        }
        // couples: [[1, 2], [1, 2]]

        // adj[x] = {i, j} means that x-th couch connected to couches i,j (all 1 indexed) by couples
        int[][] adj = new int[N + 1][2];
        for (int[] couple : couples) {
            add(adj[couple[0]], couple[1]);
            add(adj[couple[1]], couple[0]);
        }
        // adj: [[2, 2], [2, 2]]

        // The answer will be N minus the number of cycles in adj.
        int ans = N;
        // For each couch
        for (int r = 1; r <= N; r++) {
            // If this couch has no people needing to be paired, continue
            if (adj[r][0] == 0 && adj[r][1] == 0) {
                continue;
            }
            // Otherwise, there is a cycle starting at couch r.
            // We will use two pointers x, y with y faster than x by one turn.
            ans--;
            int x = r;
            int y = pop(adj[r]);
            // When y reaches the start 'r', we've reached the end of the cycle.
            while (y != r) {
                // We are at some couch with edges going to 'x' and 'new'.
                // We remove the previous edge, since we came from x.
                remove(adj[y], x);

                // We update x, y appropriately: y becomes new and x becomes y.
                x = y;
                y = pop(adj[y]);
            }
        }
        return ans;
    }
    // Replace the next 0 element with x.
    private void add(int[] pair, int x) {
        if (pair[0] == 0) {
            pair[0] = x;
        } else {
            pair[1] = x;
        }
    }
    // Remove x from pair, replacing it with 0.
    private void remove(int[] pair, int x) {
        if (pair[0] == x) {
            pair[0] = 0;
        } else {
            pair[1] = 0;
        }
    }
    // Remove the next non-0 element from pair, replacing it with 0.
    private int pop(int[] pair) {
        int x = pair[0];
        if (x != 0) {
            pair[0] = 0;
        } else {
            x = pair[1];
            pair[1] = 0;
        }
        return x;
    }
}

/*  Optimized Greedy: Time = O(n) Space = O(n)

    public int minSwaps(int[] row) {
        // pos 数组是通过数字找 index
        int[] pos = new int[row.length];
        // 记录每个数字所在 row 数组里的 index
        // row = [3, 2, 0, 1]; pos = [2, 3, 1, 0]
        for (int i = 0; i < row.length; i++) {
            pos[row[i]] = i;
        }
        int ans = 0;
        for (int i = 0; i < row.length; i += 2) {
            int j = row[i] ^ 1;
            if (row[i + 1] != j) {
                swap(row, pos, i + 1, pos[j]);
                ans++;
            }
        }
        return ans;
    }
    // 在 row 数组里交换，并且更新 pos 数组
    private void swap(int[] row, int[] pos, int x, int y) {
        // row = [0, 2, 1, 3]; pos = [0, 2, 1, 3]
        int temp = row[x];  // temp = row[1] = 2
        pos[temp] = y;      // pos[2] = 2
        pos[row[y]] = x;    // pos[row[2]] = pos[1] = 1
        row[x] = row[y];    // row[1] = row[2] = 1
        row[y] = temp;      // row[2] = 2;
        // row = [0, 1, 2, 3]; pos = [0, 1, 2, 3]
    }
 */

/*  Greedy: Time = O(n^2) Space = O(1)
    制定按顺序让每张沙发上情侣开心的策略：
    对于每张沙发，找到沙发上第一个人的情侣，如果不在同一个沙发上，就把沙发上的第二个人换成第一个人的情侣
    如果一个人的编号为 x，那么他的情侣的编号为 x^1

        int ans = 0;
        for (int i = 0; i < row.length; i += 2) {
            int x = row[i];
            if (row[i + 1] == (x ^ 1)) continue;
            ans++;
            for (int j = i + 2; i < row.length; j++) {
                if (row[j] == (x ^ 1)) {
                    row[j] = row[i + 1];
                    row[i + 1] = x ^ 1;
                    break;
                }
            }
        }
        return ans;
 */