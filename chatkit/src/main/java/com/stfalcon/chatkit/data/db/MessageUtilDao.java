package com.stfalcon.chatkit.data.db;

import com.stfalcon.chatkit.data.models.Message;

/**
 * message db util
 *
 * Created by livvy on 17-4-28.
 */

public class MessageUtilDao {

    private static MessageUtilDao instance = new MessageUtilDao();

    private MessageUtilDao(){}

    public static MessageUtilDao getInstance(){
        return instance;
    }

    /**
     * 新增message数据
     * 返回messageId
     */
    public Long insert(Message message){
        try{
            return GreenDaoHelper.getDaoSession().getMessageDao().insert(message);
        }catch (Throwable ex){
            return null;
        }
    }
}
