package HashTable;

/*  350. Intersection of Two Arrays 2
    Given two arrays, write a function to compute their intersection.

    Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2,2]

    Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [4,9]

    Note:
    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.

    Follow up:
    What if the given array is already sorted? How would you optimize your algorithm? -> Two Pointers

    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    检查数组的大小并对较小的数组进行哈希映射是一个小细节，当其中一个数组较大时，会减少内存的使用。

    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
    如果 nums2 的元素存储在磁盘上，磁盘内存你是有限的，并且你不能一次加载所有的元素到内存中。那么久无法高效地对 nums2 进行排序，因此推荐使用方法一而不是方法二。
    在方法一中，nums2 只关系到查询操作，因此每次读取 nums2 中的一部分数据，并进行处理即可。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//  HashMap: Time = O(m + n) Space = O(min(m, n))
public class IntersectionofTwoArrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        // HashMap 是由小的数组决定的
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num)) {
                if (map.get(num) > 0) {
                    list.add(num);
                    map.put(num, map.get(num) - 1);
                }
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int i : list) {
            res[index++] = i;
        }
        // 也可以不用 List，直接用 int[] 存
     // return Arrays.copyOfRange(intersection, 0, index);
        return res;
    }
}
/*  Arrays.sort: Time = O(nlogn + mlogm) Space = O(1)

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        int idx = 0;
        for (Integer num : list) {
            res[idx++] = num;
        }
        return res;
 */
