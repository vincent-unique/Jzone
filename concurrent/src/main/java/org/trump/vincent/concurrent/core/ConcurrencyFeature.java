package org.trump.vincent.concurrent.core;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent on 2017/10/17 0017.
 */
public class ConcurrencyFeature {

//    private static int origin = 0;

    public static void main(String[] args) {
        Runnable run = new Runnable() {
            private  int origin = 0;
            @Override
            public void run() {
                for (int i=0;i<2;i++) {
                    int value = ++(origin);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.print(Thread.currentThread().getId()+":"+value+"\n");
                }
            }
        };
       for(int i=0;i<100;i++){
           new Thread(run).start();
       }
    }
}
