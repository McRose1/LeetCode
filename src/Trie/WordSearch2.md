# 212. Word Search 2

和第一题居然不在同一类目里面，大大说明了 Trie Tree 对于这题起到的重要作用

第一题是典型的回溯题，给定一个矩阵和一个字符串，判断在这个矩阵里的字母能否练成这个字符串，限制条件是只用考虑小写字母和大写字母，并且矩阵大小不会超过 200 x 200，字符串长度不会超过 1000

但在这题里，字符串变成了字符串数组，输出矩阵可以拼出的字符串数组里的字符串，把这些 valid 的字符串放在 list 里面输出，并且第一题的限制条件都取消了，只剩下只用考虑 26 个小写字母

所以 input 可能会有无数个单词组成，**如果还是用 Word Search 1 的方法来做，肯定会超时！需要对回溯进行剪枝**

并且我们发现，**单词数量越多，越可能有 common prefix，预处理这些单词，可以为搜索提速**

回溯，是解决这题的主干，然而，在回溯过程中，人们会更经常地遇到这样的问题：

是否存在任何包含特定前缀的单词，而不是是否有一个字符串作为单词存在于字典中。

因为如果我们知道给定前缀的字典中不存在任何单词匹配，那么我们就不需要进一步探索某个方向。

而这，将大大减少探测空间，从而提高回溯算法的性能。

并且在 hint 里明显说明：需要优化回溯来通过 larger test，需要做到早些停止回溯

并且提示我们如果当前的 candidate 不是目标单词的前缀，就可以马上停止回溯

借此来引出 Trie Tree

所以我们首先需要构建属于这题的 Trie Tree，就是把输入的字符串数组都加进 Trie Tree 中

![#212](/src/images/%23212.png)

接着就是遍历矩阵递归调用带有回溯的 DFS，套路和第一题相同，visit 过就标记为 '#'，回溯的时候改回原来的字母

不过这题的 DFS 还是存在很多细节需要考虑的，值得反复回来看！！！

着重注意 isWord、TrieNode.word 的意义