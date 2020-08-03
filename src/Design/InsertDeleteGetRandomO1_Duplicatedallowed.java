package Design;

/*  381. Insert Delete GetRandom O(1) - Duplicates allowed
    Design a data structure that supports all following operations in average O(1) time.
    Note: Duplicate elements are allowed.
    1. insert(val): Inserts an item val to the collection.
    2. remove(val): Removes an item val from the collection if present.
    3. getRandom: Returns a random element from current collection of elements.
    The probability of each element being returned is linearly related to the number of same value the collection contains.

    Example:
    // Init an empty collection.
    RandomizedCollection collection = new RandomizedCollection();

    // Inserts 1 to the collection. Returns true as the collection did not contain 1.
    collection.insert(1);

    // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
    collection.insert(1);

    // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
    collection.insert(2);

    // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
    collection.getRandom();

    // Removes 1 from the collection, returns true. Collection now contains [1,2].
    collection.remove(1);

    // getRandom should return 1 and 2 both equally likely.
    collection.getRandom();
 */

import java.util.*;

//  ArrayList + HashMap: Time = O(n) Space = O(n)
public class InsertDeleteGetRandomO1_Duplicatedallowed {
    ArrayList<Integer> list;
    HashMap<Integer, Set<Integer>> map;
    Random rand;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1_Duplicatedallowed() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            // 一个元素现在可能有多个坐标，存在 LinkedHashSet 里以方便后面逐个删除
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return map.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).size() == 0) {
            return false;
        }
        // LinkedHashSet 的迭代器按输入顺序输出
        int remove_idx = map.get(val).iterator().next();
        map.get(val).remove(remove_idx);
        int last = list.get(list.size() - 1);
        list.set(remove_idx, last);
        map.get(last).add(remove_idx);
        map.get(last).remove(list.size() - 1);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
