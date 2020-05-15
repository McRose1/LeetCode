package SlidingWindow;

/*  3. Longest Substring Without Repeating Characters
    Given a string, find the length of the longest substring without repeating characters.

    Example 1:
    Input: "abcabcbb"
    Output: 3

    Example 2:
    Input: "bbbbb"
    Output: 1

    Example 3:
    Input: "pwwkew"
    Output: 3
 */
import java.util.HashMap;
import java.util.Map;
/*  Sliding Window + HashMap: Time = O(n) Space = O(min(m, n))
    The solution with HashSet requires at most 2n steps. In fact, it could be optimized to require only n steps.
    We could define a mapping of the characters to its index. Then we can skip the characters immediately when we found a repeated character.
    The reason is that if s[j] have a duplicate in the range [i,j) with index j', we don't need to increase i little by little.
    We can skip all the elements in the range [i,j'] and let i to be j'+1 directly.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {             // "pwwkew"
        int n = s.length(), ans = 0;
        // current index of character
        Map<Character, Integer> map = new HashMap<>();
        // try to extend the range [slow, fast]
        for (int slow = 0, fast = 0; fast < n; fast++) {
            if (map.containsKey(s.charAt(fast))) {
                slow = Math.max(map.get(s.charAt(fast)), slow); // slow=max(2,0)=2
            }
            ans = Math.max(ans, fast - slow + 1); // ans=0-0+1=1;2
            // already fast' + 1
            map.put(s.charAt(fast), fast + 1);  // {p,1}; {w,2}
        }
        return ans;
    }
}

/*  Sliding Window + Array: Time = O(n) Space = O(m)
    用数组记录 index 代替 HashMap

        int n = s.length(), ans = 0;                // "pwwkew"
        // current index of character
        int[] index = new int[128];
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {        // j=0,i=0; j=1
            i = Math.max(index[s.charAt(j)], i);    // i=max(index[p],0)=0; max(0,0)=0
            ans = Math.max(ans, j - i + 1);         // ans=max(0,0-0+1)=1; max(1,2)=2
            // 记录下一个 valid 起始点的坐标
            index[s.charAt(j)] = j + 1;             // index[p]=0+1=1; index[w]=2
        }
        return ans;
 */

/*  Sliding Window + HashSet: Time = O(n) Space = O(min(m, n))
    If a substring Sij from index i to j-1 is already checked to have no duplicate characters.
    We only need to check if s[j] is already in the substring sij.
    using HashSet as a sliding window
    slow pointer 一步一步移，比 HashMap 直接将 slow pointer 移动到 valid index 要慢很多

        int n = s.length();                             // "pwwkew"
        HashSet<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                // right boundary
                set.add(s.charAt(j++));             // {p},j=1; {w},j=2
                ans = Math.max(ans, j - i);         // max(0,1)=1; max=2
            }
            else {
                // left boundary
                set.remove(s.charAt(i++));          // remove(p),i=1; remove(w),i=2
            }
        }
        return ans;
 */

/*  my version(HashSet)

        if (s == null || s.length() == 0) return 0;
        int max = 1;
        int left = 0;
        int right = 1;
        if (s.length() == 1) {
            return 1;
        }
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                max = Math.max(max, right - left);
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
 */

/*  my version(optimized brute force): Time = O(n^2) -> O(n*128) Space = O(128)

        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int temp = 0;
        int max = Integer.MIN_VALUE;
        boolean[] seen = new boolean[128];

        for (int i = 0; i < len; i++) {
            int j = i;
            while (j < len && !seen[s.charAt(j)]) {
                temp++;
                seen[s.charAt(j)] = true;
                max = Math.max(max, temp);
                j++;
            }
            Arrays.fill(seen, false);
            seen[s.charAt(i)] = true;
            temp = 1;
        }
        return max;
 */

/*  Brute Force + HashSet: Time = O(n^3) -> O(n*128^2) TLE Space = O(min(n, m))

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
 */

