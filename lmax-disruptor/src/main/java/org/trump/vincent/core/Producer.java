package org.trump.vincent.core;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import org.trump.vincent.event.Event;

public class Producer<E extends Event> {

    private RingBuffer<E> ringBuffer;

    public Producer(RingBuffer<E> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void publish(E event){
        this.ringBuffer.publishEvent(new EventTranslator<E>() {
            @Override
            public void translateTo(E event, long sequence) {

                //TODO
            }
        });
    }
}
