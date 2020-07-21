package Greedy;

/*  621. Task Scheduler
    You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task.
    Tasks could be done without the original order of the array. Each task is done in one unit of time.
    For each unit of time, the CPU could complete either one task or just be idle.

    However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
    that is that there must be at least n units of time between any two same tasks.

    You need to return the least number of units of times that the CPU will take to finish all the given tasks.

    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation:
    A -> B -> idle -> A -> B -> idle -> A -> B
    There is at least 2 units of time between any two same tasks.

    Example 2:
    Input: tasks = ["A","A","A","B","B","B"], n = 0
    Output: 6
    Explanation: On this case any permutation of size 6 would work since n = 0.
    ["A","A","A","B","B","B"]
    ["A","B","A","B","A","B"]
    ["B","B","B","A","A","A"]
    ...
    And so on.

    Example 3:
    Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
    Output: 16
    Explanation:
    One possible solution is
    A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

    Constraints:
    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*  PQ: Time = O(time) Space = O(1)
    在选择每一轮中的任务时，我们也可以用优先队列（堆）来代替排序。
    在一开始，我们把所有的任务加入到优先队列中。
    在每一轮，我们从优先队列中选择最多 n+1 个任务，把它们的数量减去 1，再放回堆中（如果数量不为 0），直到堆为空。
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int f : map) {
            if (f > 0) {
                maxHeap.add(f);
            }
        }

        int time = 0;
        while (!maxHeap.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while (i <= n) {
                if (!maxHeap.isEmpty()) {
                    if (maxHeap.peek() > 1) {
                        temp.add(maxHeap.poll() - 1);
                    } else {
                        maxHeap.poll();
                    }
                }
                time++;
                if (maxHeap.isEmpty() && temp.size() == 0) {
                    break;
                }
                i++;
            }
            maxHeap.addAll(temp);
        }
        return time;
    }
}

/*  Sorting: Time = O(time) Space = O(1)
    由于相同的任务之间必须有 n 的冷却时间，所以我们可以想到按照任务的数量来安排它们，即一种任务的出现次数越多，我们就越早地安排。
    因此我们得到了一种安排的方法：我们规定 n+1 个任务为一轮，这样的好处是同一轮中一个任务最多只能被安排一次。
    在每一轮中，我们将当前的任务按照它们剩余的次数降序排序，并选择剩余次数最多的 n+1 个任务依次执行。
    如果任务的种类 t 少于 n+1 个，就只选择全部的 t 种任务，其余的时间空闲。
    这样做的正确性在于，由于冷却时间的存在，出现次数较多的那些任务如果不尽早安排，将会导致大量空闲时间的出现，因此贪心地将出现次数较多的任务安排在前面是合理的。

        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0) {
                    break;
                }
                if (i < 26 && map[25 - i] > 0) {
                    map[25 - i]--;
                }
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
 */

/*  Design: Time = O(M)，M 是任务的总数 Space = O(1)

        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
 */