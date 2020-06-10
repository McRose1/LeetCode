# 291. Word Pattern 2
290 题的 follow-up，唯一的改变就是现在 str 没有空格分开，需要去判断每一种分隔的可能性，难度一下子从 easy 上升到了 hard

## 回溯法
dfs(pattern, str)，递归每次如果有 <key, value> 配对成功，就取剩余的 pattern 和 str 作为参数继续 dfs

递归的出口是 pattern 和 str 同时遍历到尾部

递归内部每次以 pattern.charAt(0) 为 key，先判断 map 中是否存在该 key

1. 如果存在，就从 str 里找到对应的 value
    1. 如果 str 开头甚至都不是该 value，直接返回 false，
    2. 否则，说明已经找到一对 <key,value>，继续向后遍历
2. 如果不存在，在已经确认 key 的情况下，去尝试每一种 value 的可能
    1. 如果 map 已经存在该 value，则跳过
    2. 如果不存在，就可以将这对 <key,value> 放入 map 中，继续向后遍历，记得这里需要回溯，以便后面的 dfs 遍历
