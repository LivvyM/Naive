package com.stfalcon.chatkit.data.observer;

import com.stfalcon.chatkit.data.models.Message;
import com.stfalcon.chatkit.data.models.MessageAction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by livvy on 17-4-21.
 */

public class MessageObserverManager implements MessageSubjectListener {

    private static MessageObserverManager observerManager;
    //观察者接口集合
    private List<MessageObserverListener> list = new ArrayList<>();

    /**
     * 单例
     */
    public static MessageObserverManager getInstance() {
        if (null == observerManager) {
            synchronized (MessageObserverManager.class) {
                if (null == observerManager) {
                    observerManager = new MessageObserverManager();
                }
            }
        }
        return observerManager;
    }

    /**
     * 加入监听队列
     */
    @Override
    public void add(MessageObserverListener observerListener) {
        list.add(observerListener);
    }

    /**
     * 通知观察者刷新数据
     */
    @Override
    public void notifyObserver(@MessageAction int action, Message message) {
        for (MessageObserverListener observerListener : list) {
            observerListener.observerUpdate(action,message);
        }
    }

    /**
     * 监听队列中移除
     */
    @Override
    public void remove(MessageObserverListener observerListener) {
        if (list.contains(observerListener)) {
            list.remove(observerListener);
        }
    }

}
