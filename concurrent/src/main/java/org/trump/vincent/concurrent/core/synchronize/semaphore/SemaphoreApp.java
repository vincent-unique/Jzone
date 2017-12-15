package org.trump.vincent.concurrent.core.synchronize.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent on 2017/11/27 0027.
 */
public class SemaphoreApp {

    static class CoreTask implements Runnable{
        private Semaphore authorization;

        public CoreTask(Semaphore authorization){
            this.authorization = authorization;
        }
        @Override
        public void run(){
            try {
                this.authorization.acquire();
                System.out.println(Thread.currentThread().getId()+" has aquired the previlidge and start core tasks.");
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                System.out.println(Thread.currentThread().getId()+" release the previlidge.");
                this.authorization.release();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService tpool = Executors.newFixedThreadPool(10);
        for(int i=0;i<20;i++){
            tpool.execute(new SemaphoreApp.CoreTask(semaphore));
        }
    }
}
