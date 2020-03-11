package Graph;

/*  399. Evaluate Division
    Equations are given in the format A / B = k, where A and B are variables represented as strings,
    and k is a real number (floating point number). Given some queries, return the answers.
    If the answer does not exist, return -1.0 .

    Example:
    Given a / b = 2.0, b / c = 3.0.
    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
    return [6.0, 0.5, -1.0, 1.0, -1.0 ].

    The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries,
    where equations.size() == values.size(), and the values are positive.
    This represents the equations. Return vector<double>.

    According to the example above:
    equations = [ ["a", "b"], ["b", "c"] ],
    values = [2.0, 3.0],
    queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

    The input is always valid.
    You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

import java.util.*;

/*  Graph（有向图）+ DFS
    A / B = 2
    g[A][B] = 2 | g[B][A] = 1.0 / 2
 */
public class EvaluateDivision {
    Map<String, HashMap<String, Double>> map = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); ++i) {
            String x = equations.get(i).get(0);     // x = "a"
            String y = equations.get(i).get(1);     // y = "b"
            double k = values[i];                   // k = 2.0
            map.computeIfAbsent(x, l -> new HashMap<String, Double>()).put(y, k);   // a -> b, 2.0
            map.computeIfAbsent(y, l -> new HashMap<String, Double>()).put(x, 1.0 / k); // b -> a, 0.5
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            String x = queries.get(i).get(0);       // x = a
            String y = queries.get(i).get(1);       // y = c
            if (!map.containsKey(x) || !map.containsKey(y)) {
                ans[i] = -1.0;
            } else {
                ans[i] = dfs(x, y, new HashSet<String>());  // ans[0] = dfs(a, c, visited)
            }
        }
        return ans;
    }

    private double dfs(String x, String y, Set<String> visited) {
        if (x.equals(y)) return 1.0;
        visited.add(x);                         // a;
        if (!map.containsKey(x)) return -1.0;
        for (String n : map.get(x).keySet()) {  // n = b
            if (visited.contains(n)) continue;
            visited.add(n);                     // a, b
            double d = dfs(n, y, visited);      // d = dfs(b, c, visited)
            if (d > 0) {
                return d * map.get(x).get(n);
            }
        }
        return -1.0;
    }
}

/*  Union Find

 */