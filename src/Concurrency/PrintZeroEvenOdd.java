package Concurrency;

/*  1116. Print Zero Even Odd
    Suppose you are given the following code:

    class {
        ZeroEvenOdd {
            public ZeroEvenOdd(int n) {...}     // constructor
            public void zero(printNumber) {...} // only output 0's
            public void even(printNumber) {...} // only output even numbers
            public void odd(printNumber) {...}  // only output odd numbers
        }
    }

    The same instance of ZeroEvenOdd will be passed to three different threads:
        1. Thread A will call zero() which should only output 0's.
        2. Thread B will call even() which should only output even numbers.
        3. Thread C will call odd() which should only output odd numbers.

    Each of the threads is given a printNumber method to output an integer.
    Modify the given program to output the series 010203040506... where the length of the series must be 2n.

    Example 1:
    Input: n = 2
    Output: "0102"
    Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd().
    "0102" is the correct output.

    Example 2:
    Input: n = 5
    Output: "0102030405"
 */

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/*  Semaphore

 */
public class PrintZeroEvenOdd {
    private int n;
    Semaphore zeroSem, oddSem, evenSem;

    public PrintZeroEvenOdd(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        // 起始 lock (odd&even)
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSem.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }
}

/*  Synchronization：busy waiting

    private int n;
    private int mutex = 0;

    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // need mutex = 0 to unlock
            while (mutex != 0) {
                wait();
            }
            printNumber.accept(0);
            if (i % 2 == 0) {
                mutex = 1;
            } else {
                mutex = 2;
            }
            notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            // need mutex = 2 to unlock
            while (mutex != 2) {
                wait();
            }
            printNumber.accept(i);
            mutex = 0;
            notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            // need mutex = 1 to unlock
            while (mutex != 1) {
                wait();
            }
            printNumber.accept(i);
            mutex = 0;
            notifyAll();
        }
    }
 */
