# 269. Alien Dictionary
给定一串已经根据某种字典序排序的字符串，总结出该字典序排序的字母大小顺序

首先是需要转换问题


给定 wrt -> wrf -> er -> ett -> rftt
- wrt
- wrf
  - t -> f

- wrf
- er
  - w -> e

- er
- ett
 - r -> t
 
- ett
- rftt
  - e -> r
  
可以将这些看作有向图中的 edge，构建有向图，并且统计入度

拿到图和入度数组，后面就是普通的拓扑排序

难点主要在建图和统计入度
