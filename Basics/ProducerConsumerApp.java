package Basics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 28/11/2017.
 */
public class ProducerConsumerApp {
    public static void main(String[] args) {
        // this we use the intrinsic lock here
        // since we use sync, locking on the same object
        Processor2 processor = new Processor2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Processor2 {
    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LIMIT) {
                    System.out.println("Waiting for removing items from the list..");
                    lock.wait();
                } else {
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                    // it got the change to notify other thread, but since it is the while loop
                    // we need to work it first, and until it is obtained the size == limit, then we wait
                    // after that, we will call the consumer methd.
                }

                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == BOTTOM) {
                    System.out.println("Waiting for adding items from the list..");
                    lock.wait();
                } else {
                    System.out.println("Removed: " + list.remove(--value));
                    lock.notify();
                    // this will notify other thread, after current thread is finish
                    // since it is in the while loop, it will keep remove until size == bottom, then it wait
                    // now we got the change to call producer thread
                }

                Thread.sleep(500);
            }
        }
    }
}

