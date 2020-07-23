package UnionFind;

// Disjoint Set Union
public class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            // 初始化每个节点的 root 都是本身
            parent[i] = i;
        }
        rank = new int[size];
    }

    public int find(int x) {
        // 不断往上找 root
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        // 分别找到 x 和 y 的根
        int xr = find(x);
        int yr = find(y);

        // x 和 y 在同一个集合中，说明 x 和 y 之间的 edge 会使 graph 形成 cycle，也就是 redundant，所以直接返回这条 edge
        if (xr == yr) {
            return false;
        }
        // x 和 y 不在同一个集合中，进行 union
        // Union：always merge smaller set to larger set
        else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}
