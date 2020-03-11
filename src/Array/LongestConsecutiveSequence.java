package Array;

/*  128. Longest Consecutive Sequence
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
    Your algorithm should run in O(n) complexity.

    Example:
    Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
import java.util.HashSet;
import java.util.Set;
/*  HashSet & Intelligent Sequence Building: Time = O(n) Space = O(n)   Offline
    Numbers are stored in a HashSet to allow O(1) lookups
    Check whether h contains key-1 or not
    if not, key is a lower bound, check key+1, key+2, until key+l+1 is not in h
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longestStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}

/*  Brute Force: Time = O(n^3) Space = O(1)

    private boolean arrayContains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
    public int longestConsecutive(int[] nums) {
        int longestStreak = 0;
        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;

            while (arrayContains(nums, currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }
        return longestStreak;
    }
 */

/*  Sorting: Time = O(nlogn) Space = O(1)

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentStreak += 1;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);
 */

/*  HashMap: Time = O(n) Space = O(n)   Online
    Whenever a new element n is inserted into the map, do two things:
    1. See if n-1 and n+1 exists in the map, it means there is an existing sequence next to n
    2. Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length

        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1) ? map.get(n - 1) : 0);
                int right = (map.containsKey(n + 1) ? map.get(n + 1) : 0);
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length of the boundaries of sequence
                // will do nothing if n has no neighbours
                map.put(n - left, sum);
                map.put(n + right, sum);
            } else {
                continue;
            }
        }
        return res;
 */