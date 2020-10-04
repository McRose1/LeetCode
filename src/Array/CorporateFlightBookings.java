package Array;

/*  1109. Corporate Flight Bookings
    There are n flights, and they are labeled from 1 to n.
    We have a list of flight bookings.
    The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
    Return an array answer of length n, representing the number of seats booked on each flight in order of their label.

    Example:
    Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
    Output: [10,55,45,25,25]

    Constraints:
        o 1 <= bookings.length <= 20000
        o 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
        o 1 <= bookings[i][2] <= 10000
 */

/*  BIT Tree: Time = O(n) Space = O(1)

 */
public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];
        for (int[] booking : bookings) {
            answer[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                answer[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            answer[i] += answer[i - 1];
        }
        return answer;
    }
}

/*  Brute Force

        int[] answer = new int[n];
        for (int[] booking : bookings) {
            int i = booking[0];
            int j = booking[1];
            int k = booking[2];
            for (int idx = i; idx <= j; idx++) {
                answer[idx - 1] += k;
            }
        }
        return answer;
 */