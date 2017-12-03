package Collections;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by tianbingleng on 30/11/2017.
 */
public class ConcurrentMapApp {
    /*
    *    ConcurrentHashMap -> it implements the BlockingQueue interface.
    *    (It is similar as HashMap, but it is thread-safe, we don't need to worry about synchronization.)
    *
    * */

    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread(new FirstWorker2(map)).start();
        new Thread(new SecondWorker2(map)).start();


        // List<String> list = new ArrayList<>();
        // List<String> list2 = new Collections.synchronizedList(list);
        // if you really need it, use Collections.synchronizedList -> sync this list
        // but it will be very low in this example
        // it can also use for other DS, map, set etc.

    }
}

class FirstWorker2 implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public FirstWorker2(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {

        try {
            map.put("B", 1);
            map.put("D", 2);
            Thread.sleep(1000);
            map.put("E", 3);
            map.put("A", 4);
            Thread.sleep(1000);
            map.put("C", 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker2 implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public SecondWorker2(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            System.out.println(map.get("C"));
            Thread.sleep(1000);
            System.out.println(map.get("B"));
            System.out.println(map.get("D"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}