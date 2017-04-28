package com.stfalcon.chatkit.data.datasource;

/**
 * im数据操作
 *
 * Created by livvy on 17-4-21.
 */

public interface IMessageDataSource {

    /**
     * 发送消息
     */
    void send(String message,int type,long roomId);

    /**
     * 删除消息
     */
    void delete(int messageId);

    /**
     * 获取消息
     */
    void getMessages(int page);

    /**
     * 获取dialog列表
     */
    void getDialogs();

}
