package StudentLibrary;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class Book {

    private int id;
    private Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
        // approach 1
        // if we want to run tryLock(), we need to have if block
        // lock.tryLock(10, TimeUnit.MILLISECONDS); -> return boolean

        // approach 2
        // just lock(), if it is not aquire the lock, it will waiting until get the lock (no need while)
        lock.lock();
        System.out.println(student + " starts reading " + this);
        Thread.sleep(15000); // read for 15 seconds
        lock.unlock();
        System.out.println(student + " has finished reading " + this);
    }

    @Override
    public String toString() {
        return "Book #" + id;
    }
}
