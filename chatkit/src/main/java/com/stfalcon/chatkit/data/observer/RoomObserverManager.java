package com.stfalcon.chatkit.data.observer;

import com.stfalcon.chatkit.data.models.MessageAction;
import com.stfalcon.chatkit.data.models.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by livvy on 17-4-28.
 */

public class RoomObserverManager implements RoomSubjectListener{


    private static RoomObserverManager observerManager;
    //观察者接口集合
    private List<RoomObserverListener> list = new ArrayList<>();

    /**
     * 单例
     */
    public static RoomObserverManager getInstance() {
        if (null == observerManager) {
            synchronized (RoomObserverManager.class) {
                if (null == observerManager) {
                    observerManager = new RoomObserverManager();
                }
            }
        }
        return observerManager;
    }

    /**
     * 加入监听队列
     */
    @Override
    public void add(RoomObserverListener observerListener) {
        list.add(observerListener);
    }

    /**
     * 通知观察者刷新数据
     */
    @Override
    public void notifyObserver(@MessageAction int action, Room room) {
        for (RoomObserverListener observerListener : list) {
            observerListener.observerUpdate(action,room);
        }
    }

    /**
     * 监听队列中移除
     */
    @Override
    public void remove(RoomObserverListener observerListener) {
        if (list.contains(observerListener)) {
            list.remove(observerListener);
        }
    }
}
