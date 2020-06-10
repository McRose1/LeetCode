package HashTable;

/*  299. Bulls and Cows
    You are playing the following Bulls and Cows game with your friend:
    You write down a number and ask your friend to guess what the number is.
    Each time your friend makes a guess, you provide a hint that indicates how many digits
    in said guess match your secret number exactly in both digit and position (called "bulls")
    and how many digits match the secret number but locate in the wrong position (called "cows").
    Your friend will use successive guesses and hints to eventually derive the secret number.

    Write a function to return a hint according to the secret number and friend's guess,
    use A to indicate the bulls and B to indicate the cows.

    Please note that both secret number and friend's guess may contain duplicate digits.

    Example 1:
    Input: secret = "1807", guess = "7810"
    Output: "1A3B"

    Example 2:
    Input: secret = "1123", guess = "0111"
    Output: "1A1B"

    Note:
    You may assume that the secret number and your friend's guess only contain digits,
    adn their lengths are always equal.
 */

/*  One Pass
    0-9 这十个数字如果在 secret 中出现，就让 count++，如果在 guess 中出现过，就让 count--
 */
public class BullsandCows {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        // 存 digit(0-9)
        int[] count = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            } else {
                // 当前数的 count 小于 0, 说明之前在 guess 中出现过, 和 secret 当前的数匹配
                if (count[s] < 0) cows++;
                // 当前数的 count 小于 0, 说明之前在 secret 中出现过, 和 guess 当前的数匹配
                if (count[g] > 0) cows++;
                // secret 中的数, 计数加 1
                count[s]++;
                // guess 中的数, 计数减 1
                count[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}

/*  Two Pass
    第一遍遍历计算所有相同位置大小相同的个数，分别用大小为10的数组记录两个字符串每个数字出现的次数;
    第二次遍历数组，相同的次数为对应位置的最小值

        int[] sCount = new int[10];
        int[] gCount = new int[10];
        int bulls = 0;
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            // 相同位置相同数字
            if (s == g) {
                bulls++;
            } else {
                sCount[s]++;
                gCount[g]++;
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            cows += Math.min(sCount[i], gCount[i]);
        }
        return bulls + "A" + cows + "B";
 */
