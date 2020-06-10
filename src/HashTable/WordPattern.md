# 290. Word Pattern 
## 四种情况
abba -> dog cat cat dog -> true       <key, value> 双向对应

abba -> dog cat cat fish -> false     <key, value> 双向均不对应

abba -> dog dog dog dog -> false      仅满足 <key -> value>

aaaa -> dog cat cat dog -> false      仅满足 <value -> key>

其实很简单，就是实现双向对应，一个小写字母对应唯一的单词，而一个单词同样要反过来唯一对应于一个小写字母

也就是说对 HashMap 里的 value 也有和对 key 同样的要求

map.containsKey() -> map.get(key).equals() -> 满足 key -> value 对应的唯一性

!map.containskey() -> !map.containsValue() -> 满足 value -> key 对应的唯一性

**也可用空间换时间：用 HashSet 专门存储 value 值，增加了 O(n) 的空间复杂度的同时免去了 map.containsValue() 这一 O(n) 的时间复杂度操作**
