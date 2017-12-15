package org.trump.vincent.concurrent.core.synchronize;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Vincent on 2017/12/15 0015.
 */

/**
 * Spin Lock for non-blocking and awiat
 */
public class SpinLock {
    private static Unsafe unsafe = null;
    private static final long valueOffset;
    private volatile int value = 0;
    static {
        try {
            unsafe=getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(SpinLock.class
                    .getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * SpinLock and non-thread blocking
     */
    public void lock() {
        for (;;) {
            int newV = value + 1;
            if (unsafe.compareAndSwapInt(this, valueOffset, 0, newV)){
                return ;
            }
        }
    }

    /**
     * release the lock
     */
    public void unlock() {
        unsafe.compareAndSwapInt(this, valueOffset, 1, 0);
    }


    /**
     * the public getInstance method of singleton Unsafe allow to be called by JDK library Implementaion Class
     * Here ,we need to define another get methond with Refletion
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
