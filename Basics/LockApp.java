package Basics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianbingleng on 28/11/2017.
 */
public class LockApp {
    /*
    * ReentrantLock
    *   - It has the same behavior as the "sychronized approach"
    *   - of course it has some additional features
    *
    *   new ReentrantLock(boolean fairnessParameter)
    *       - fairness parameter:
    *       if it is set to be true --> the longest-waited thread will get the lock
    *       false --> there is no access order! (starvation may occur!)
    *
    * Important:
    *   We have to use try-catch block when doing critical section
    *   - that may throw exceptions
    *   - we call unlock() in the finally block !!!
    *
    * */

    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment() {
        //t1, t2 can run this in the same time
        // this is critical section
        lock.lock();

        try {
            for (int i = 0; i < 10000; i++)
                count++;
        } finally {
            lock.unlock();
        }

        // this will have try-catch, we make cure NO deadlock here
        // because it is always unlock if it is in exception
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter = "+count);

    }
}
