package ForkJoinFramework;

import java.util.concurrent.RecursiveAction;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        // this is NO return value
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split task... " +simulatedWork);
            // split the tasks by two
            SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork / 2);

            simpleRecursiveAction1.fork(); // insert into forkjoin pool , maybe not execute if no thread available
            simpleRecursiveAction2.fork(); // insert into forkjoin pool , maybe not execute if no thread available
        } else {
            System.out.println("No need for Parallel execution, sequential algorithm is OK. "+ simulatedWork);

        }
    }
}
