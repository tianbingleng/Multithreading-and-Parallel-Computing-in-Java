package Basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * Created by tianbingleng on 28/11/2017.
 */
public class SemaphoreApp {
    /*
    * Semaphore
    *   - Semaphore maintains a set of permits.
    *   - acquire() -> if a permit is available then takes it
    *   - release() -> adds a permit
    *
    *   Semaphore just keeps a count of number available.
    *   new Semaphore(int permits, boolean fair) !!!
    *       - fairness parameter:
    *       if it is set to be true --> the longest-waited thread will get the lock
    *       false --> there is no access order! (starvation may occur!)
    *
    * */

    enum Downloader {
        INSTANCE;
        private Semaphore semaphore = new Semaphore(2, true); // 3 thread at a time

        public void downloadData() {
            try {
                semaphore.acquire();
                download();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        private void download() {
            System.out.println("Downloading data from web..");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        ExecutorService exService = Executors.newCachedThreadPool();

        // total 12 thread
        // but each time only allow 2 at the same time
        for (int i = 0; i < 12; i++) {
            exService.execute(new Runnable() {
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }

        exService.shutdown();
    }
}
