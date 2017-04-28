package com.stfalcon.chatkit.data.db;

import com.stfalcon.chatkit.data.models.Message;
import com.stfalcon.chatkit.data.models.MessageAction;
import com.stfalcon.chatkit.data.models.MessageStatusType;
import com.stfalcon.chatkit.data.models.Room;
import com.stfalcon.chatkit.data.models.User;
import com.stfalcon.chatkit.data.observer.MessageObserverManager;

import java.util.Date;

/**
 *
 * Created by livvy on 17-4-27.
 */

public class IMUtils {


    private static IMUtils instance = new IMUtils();

    private IMUtils(){}

    public static IMUtils getInstance(){
        return instance;
    }

    public boolean insertMessage(String message,int messageType,long roomId,User mineUser){
        Message entity = build(message,messageType,mineUser,MessageStatusType.TYPE_SEND_ING,roomId);
        Long messageId = MessageUtilDao.getInstance().insert(entity);
        if(messageId == null){
            return false;
        }
        /**
         * 发布新增message事件
         */
        MessageObserverManager.getInstance().notifyObserver(MessageAction.ACTION_SEND,entity);
        return true;
    }

    public void sendMessageSuccess(){

    }

    public boolean deleteMessage(int messageId){
        return false;
    }

    public boolean updateMessage(int messageId){
        return false;
    }

    private Message build(String message,int messageType,User user,int messageStatus,long roomId){
        Message entity = new Message();
        entity.setCreatedAt(new Date());
        entity.setSendStatus(messageStatus);
        entity.setMessageType(messageType);
        entity.setText(message);
        if(roomId == -1){
//            entity.setRId(RoomUtilDao.getInstance().insertRoom(buildRoom()));
        }else{
            entity.setRId(roomId);
        }
        entity.setUId(UserUtilDao.getInstance().getOrInsertUser(user));
        return entity;
    }

    private Room buildRoom(String roomName,String roomContent,String roomPicture,int roomType){
        Room room = new Room();
        room.setRoomName(roomName);
        room.setRoomContent(roomContent);
        room.setRoomPicture(roomPicture);
        room.setRoomType(roomType);
        return room;
    }

}
