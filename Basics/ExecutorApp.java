package Basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianbingleng on 28/11/2017.
 */
public class ExecutorApp {
    /*
    * Executor
    *
    *   1.) ExecutorService executorService = Executors.newCachedThreadPool();
    *       - going to return an executorService that can dynamically REUSE threads
    *       - before starting a job -> it going to check whether there are any threads that
    *                                  finished the job... REUSE them
    *       - if there are no waiting threads -> it is going to create another NEW one.
    *       - good for the processor ... effective solution!!
    *
    *   2.) ExecutorService executorService = Executors.newFixedThreadPool();
    *       - maximize the number of threads
    *       - if we want to start a job -> if all the threads are busy, we have to wait for one to terminate.
    *         which means, we have to WAIT.
    *
    *   3.) ExecutorService executorService = Executors.newSingleThreadExecutor();
    *       - It uses a single thread for the job.
    *       execute() -> runnable + callable
    *       submit() -> runnable
    *
    * */

    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Worker3());
        }

        executorService.shutdown();

    }
}
class Worker3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}