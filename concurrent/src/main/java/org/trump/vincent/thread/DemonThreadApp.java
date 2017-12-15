package org.trump.vincent.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent on 2017/12/15 0015.
 */
public class DemonThreadApp {

    static class DemonTask extends Thread{
        public DemonTask(){
            super();
            setDaemon(true);
        }
        public void run() {
            while (true) {
                System.out.println("I am a demon and I am alived.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        System.out.println("I am main and will start demon.");
        DemonThreadApp.DemonTask demon = new DemonTask();
        demon.start();
        new Thread(){
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("I am non demon and will end.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
