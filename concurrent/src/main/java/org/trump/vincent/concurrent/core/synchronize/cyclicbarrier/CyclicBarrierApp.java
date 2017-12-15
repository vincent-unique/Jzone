package org.trump.vincent.concurrent.core.synchronize.cyclicbarrier;

import java.util.concurrent.*;

/**
 * Created by Vincent on 2017/11/27 0027.
 */
public class CyclicBarrierApp {

    private final static int THREAD_NUM = 5;
    static class CoreTask implements Runnable{
        private CyclicBarrier commonBarrier;
        public CoreTask(CyclicBarrier barrier){
            this.commonBarrier = barrier;
        }

        public void run(){
            /**
             * doing something
             */
            System.out.println(Thread.currentThread().getId()+" starting.\n");

            try {
                commonBarrier.await();
                TimeUnit.SECONDS.sleep(1);
                commonBarrier.reset();
                System.out.println(Thread.currentThread().getId()+" finished.\n");
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException be){
                be.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("All Wait 3s.\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        });
        ExecutorService tpool = Executors.newFixedThreadPool(THREAD_NUM);
        for(int i=0;i<THREAD_NUM;i++){
            tpool.execute(new CoreTask(cyclicBarrier));
        }
    }
}
