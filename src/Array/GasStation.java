package Array;

/*  134. Gas Station
    There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
    You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
    You begin the journey with an empty tank at one of the gas stations.
    Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

    Note:
        If there exists a solution, it is guaranteed to be unique.
        Both input arrays are non-empty and have the same length.
        Each element in the input arrays is a non-negative integer.

    Example 1:

    Input:
    gas  = [1,2,3,4,5]
    cost = [3,4,5,1,2]
    Output: 3

    Example 2:

    Input:
    gas  = [2,3,4]
    cost = [3,4,3]
    Output: -1
 */
/*  Time: O(N) Space = O(1)
    如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的
    如果 A -> B 不可能到达，那么 A-B 之间的任意一个点也不可能到达 B，因为前提就是 A 可以作为起点，
    也就是说在 A 点，gas[A] - cost[A] >= 0，但是最后到达 B 的结果是 < 0，所以 A 以后任意节点出发肯定也是 < 0
    我们需要做的就是把 B 设置为新的起点
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        int total_tank = 0;
        int cur_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; i++) {
            total_tank += gas[i] - cost[i];             // 1-3=-2; -2+2-4=-4; -4+3-5=-6; -6+4-1=-3; -3+5-2=0
            cur_tank += gas[i] - cost[i];               // -2; 0+2-4=-2; 0+3-5=-2; 0+4-1=3; 3+5-2=6
            // 判断是否可作为起点
            if (cur_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;               // 1; 2; 3
                // Start with an empty tank.
                cur_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }
}