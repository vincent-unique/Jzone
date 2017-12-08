package org.trump.vincent.kafka.core;

import kafka.consumer.Consumer;

/**
 * Created by Vincent on 2017/12/8 0008.
 */
public interface Woker<T> {
    void run(T t);
}
