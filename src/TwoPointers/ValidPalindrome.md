# 125. Valid Palindrome 
有效回文数

considering only **alphanumeric** characters and ignoring cases

alphanumeric!!! 是“字母数字组成的”的意思！！！

tag 标记为双指针

双指针两头往中间走，

如果相等，同时聚拢

发现自己不会处理 string 里的标点符号

被自己蠢哭了啊。。。这就是不清楚 inbuild method 的下场

Character.isLetterOrDigit(char ch)

这样就不用考虑用 split 了呀，毕竟标点符号这么多，delimiter 会写到死的呀

一开始用 split 想的时候整个思路就完全反了

想着没有分割符以后就可以 while（相等）然后双指针距离，最后判断 left 是否大于等于 right（2020.5.20 更新：其实是可以这样做的）

但是如果不处理分隔符，直接拿原来的 string 来做，就得反着来

遇到 delimiter 就指针聚拢，跳过分隔符

如果两个指针指的都不是分隔符，那就可以开始比较，如果不相等就直接 return false，否则双指针继续靠拢

非常值得注意的一点是，外循环里while(left < right)，在内循环和内判断里面也得加上left < right 这个条件，切记！

-----------------------------------
2020.5.20

和普通的有效回文数有些不同，给的是包含特殊字符的字符串，这也是这题为什么这么多 downnote 的原因，根本每讲清楚哪些要考虑哪些要省略

所以每次遇到这些特殊字符我们需要跳过而不是进行比较

试了几个 test case 发现其实除了字母，**数字也算，不能跳过数字，而是需要进入字符比较的条件里** e.g. s = "0P" -> **Character.isLetterOrDigit()**

并且要记得小写字母和大写字母不区分，所以干脆全部转化为小写字母





