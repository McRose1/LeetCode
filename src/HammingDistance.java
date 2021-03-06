/*
    The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    Given two integers x and y, calculate the Hamming distance.

    Note: 0 <= x, y < 2^31

    Example:
    Input: x = 1, y = 4
    Output: 2
 */

public class HammingDistance {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
