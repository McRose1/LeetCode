package BinarySearch;

/*  374. Guess Number Higher or Lower
    We are playing the Guess Game. The game is as follows:
    I pick a number from 1 to n. You have to guess which number I picked.
    Every time you guess wrong, I'll tell you whether the number is higher or lower.
    You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

    -1: My number is lower
    1: My number is higher
    0: Congrats! You got it!

    Example:
    Input: n = 10, pick = 6
    Output: 6
 */
import BinarySearch.GuessGame;

//  Binary Search: Time = O(logn) Space = O(1)
public class GuessNumberHigherorLower extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

/*  Brute Force: Time = O(n) Space = O(1)

        for (int i = 1; i < n; i++) {
            if (guess(i) == 0) {
                return i;
            }
        }
        return n;
 */

/*  Ternary Search: Time = O(log3(n)) Space = O(1)
    Ternary Search does more comparisons than Binary Search in the worst case.

        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0)
                return mid1;
            if (res2 == 0)
                return mid2;
            else if (res1 < 0)
                high = mid1 - 1;
            else if (res2 > 0)
                low = mid2 + 1;
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
 */