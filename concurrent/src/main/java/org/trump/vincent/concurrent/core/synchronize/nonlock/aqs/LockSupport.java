package org.trump.vincent.concurrent.core.synchronize.nonlock.aqs;

import sun.misc.Unsafe;

/**
 * Created by Vincent on 2017/12/15 0015.
 */
public class LockSupport {

    private static Unsafe unsafe;

    protected Unsafe getUnsafe(){
        /**
         * TODO
         * build unsafe by reflection
         */
        return null;
    }

    public void park(Object blocker){
    }

    public void unpark(Thread thread){
        unsafe.unpark(thread);
    }
}
