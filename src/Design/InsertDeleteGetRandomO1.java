package Design;

/*  380. Insert Delete GetRandom O(1)
    Design a data structure that supports all following operations in average O(1) time.
    1. insert(val): Insert an item val to the set if not already present.
    2. remove(val): Removes an item val from the set if present.
    3. getRandom: Returns a random element from current set of elements.
    Each element must have the same probability of being returned.

    Example:
    // Init an empty set.
    RandomizedSet randomSet = new RandomizedSet();

    // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomSet.insert(1);

    // Returns false as 2 does not exist in the set.
    randomSet.remove(2);

    // Inserts 2 to the set, returns true. Set now contains [1,2].
    randomSet.insert(2);

    // getRandom should return either 1 or 2 randomly.
    randomSet.getRandom();

    // Removes 1 from the set, returns true. Set now contains [2].
    randomSet.remove(1);

    // 2 was already in the set, so return false.
    randomSet.insert(2);

    // Since 2 is the only number in the set, getRandom always return 2.
    randomSet.getRandom();
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*  HashMap + ArrayList
    Insert O(1): hashtable
    Delete O(1): hashtable  -> swap with the last element, delete the last position
    GetRandom O(1): array
 */
public class InsertDeleteGetRandomO1 {
    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;
    Random rand;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = map.get(val);
        list.set(idx, lastElement);
        map.put(lastElement, idx);
        // delete the last element
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
