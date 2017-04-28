package com.stfalcon.chatkit.data.db;

import com.stfalcon.chatkit.data.models.Room;
import com.stfalcon.chatkit.data.models.RoomDao;

import java.util.List;

/**
 * Created by livvy on 17-4-28.
 */

public class RoomUtilDao {

    private static RoomUtilDao instance = new RoomUtilDao();

    private RoomUtilDao(){}

    public static RoomUtilDao getInstance(){
        return instance;
    }


    /**
     * 获取房间
     */
    private Long getRoomId(Room room){
        try{
            return GreenDaoHelper.getDaoSession().getRoomDao().getKey(room);
        }catch (Throwable ex){
            return null;
        }
    }

    private boolean hashKey(Room room){
        return GreenDaoHelper.getDaoSession().getRoomDao().hasKey(room);
    }

    private void updateRoom(Room room){
        GreenDaoHelper.getDaoSession().getRoomDao().update(room);
    }

    public Long insertRoom(Room room){
        return GreenDaoHelper.getDaoSession().getRoomDao().insert(room);
    }

    public Long getOrInsertRoomId(long roomId){
        List<Room> room = GreenDaoHelper.getDaoSession().getRoomDao().queryBuilder().where(RoomDao.Properties.RId.eq(roomId)).list();
        if(room == null || room.size() == 0){
            return null;
        }
        return room.get(0).getRId();
    }

    public Long insertOrReplace(Room room){
        try{
            return GreenDaoHelper.getDaoSession().getRoomDao().insertOrReplace(room);
        }catch (Throwable ex){
            return null;
        }
    }
}
