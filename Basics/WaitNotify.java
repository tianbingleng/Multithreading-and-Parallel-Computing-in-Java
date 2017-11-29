package Basics;

/**
 * Created by tianbingleng on 28/11/2017.
 */
public class WaitNotify {
    public static void main(String[] args) {

        // this we use the intrict lock here
        // since we use sync, locking on the same object
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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


    }
}

class Processor {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer method...");
            wait();
            //wait(1000); wait with timeout
            System.out.println("Again, producer method...");
        }
    }

    public void consume() throws InterruptedException {

        Thread.sleep(1000);

        synchronized (this) {
            System.out.println("Consumer method...");
            notify(); //non-determinate, no guarentee which thread is wait up
            //Thread.sleep(3000);//this is runnning now, all other thread will wait until this method is finished
        }
    }
}

