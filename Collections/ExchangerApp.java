package Collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Exchanger;

/**
 * Created by tianbingleng on 30/11/2017.
 */
public class ExchangerApp {
    /*
    *    With the help of Exchanger -> two threads can exchange objects.
    *
    *    exchange() -> exchanging objects is done via one of the two exchange() methods
    *
    *    For example: generic algorithm, training neural networks.
    *
    *    Note: since it is named exchanger, there must be TWO objects in the exchange function.
    *    which from difference threads.
    *
    *
    * */

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }
}

class FirstThread implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true) {
            counter++;
            System.out.println("FirstThread counter: "+ counter);
            try {
                //exchange with SecondThread, swap the counter
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true) {
            counter--;
            System.out.println("SecondThread counter: "+ counter);
            try {
                //exchange with SecondThread, swap the counter
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
