package org.trump.vincent.concurrent.core.synchronize.cyclicbarrier;

import java.util.concurrent.*;

/**
 * Created by Vincent on 2017/11/27 0027.
 */
public class CBDeformation {
    private final static int THREAD_NUM = 5;

    static class CoreTask implements Runnable{
        private CyclicBarrier counter;

        public CoreTask(CyclicBarrier reusableCounter){
            this.counter = reusableCounter;
        }

        @Override
        public void run(){
            /**
             * Doing something
             */
            try {
                System.out.println("Doing: all task starting.\n");
                TimeUnit.SECONDS.sleep(2);
                this.counter.await();
//                System.out.println("just mark : all task finished.\n");
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException be){
                be.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final CyclicBarrier reusableCounter = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("all task finished.");
            }
        });
        ExecutorService tpool = Executors.newFixedThreadPool(THREAD_NUM);
        for(int i=0;i<THREAD_NUM;i++){
            tpool.execute(new CBDeformation.CoreTask(reusableCounter));
        }

        while (reusableCounter.getNumberWaiting()!=0){
            System.out.println("Cyclic Barrier Counter :"+reusableCounter.getNumberWaiting()+"\n");
        }

        reusableCounter.reset();

        for (int i=0;i<THREAD_NUM;i++){
            tpool.execute(new Thread(){
                @Override
                public void run(){
                    try {
                        System.out.println("New Task LOOP.\n");
                        reusableCounter.await();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    catch (BrokenBarrierException be){
                        be.printStackTrace();
                    }
                }
            });
        }

    }
}
