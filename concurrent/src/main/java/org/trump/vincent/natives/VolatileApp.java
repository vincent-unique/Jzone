//package org.trump.vincent.natives;

public class VolatileApp {
    private static volatile VolatileApp app;
    private static boolean isworking = true;
    public static VolatileApp getInstance(){
        if(app==null){
            synchronized (VolatileApp.class){
                if(app==null){
                    app = new VolatileApp();
                }
            }
        }
        return app;
    }

    public void run(){
        new Thread("Volatile-App"){

            public void run(){
                int flag = 0;
                while (isworking){
                    if(flag>=20){
                        isworking = false;
                    }
                }
            }
        }.start();
    }
}
