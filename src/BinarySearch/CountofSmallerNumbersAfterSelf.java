package BinarySearch;

/*  315. Count of Smaller Numbers After Self
    You are given an integer array nums and you have to return a new counts array.
    The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

    Example:
    Input: [5,2,6,1]
    Output: [2,1,1,0]
    Explanation:
    To the right of 5 there are 2 smaller elements (2 and 1).
    To the right of 2 there is only 1 smaller element (1).
    To the right of 6 there is 1 smaller element (1).
    To the right of 1 there is 0 smaller element.
 */

import java.util.*;

/*  Fenwick Tree / Binary Indexed Tree: Time = O(nlogn) Space = O(k) # of unique numbers
    Prefix sums of frequencies, convert the number to its rank as in sorted array.

 */
public class CountofSmallerNumbersAfterSelf {
    private static int lowbit(int x) {
        return x & (-x);
    }

    class FenwickTree {
        private int[] sums;

        public FenwickTree(int n) {
            sums = new int[n + 1];
        }

        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowbit(i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowbit(i);
            }
            return sum;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        // Sort the unique numbers
        Arrays.sort(sorted);
        // Map the number to its rank
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                ranks.put(sorted[i], ++rank);
            }
        }
        FenwickTree tree = new FenwickTree(ranks.size());
        List<Integer> ans = new ArrayList<>();
        // Scan the numbers in reversed order
        for (int i = nums.length - 1; i >= 0; --i) {
            int sum = tree.query(ranks.get(nums[i]) - 1);
            // Check how many numbers are smaller than the current number
            ans.add(tree.query(ranks.get(nums[i]) - 1));
            // Increase the count of the rank of current number
            tree.update(ranks.get(nums[i]), 1);
        }
        Collections.reverse(ans);
        return ans;
    }
}

/*  Binary Search Tree: Time = O(nlon) ~ O(n^2) Space = O(n)
    Track the number of elements smaller than the current node.
    Count the total number of elements smaller than val during inserting val into the tree.

    class Node {
        int val;
        int count;
        int left_count;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            this.count = 1;
        }

        public int less_or_equal() {
            return count + left_count;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        int n = nums.length;
        Node root = new Node(nums[n - 1]);
        ans.add(0);
        for (int i = n - 2; i >= 0; --i) {
            ans.add(insert(root, nums[i]));
        }
        Collections.reverse(ans);
        return ans;
    }
    // Return the number of elements smaller than val under root
    private int insert(Node root, int val) {
        if (root.val == val) {
            ++root.count;
            return root.left_count;
        } else if (val < root.val) {
            ++root.left_count;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);
        } else {
            if (root.right == null) {
                root.right = new Node(val);
                return root.less_or_equal();
            }
            return root.less_or_equal() + insert(root.right, val);
        }
    }
 */

/*  Binary Search

    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(list, nums[i]);
            res[i] = index;
            list.add(index, nums[i]);
        }
        return Array.asList(res);
    }

    private int findIndex(List<Integer> list, int target) {
        if (list.size() == 0) return 0;
        int start = 0;
        int end = list.size() - 1;
        if (list.get(end) < target) return end + 1;
        if (list.get(start) >= target) return 0;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (list.get(start) >= target) return start;
        return end;
    }
 */