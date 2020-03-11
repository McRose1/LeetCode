/*
    Given a non-empty array of numbers, a0, a1, a2, ... ,an-1, where 0 ≤ ai < 2^31.
    Find the maximum result of ai XOR aj, where where 0 ≤ i, j < n.
    Could you do this in O(n) runtime?

    Example:
    Input: [3, 10, 5, 25, 2, 8]
    Output: 28
 */

public class MaximumXORofTwoNumbersinanArray {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int cur = nums[i] ^ nums[j];
                if (cur > max) {
                    max = cur;
                }
            }
        }
        return max;
    }
}
/*
    a ^ b = x
    a ^ x = b

        int res = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);     // 100..000, 110..000, 111..00,... 111..11 which is the largest result we have right now
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);    // we only care about the left parts
            }
            int tmp = res | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    res = tmp;
                    break;
                }
            }
        }
        return res;
 */