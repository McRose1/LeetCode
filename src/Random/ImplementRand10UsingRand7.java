package Random;

/*  470. Implement Rand10() Using Rand7()
    Given a function rand7 which generates a uniform random integer in the range 1 to 7,
    write a function ran10 which generates a uniform random integer in the range 1 to 10.
    DO NOT use system's Math.random().

    Example 1:
    Input: 1
    Output: [7]

    Example 2:
    Input: 2
    Output: [8,4]

    Example 3:
    Input: 3
    Output: [8,1,10]

    Note:
    1. rand7 is predefined.
    2. Each testcase has one argument: n, the number of times that rand10 is called.

    Follow up:
    1. What is the expected value for the number of calls to rand7() function?
    2. Could you minimize the number of calls to rand7()?
 */

/*  Rejection Sampling
    The main idea is when you generate a number in the desired range, output that number immediately.
    If the number is out of the desired range, reject it and resample again.
    As each number in the desired range has the same probability of being chosen, a uniform distribution is produced.
    Calling rand7() twice will get us row and column index that corresponds to a unique position in the table.
    Since 49 is not a multiple of 10, we have to use rejection sampling. Our desired range is integers from 1 to 10, which we can return the answer immediately.
    If not (the integer falls between 41 to 49), we reject it and repeat the whole process again.
    ((row - 1) * 7 + col - 1) % 10 + 1
 */
public class ImplementRand10UsingRand7 {
    public int ran10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = (row - 1) * 7 + col - 1;
         // idx = (row - 1) * 7 + col;
        } while (idx >= 40);
       // while (idx > 40);
        return idx % 10 + 1;
     // return (idx - 1) % 10 + 1;
    }

    private int rand7() {
        return 0;
    }
}

/*  Utilizing out-of-range samples
    The idea is that we should not throw away the out-of-range samples,
    but instead use them to increase our chances of finding an in-range sample on the successive call to ran7.
    In the event that we could not generate a number in the desired range (1 to 40), it is equally likely that each number of 41 to 49 would be chosen.
    In other words, we are able to obtain integers in the range of 1 to 9 uniformly.
    Now, run rand7() again to obtain integers in the range of 1 to 63 uniformly. Apply rejection sampling where the desired range is 1 to 60.
    If it is not (61 to 63), we at least obtain integers of 1 to 3 uniformly. Run rand7() again to obtain integers in the range of 1 to 21 uniformly.
    The desired range is 1 to 20, and in the unlikely event we get a 21, we reject it and repeat the entire process again.

        int a, b, idx;
        while (true) {
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;
            if (idx <= 40)
                return 1 + (idx - 1) % 10;
            a = idx - 40;
            b = rand7();
            // get uniform dist from 1 - 63
            idx = b + (a - 1) * 7;
            if (idx <= 60)
                return 1 + (idx - 1) % 10;
            a = idx - 60;
            b = rand7();
            // get uniform dist from 1 - 21
            idx = b + (a - 1) * 7;
            if (idx <= 20)
                return 1 + (idx - 1) % 10;
        }
 */