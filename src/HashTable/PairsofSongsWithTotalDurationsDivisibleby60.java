package HashTable;

/*  1010. Pairs of Songs With Total Durations Divisible by 60
    In a list of songs, the i-th song has a duration of time[i] seconds.
    Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
    Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 = 0.

    Example 1:
    Input: [30,20,150,100,40]
    Output: 3
    Explanation: Three pairs have a total duration divisible by 60:
    (time[0] = 30, time[2] = 150): total duration 180
    (time[1] = 20, time[3] = 100): total duration 120
    (time[1] = 20, time[4] = 40): total duration 60

    Example 2:
    Input: [60,60,60]
    Output: 3
    Explanation: All three pairs have a total duration of 120, which is divisible by 60.

    Note:
        o 1 <= time.length <= 60000
        o 1 <= time[i] <= 500

    Hint:
    1. We only need to consider each song length modulo 60.
    2. We can count the number of songs with (length % 60) equal to r, and store that in an array of size 60.
 */

/*  Two Sum with K=60
    1. x % 60 = 0 && t % 60 = 0
    2. x % 60 + t % 60 = 60
    合并 -> x % 60 = (60 - t % 60) % 60
 */
public class PairsofSongsWithTotalDurationsDivisibleby60 {
    public int numPairsDivisibleBy60(int[] time) {
        int[] c = new int[60];
        int count = 0;
        for (int t : time) {
            count += c[(60 - t % 60) % 60];
            c[t % 60] += 1;
        }
        return count;
    }
}
