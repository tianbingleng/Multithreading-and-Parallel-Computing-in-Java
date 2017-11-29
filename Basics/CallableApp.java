package Basics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianbingleng on 28/11/2017.
 */
public class CallableApp {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Future<String> future = executorService.submit(new Processor3(i + 1));
            list.add(future);
        }

        for (Future<String> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}

// callable interface good at return things
// like mergesort in the multiple thread.
class Processor3 implements Callable<String> {

    private int id;

    public Processor3 (int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception { // notice, it throws exception
        Thread.sleep(1000);
        return "ID: " + id;
    }
}