package com.stfalcon.chatkit.data.observer;

import com.stfalcon.chatkit.data.models.Room;

/**
 * Created by livvy on 17-4-28.
 */

public interface RoomSubjectListener {

    void add(RoomObserverListener observerListener);
    void remove(RoomObserverListener observerListener);

    /**
     * 通知
     * @param action    行为
     */
    void notifyObserver(int action, Room room);
}
