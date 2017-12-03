package ForkJoinFramework;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class ForkJoinPoolApp2 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        //int workload = 20;
        int workload = 120;
        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(workload);
        System.out.println(pool.invoke(simpleRecursiveTask));

    }
}
