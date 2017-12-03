package Collections;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by tianbingleng on 30/11/2017.
 */
public class CyclicBarrierApp {
    /*
    * (prev)Latch -> Multiple threads can wait for each other
    *   - A CyclicBarrier is used in situations where you want to create a group of tasks to perform work
    *    in parallel + wait until they are all finished before
    *    -> something like join()
    *    -> something like CountDownLatch
    *
    *    CountDownLatch: one-shot event
    *    CyclicBarrier: it can be REUSED over and over again
    *
    *    *CyclicBarrier has a barrier action: a runnable, that will run automatically when the count reaches 0 !!
    *
    *    new CyclicBarrier(N) -> N threads will wait for each other
    *
    *    WE CAN NOT REUSE LATCHES BUT WE CAN REUSE CylicBarriers ---> reset() !!!
    * */

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            // will be called after 5 threads finished
            public void run() {
                System.out.println("All the tasks are finished..");
                System.out.println("Now we are able to start sth..");
            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker1(i+1, barrier));
        }

        executorService.shutdown();
    }
}

class Worker1 implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker1(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }
    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id "+ id+" start working..");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with id "+ id+" finished..");

        try {
            cyclicBarrier.await();
            // we can run after task are finished
            System.out.println("After await...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return ""+id;
    }

}



