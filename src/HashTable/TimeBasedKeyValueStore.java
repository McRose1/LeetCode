package HashTable;

/*  981. Time Based Key-Value Store
    Create a timebased key-value store class TimeMap, that supports two operations.
    1. set(string key, string value, int timestamp)
        o Stores the key and value, along with the given timestamp.
    2. get(string key, int timestamp)
        o Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
        o If there are multiple such values, it returns the one with the largest timestamp_prev.
        o If there are no values, it returns the empty string ("").

    Example 1:
    Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    Output: [null,null,"bar","bar",null,"bar2","bar2"]
    Explanation:
    TimeMap kv;
    kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
    kv.get("foo", 1);  // output "bar"
    kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
    kv.set("foo", "bar2", 4);
    kv.get("foo", 4); // output "bar2"
    kv.get("foo", 5); //output "bar2"

    Example 2:
    Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
    Output: [null,null,null,"","high","high","low","low"]

    Note:
    1. All key/value strings are lowercase.
    2..All key/value strings have length in the range [1, 100]
    3. The timestamps for all TimeMap.set operations are strictly increasing.
    4. 1 <= timestamp <= 10^7
    5. TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*  TreeMap

 */
public class TimeBasedKeyValueStore {
    // <String, <timestamp, value>>
    Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        TreeMap<Integer, String> tree = map.get(key);
        Integer time = tree.floorKey(timestamp);
        return time != null ? tree.get(time) : "";
    }
}

/*  HashMap + BinarySearch

    Map<String, List<Pair<Integer, String>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String key, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<Pair<Integer, String>>());
        }
        map.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        List<Pair<Integer, String>> list = map.get(key);
        int i = Collections.binarySearch(list, new Pair<Integer, String>(timestamp, "{"), (a, b) -> Integer.compare(a.getKey(), b.getKey()));
        if (i >= 0) {
            return list.get(i).getValue();
        } else if (i == -1) {
            return "";
        } else {
            return list.get(-i - 2).getValue();
        }
    }
 */