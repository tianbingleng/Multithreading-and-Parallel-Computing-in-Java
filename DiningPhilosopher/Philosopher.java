package DiningPhilosopher;

import java.util.Random;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class Philosopher implements Runnable{

    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;
    private volatile boolean isFull = false; // volatile make sure get from main memory, not from catch

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {

        try {
            // whether stop or not (controlled by main)
            while (!isFull) {
                think();
                if (leftChopstick.pickUp(this, State.LEFT)) {
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating...");
        this.eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public int getCounter() {
        return this.eatingCounter;
    }

    public void setFull(boolean full) {
        this.isFull = full;
    }

    @Override
    public String toString() {
        return " Philosopher " + this.id;
    }

}
