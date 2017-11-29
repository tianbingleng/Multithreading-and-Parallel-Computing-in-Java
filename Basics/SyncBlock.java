package Basics;

/**
 * Created by tianbingleng on 28/11/2017.
 */
public class SyncBlock {

    private static int count1 = 0;
    private static int count2 = 0;


    // What is Intrinsic lock?
    //It is when we do not instantiate an Object lock1 = new Object() to use as a lock but we use the class itself for this purpose

    // use two locks, can run it in sync block run at the same time.
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

//    public synchronized static void add() {
//        count1++;
//    }
//
//    public synchronized static void addAgain() {
//        count2++;
//    }

    // Basics.SyncBlock.class

    public static void add() {
        synchronized (lock1) {
            count1++;
        }
    }

    public static void addAgain() {
        synchronized (lock2) {
            count2++;
        }
    }

    public static void compute() {
        for (int i = 0; i < 100; i++) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               compute();
           }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
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

        System.out.println("Count1 = "+count1+", Count2 = "+count2);

    }
}
