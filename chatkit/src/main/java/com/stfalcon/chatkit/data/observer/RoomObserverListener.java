package com.stfalcon.chatkit.data.observer;

import com.stfalcon.chatkit.data.models.Room;

/**
 * Created by livvy on 17-4-28.
 */

public interface RoomObserverListener {

    void observerUpdate(int action, Room room);//刷新操作
}
