package DiningPhilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class Chopstick {

    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    // whether can pickup or not
    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException{
        // how much time, we gonna try acquire the lock (then we lock)
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picks up " + state.toString() + " " + this);
            return true;
        }

        return false;
    }

    public void putDown(Philosopher philosopher, State state) throws InterruptedException {
        lock.unlock();
        System.out.println(philosopher + " puts down " + this);

    }

        @Override
    public String toString() {
        return "Chopstick" + id;
    }
}
