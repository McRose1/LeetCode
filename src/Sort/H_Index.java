package Sort;

/*  274. H-Index
    Given an array of citations (each citation is a non-negative integer) of a researcher,
    write a function to compute the researcher's h-index.
    According to the definition of h-index on Wikipedia:
    "A scientist has index h if h of his/her N papers have at least h citations each,
    and the other N - h papers have no more than h citations each."

    Example:
    Input: citations = [3,0,6,1,5]
    Output: 3
    Explanation:
    [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
    Since the researcher has 3 papers with at least 3 citations each
    and the remaining two with no more than 3 citations each, her h-index is 3.

    Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */

/*  Sort: Time = O(nlogn) Space = O(1)
    相当于一个直方图，横坐标代表 i，纵坐标代表 citations[i]，y = x 即 citations[i] = i
    将数组降序排序 -> [6,5,3,1,0]
    如果 citations[i] > i，说明第 0 到第 i 篇论文都有至少 i+1 次引用
    因此我们只要找到最大的 i 满足 citations[i] > i，那么 h-index 即为 i+1
 */

import java.util.Arrays;

public class H_Index {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        // 倒序扫描找出最大的 i
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }
}

/*  Bucket Sort（计数）: Time = O(n) Space = O(n)

        int n = citations.length;
        int[] buckets = new int[n + 1];
        // 计数
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        // 找出最大的 count
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
 */