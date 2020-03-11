/*
   Given an integer (signed 32 bits), write a function to determine if it is a power of four.

   Example 1:
   Input: 16
   Output: true

   Example 2:
   Input: 5
   Output: false

   Follow up: Could you do it without using any loop/recursion?
*/

public class PowerofFour {
    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        while (num % 4 == 0) {
            num /= 4;
        }
        return num == 1;
    }
}


