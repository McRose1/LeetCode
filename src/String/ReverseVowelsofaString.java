package String;

/*  345. Reverse Vowels of a String
    Write a function that takes a string as input and reverse only the vowels of a string.

    Example 1:
    Input: "hello"
    Output: "holle"

    Example 2:
    Input: "leetcode"
    Output: "leotcede"

    Note: The vowels does not include the letter "y".
 */

//  Array + Two Pointers: Time = O(n) Space = O(n)
public class ReverseVowelsofaString {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        String vowels = "aeiouAEIOU";
        char[] array = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && vowels.indexOf(array[left]) == -1) {
                left++;
            }
            while (left < right && vowels.indexOf(array[right]) == -1) {
                right--;
            }
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(array);   // char[].toString() 会把[]也读进来，所以需要用String.valueOf(array)
        // Arrays.toString(array) -> [h, o, l, l, e]
    }
}

/*  HashSet + Two Pointers: Time = O(n) Space = O(n)

        Set<Character> vowels = new HashSet<>();
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        char[] characters = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !vowels.contains(characters[j])) {
                i++;
            }
            while (i < j && !vowels.contains(characters[j])) {
                j--;
            }
            char temp = characters[i];
            characters[i++] = characters[j];
            characters[j--] = temp;
        }
        return new String(characters);
 */