package Array;

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

public class BullsandCows {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];   // 存 digit(0-9)
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                if (count[secret.charAt(i) - '0']++ < 0) cows++;    // 如果在 secret 出现，index + 1
                if (count[guess.charAt(i) - '0']-- > 0) cows++;     // 如果在 guess 出现，index - 1
            }
        }
        return bulls + "A" + cows + "B";
    }
}
