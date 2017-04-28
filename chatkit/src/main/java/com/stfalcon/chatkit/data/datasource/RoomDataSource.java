package com.stfalcon.chatkit.data.datasource;

import com.stfalcon.chatkit.data.db.RoomUtilDao;
import com.stfalcon.chatkit.data.models.Message;
import com.stfalcon.chatkit.data.models.MessageAction;
import com.stfalcon.chatkit.data.models.Room;
import com.stfalcon.chatkit.data.observer.MessageObserverListener;
import com.stfalcon.chatkit.data.observer.MessageObserverManager;

/**
 * Created by livvy on 17-4-28.
 */
public class RoomDataSource implements MessageObserverListener,IRoomDataSource{


    public RoomDataSource(){
        MessageObserverManager.getInstance().add(this);
    }

    @Override
    public void getRooms() {

    }

    @Override
    public void observerUpdate(int action, Message message) {
        if(action == MessageAction.ACTION_SEND){
            Room room = message.getRoom();
            room.setUnReadNum(room.getUnReadNum() + 1);
            Long roomId = RoomUtilDao.getInstance().insertOrReplace(room);
            if(roomId != null){
                /**
                 * 新增或修改数据成功
                 */
            }
        }
    }

    @Override
    public void recycle(){
        MessageObserverManager.getInstance().remove(this);
    }
}
