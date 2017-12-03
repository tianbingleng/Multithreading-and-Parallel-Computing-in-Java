package Collections;

import java.util.concurrent.*;

/**
 * Created by tianbingleng on 30/11/2017.
 */
public class BlockQueueApp {
    /*
    *    BlockingQueue -> an interface that represents a queue that is thread safe.
    *    -put items or take items from it...
    *
    *    For example: one thread putting items into the queue and
    *                   another thread taking items from it at the same time !!
    *                 We can do it with producer-consumer pattern !!!
    *
    *    put() putting items to the queue
    *    take() taking items from the queue
    *
    *    In below code, we have two cases, and notice, if the queue is full, the thread to put() will wait.
    * */

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}

class FirstWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        int counter = 0;
        while(true) {
            try {
                blockingQueue.put(counter);
                System.out.println("Putting items to the queue... " + counter);
                counter++;
                //Thread.sleep(1000); // case1
                Thread.sleep(300); // case2
                // if the queue reach the capacity (10), this thread will wait!!!! (case2)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        while(true) {
            try {
                int number = blockingQueue.take();
                System.out.println("Taking items from the queue... " + number);
                //Thread.sleep(300); // case1
                Thread.sleep(1000); //case2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

