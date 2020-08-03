# 381. Insert Delete GetRandom O(1) - Duplicates allowed
允许重复元素的存在，也就是说现在使用 insert()，如果数组中已经有该元素，会 return false，但还是可以 insert 成功

在 #380 的基础上，这题只用做一些修改

![#381](/src/images/%23381.png)

Map 存的 value 有所改变，因为现在存在 duplicate，所以会出现 1 个元素对应多个 index 的情况，所以我们用 LinkedHashSet 存所有的 index

用 LinkedHashSet 的好处在于，我们在 remove 的时候可以用到该数据结构的 iterator，这样我们就可以按顺序删除 LinkedHashSet 中存在的 index 了。
