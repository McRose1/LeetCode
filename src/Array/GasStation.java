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

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
        }
        if (tank < 0) {
            return -1;
        }

        int start = 0;
        int accumulate = 0;
        for (int i = 0; i < gas.length; i++) {
            int curGain = gas[i] - cost[i];
            if (accumulate + curGain < 0) {
                start = i + 1;
                accumulate = 0;
            } else {
                accumulate += curGain;
            }
        }
        return start;
    }
}
/*
    如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的
 */