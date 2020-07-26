package Tree;

/*  307. Range Sum Query - Mutable
    Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
    The update(i, val) function modifies nums by updating the element at index i to val.

    Example:
    Given nums = [1, 3, 5]

    sumRange(0, 2) -> 9
    update(1, 2)
    sumRange(0, 2) -> 8

    Constraints:
    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.
    0 <= i <= j <= nums.length - 1
 */

/*  Segment Tree
    本质上是一颗二叉树，不同于其他二叉树，线段树的每一个节点会记录一段线段区间的信息
    线段树可以分为以下 3 个步骤：
        1. 从给定数组构建线段树的预处理步骤
        2. 修改元素时更新线段树
        3. 使用线段树进行区域和检索
     如果某个节点包含范围 [i...j] 的和，那么其左、右子节点分别包含范围 [i...(i+j)/2] 和 [(i+j)/2 + 1, j] 上的和
 */
public class RangeSumQuery_Mutable {
    int[] tree;
    int n;
    public RangeSumQuery_Mutable(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }
    // 自下而上构建线段树，逐步向上移动到更高一层来计算父节点的和，直到最后到达线段树的根节点
    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int i, int val) {
        i += n;
        tree[i] = val;
        while (i > 0) {
            int left = i;
            int right = i;
            if (i % 2 == 0) {
                right = i + 1;
            } else {
                left = i - 1;
            }
            // parent is updated after child is updated
            tree[i / 2] = tree[left] + tree[right];
            i /= 2;
        }
    }

    public int sumRange(int i, int j) {
        // get leaf with 'i'
        i += n;
        // get leaf with 'j'
        j += n;
        int sum = 0;
        while (i <= j) {
            if ((i % 2) == 1) {
                sum += tree[i];
                i++;
            }
            if ((j % 2) == 0) {
                sum += tree[j];
                j--;
            }
            i /= 2;
            j /= 2;
        }
        return sum;
    }
}

/*  山景城一姐

    class SegmentTreeNode {
        int start;
        int end;
        int val;
        SegmentTreeNode left;
        SegmentTreeNode right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.val = 0;
        }
    }

    private SegmentTreeNode root;

    public NumArray(int[] nums) {
        this.root = buildTree(nums, 0, nums.length - 1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if (start == end) {
            node.val = nums[start];
        }  else {
            int mid = start + (end - start) / 2;
            node.left = buildTree(nums, start, mid);
            node.right = buildTree(nums, mid + 1, end);
            node.val = node.left.val + node.right.val;
        }
        return node;
    }

    public void update(int i, int val) {
        update(this.root, i, val);
    }
    void update(SegmentTreeNode root, int i, int val) {
        if (root.start == root.end) {
            root.val = val;
            return;
        }
        int middle = root.start + (root.end - root.start) / 2;
        if (i <= middle) {
            update(root.left, i, val);
        } else {
            update(root.right, i, val);
        }
        root.val = root.left.val + root.right.val;
    }

    public int sumRange(int i, int j) {
        return sumRange(this.root, i, j);
    }
    int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.end == end && root.start == start) return root.val;
        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) {
            return sumRange(root.left, start, end);
        } else if (start >= mid + 1) {
            return sumRange(root.right, start, end);
        } else {
            return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
        }
    }
 */

/*  BIT

 */

/*  Sqrt Decomposition
    将数组分割成块，块的长度为 sqrt(n)。然后我们计算每个块的和，并将其存储在辅助存储器 b 中。
    要查询 RSQ(i, j)，我们将添加位于内部的所有块和部分在范围 [i...j] 重叠的块的总和。

    private int[] b;
    private int len;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        double l = Math.sqrt(nums.length);
        len = (int) Math.ceil(nums.length / l);
        b = new int[len];
        for (int i = 0; i < nums.length; i++) {
            b[i / len] += nums[i];
        }
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        int startBlock = i / len;
        int endBlock = j / len;
        if (startBlock == endBlock) {
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }
        } else {
            for (int k = i; k <= (startBlock + 1) * len - 1; k++) {
                sum += nums[k];
            }
            for (int k = startBlock + 1; k <= endBlock - 1; k++) {
                sum += b[k];
            }
            for (int k = endBlock * len; k <= j; k++) {
                sum += nums[k];
            }
        }
        return sum;
    }

    public void update(int i, int val) {
        int b_l = i / len;
        b[b_l] = b[b_l] - nums[i] + val;
        nums[i] = val;
    }
 */