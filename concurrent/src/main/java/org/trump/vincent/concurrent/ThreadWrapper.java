package org.trump.vincent.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent on 2017/10/12 0012.
 */
public class ThreadWrapper extends Wrapper
{
    public static void main(String[] args) throws InterruptedException {
      /*  Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.print("I will sleep 6 seconds.");
                    TimeUnit.SECONDS.sleep(6);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread handle = new Thread(run);
        handle.start();
        handle.join();
        System.out.print("finished");
        new ThreadWrapper().staticSynMothed();*/
//        final ThreadWrapper wrapper = new ThreadWrapper();
//      for(int i=0;i<20;i++){
//          new Thread(){
//              public void run(){
//                  new ThreadWrapper().staticSynMothed();
//              }
//          }.start();
//      }

        ThreadWrapper.member = 20;
        System.out.print(Wrapper.member);
    }


    public synchronized void synMethod(){
        /**
         * any executions
         */

        System.out.print(Thread.currentThread().getId()+"\t begin.\n");
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.print(Thread.currentThread().getId()+"\t end.\n");

    }

    // is equivalent to

    public void synMethod1(){
        synchronized(this){
            /**
             * any executions
             */
        }
    }

    // 1
    public static synchronized void staticSynMothed(){
        /**
         * any executions
         */
    }

    /**
     * Different:
     * The above indicate that all reference to the staticSynMethod should acquire the lock of ThreadWrapper Class Instance before.
     * [ Notice all references of static method are pointed Class Instance in which the static is ]
     * ******************************
     * The below imply that all invocation to the synClassMethod should acquire the lock of ThreadWrapper Class Instance before.
     */

    // 2
    public void synClassMethod(){
        synchronized (ThreadWrapper.class){
            /**
             *  any executions
             */
        }
    }

    //3
    public static void synStaticMethod(){
        synchronized (ThreadWrapper.class){
            /**
             *  any executions
             */
        }
    }
}

class Wrapper{
    public static int member = 10;
}