# 543. Diameter of Binary Tree

需要同时维护 2 个值：全局变量 max 和当前 node 的高度

max = Math.max(max, lh + rh);

height: return Math.max(lh, rh) + 1;
