package Collections;

import java.util.concurrent.*;

/**
 * Created by tianbingleng on 30/11/2017.
 */
public class DelayQueueApp {
    /*
    *    DelayQueue -> This is an unbounded BlockingQueue of objects that implement the Delayed interface.
    *
    *    - DelayQueue keeps the elements internally until certain delay has expired.
    *
    *    - an object can only be taken from the queue when its delay has **expired** !!!
    *
    *    - We CANNOT place null items in the queue. - The queue is sorted so that the
    *       object at the head has a delay that has **expired** for the longest time.
    *
    *    - If no delay has expired, then there is no head element and poll() will return null.
    *
    *    - size() return the count of both expired and unexpired items!!
    *
    *    For server-based application, framework!
    *
    * */

    public static void main(String[] args) {

        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000, "This is the 1st message")); //1
            queue.put(new DelayedWorker(10000, "This is the 2nd message")); //3
            queue.put(new DelayedWorker(4000, "This is the 3rd message")); //2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take()); // order 132, based on the expired time order
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class DelayedWorker implements Delayed {

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (duration < ((DelayedWorker) o).getDuration()) {
            return -1;
        } else if (duration > ((DelayedWorker) o).getDuration()) {
            return 1;
        } else {
            return 0;
        }
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String toString() {
        return message;
    }
}
