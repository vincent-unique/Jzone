package org.trump.vincent.concurrent.core.synchronize.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent on 2017/11/27 0027.
 */
public class CountDownLatchApp {
    private final static int THREAD_NUM = 5;

    static class PreTask implements Runnable{
        private CountDownLatch counter = new CountDownLatch(THREAD_NUM);

        public PreTask(CountDownLatch counter){
            this.counter =counter;
        }

        @Override
        public void run(){
            /**
             * Doing something
             */
            System.out.println("Member doing something.");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Member is in place.");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                this.counter.countDown();
            }
        }
    }

    static class MainAndThen implements Runnable{
        private CountDownLatch countPointer ;
        public MainAndThen(CountDownLatch waiter){
            this.countPointer = waiter;
        }

        @Override
        public void run(){
            try {
                System.out.println("Start Main tasks...");
                this.countPointer.await();
                tasks();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        private void tasks(){
            System.out.println("Main tasks end.");
        }
    }

    public static void scheduler() {
        ExecutorService tpool = Executors.newFixedThreadPool(THREAD_NUM+1);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        for(int i=0;i<THREAD_NUM;i++){
            tpool.execute(new CountDownLatchApp.PreTask(countDownLatch));
        }
        tpool.execute(new CountDownLatchApp.MainAndThen(countDownLatch));
    }

    public static void main(String[] args) {
        scheduler();
    }
}
