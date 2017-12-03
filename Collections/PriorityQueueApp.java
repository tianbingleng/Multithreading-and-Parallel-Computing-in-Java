package Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by tianbingleng on 30/11/2017.
 */
public class PriorityQueueApp {
    /*
    *    PriorityBlockingQueue -> it implements the BlockingQueue interface.
    *    (It is similar as PriorityQueue, but it is thread-safe, we don't need to worry about synchronization.)
    *
    *    - unbounded concurrent queue
    *    - it uses the same ordering rules as the java.util.PriorityQueue class (->have to implement the Comparable interface)
    *           The comparable interface will determine what will the order in the queue.
    *
    *    - The priority can be the same, compare() == 0 case
    *
    *    - No null items!!
    * */

    public static void main(String[] args) {

        //BlockingQueue<String> queue = new PriorityBlockingQueue<>();
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();


        FirstWorker1 firstWorker = new FirstWorker1(queue);
        SecondWorker1 secondWorker = new SecondWorker1(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}

class FirstWorker1 implements Runnable {

    private BlockingQueue<Person> blockingQueue;
    //private BlockingQueue<String> blockingQueue;

    public FirstWorker1(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
            try {
                // set 1
//                blockingQueue.put("B");
//                blockingQueue.put("H");
//                blockingQueue.put("F");
//                Thread.sleep(1000);
//                blockingQueue.put("A");
//                Thread.sleep(1000);
//                blockingQueue.put("E");

                // set 2
                blockingQueue.put(new Person(10, "Bob"));
                blockingQueue.put(new Person(20, "Devin"));
                blockingQueue.put(new Person(30, "Gorge"));
                Thread.sleep(1000);
                blockingQueue.put(new Person(40, "Allen"));
                Thread.sleep(1000);
                blockingQueue.put(new Person(50, "Cathy"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

class SecondWorker1 implements Runnable {

    private BlockingQueue<Person> blockingQueue;
    //private BlockingQueue<String> blockingQueue;

    public SecondWorker1(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                Thread.sleep(1000);
                System.out.println(blockingQueue.take());
                Thread.sleep(1000);
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person other) {
        return name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return name+"-"+age;
    }
}