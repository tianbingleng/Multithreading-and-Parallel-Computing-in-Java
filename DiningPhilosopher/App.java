package DiningPhilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;

        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHER];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHER);
            // p0, c0, c1
            // p1, c1, c2
            // p2, c2, c3
            // p3, c3, c4
            // p4, c4, c0
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHER; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }

            // main thread - play time
            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            // force setting each philosopher to be full
            for (Philosopher p : philosophers) {
                p.setFull(true);
            }

        } finally {
            executorService.shutdown(); // not guarantee

            // wait until it is terminated
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for (Philosopher p : philosophers) {
                System.out.println("Philosopher eats " + p.getCounter() + " times.");
            }
        }
    }
}
