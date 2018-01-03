package org.trump.vincent.concurrent.core.synchronize.nonlock.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

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

        Unsafe unsafe = null;
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe)unsafeField.get(null);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return unsafe;
    }

    public void park(Object blocker){

    }

    public void unpark(Thread thread){
        unsafe.unpark(thread);
    }
}
