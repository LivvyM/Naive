package com.stfalcon.chatkit.data.observer;

import com.stfalcon.chatkit.data.models.Message;

/**
 * 被观察者接口
 *
 * Created by livvy on 17-4-21.
 */

public interface MessageSubjectListener {

    void add(MessageObserverListener observerListener);
    void remove(MessageObserverListener observerListener);

    /**
     * 通知
     * @param action    行为
     * @param message   数据
     */
    void notifyObserver(int action, Message message);

}
