package org.trump.vincent.rabbitmq.model;

/**
 * Created by Vincent on 2017/12/5 0005.
 */
public class MessageModel<M> {
    public String getId() {
        return id;
    }

    public MessageModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getRoutineKey() {
        return routineKey;
    }

    public MessageModel setRoutineKey(String routineKey) {
        this.routineKey = routineKey;
        return this;
    }

    public M getContent() {
        return content;
    }

    public MessageModel setContent(M content) {
        this.content = content;
        return this;
    }

    private String id;
    private String routineKey;
    private M content;

}
