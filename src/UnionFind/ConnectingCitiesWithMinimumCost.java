package UnionFind;

/*  1135. Connecting Cities With Minimum Cost
    There are N cities numbered from 1 to N.
    You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
    (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
    Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.
    The cost is the sum of the connection costs used. If the task is impossible, return -1.

    Example 1:
    Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
    Output: 6
    Explanation:
    Choosing any 2 edges will connect all cities so we choose the minimum 2.

    Example 2:
    Input: N = 4, connections = [[1,2,3],[3,4,4]]
    Output: -1
    Explanation:
    There is no way to connect all cities even if all edges are used.

    Note:
        1. 1 <= N <= 10000
        2. 1 <= connections.length <= 10000
        3. 1 <= connections[i][0], connections[i][1] <= N
        4. 0 <= connections[i][2] <= 10^5
        5. connections[i][0] != connections[i][1]
 */

import java.util.Arrays;

public class ConnectingCitiesWithMinimumCost {
    public int minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        int res = 0;
        UF uf = new UF(N);
        for (int[] connect : connections) {
            if (uf.find(connect[0]) != uf.find(connect[1])) {
                uf.union(connect[0], connect[1]);
                res += connect[2];
            }
            if (uf.count == 1) {
                return res;
            }
        }
        return -1;
    }
}

class UF {
    int[] parent;
    int[] size;
    int count;

    public UF(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = i;
        }
        count = n;
    }

    public int find(int i) {
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int x_root = find(x);
        int y_root = find(y);
        if (size[x] > size[y]) {
            parent[y] = x;
            size[x] += size[y];
        } else {
            parent[x] = y;
            size[y] += size[x];
        }
        count--;
    }
}
