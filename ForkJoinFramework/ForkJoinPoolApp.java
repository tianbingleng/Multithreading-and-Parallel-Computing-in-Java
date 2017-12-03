package ForkJoinFramework;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class ForkJoinPoolApp {
    /*
    *   fork()  - asynchronously execute the given task in the pool
    *           We can call this on  RecursiveAction or RecursiveTask<T>
    *
    *   join()  - return the result of the computation when it is done
    *
    *
    *   invoke() - execute the given task + return its result upon completion
    *
    *
    * */

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        //int workload = 20;
        int workload = 1200000;
        SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(workload);
        pool.invoke(simpleRecursiveAction);

    }



}
