package String;

/*  383. Ransom Note
    Given an arbitrary ransom note string and another string containing letters from all the magazines,
    write a function that will return true if the ransom note can be constructed from the magazines;
    otherwise, it will return false.

    Each letter in the magazine string can only be used once in your ransom note.

    Note:
    You may assume that both strings contain only lowercase letters.

    canConstruct("a", "b") -> false
    canConstruct("aa", "ab") -> false
    canConstruct("aa", "aab") -> true
 */

// Array: Time = O(N) Space = O(1)
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) return false;
        if (magazine.length() < ransomNote.length()) return false;

        int[] count = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            // 记录每个字符的个数
            count[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            // 在 ransomNote 中减去字符的个数并判断是否 < 0
            if (--count[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
