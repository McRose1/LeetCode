package Array;

/*  277. Find the Celebrity
    Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
    The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
    Now you want to find out who the celebrity is or verify that there is not one.
    The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
    You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
    You are given a helper function bool knows(a, b) which tells you whether A knows B.
    Implement a function int findCelebrity(n).
    There will be exactly one celebrity if he/she is in the party.
    Return the celebrity's label if there is a celebrity in the party.
    If there is no celebrity, return -1.

    Example 1:
    Input:
    graph = [
              [1,1,0],
              [0,1,0],
              [1,1,1]
                       ]
    Output: 1

    Example 2:
    Input:
    graph = [
              [1,0,1],
              [1,1,0],
              [0,1,1]
                       ]
    Output: -1

    Note:
    The directed graph is represented as an adjacency matrix,
    which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.

    Remember that you won't have direct access to the adjacency matrix.
 */
/*
    Optimized Solution: O(n) 扫两遍

                1 2 3 4 5 6
    Candidate         C

    1. Find a candidate by one pass: (make sure other people are not a celebrity)
    knows(i, j)
    By comparing a pair (i, j), we are able to discard one of them.
    if knows(i, j)
        i is guaranteed not to be celebrity
    otherwise
        j is not a celebrity

    Any person comes before candidate is guaranteed not a celebrity (previous candidate does not know him)
    Any person comes after candidate is not a celebrity (candidate does not know any person come after him)

    2. Make sure if the candidate is a celebrity by one pass
 */
/*
public class FindtheCelebrity {
    public int findCelebrity(int n) {
         int candidate = 0;
         for (int i = 0; i < n; i++) {
             if (knows(candidate, i)) {
                 candidate = i;
             }
         }
         for (int i = 0; i < n; i++) {
             if (i == candidate) {
                 continue;
             }
             if (!knows(i, candidate) || know(candidate, i)) {
                 return -1;
             }
         }
         return candidate;
    }
}
*/
/*
    Brute Force: O(n^2)

    for each person a - (check if a is a celebrity: 2 conditions):
        go through all other people b
            check !knows(a, b) && check knows(b, a)
 */
