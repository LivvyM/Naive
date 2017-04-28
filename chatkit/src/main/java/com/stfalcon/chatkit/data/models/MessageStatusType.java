package com.stfalcon.chatkit.data.models;

/**
 * 消息类型
 *
 * Created by livvy on 17-4-27.
 */

public interface MessageStatusType {

    /**
     * 发送消息状态-正在发送中
     */
    int TYPE_SEND_ING = 0x01;


    /**
     * 发送消息状态-发送成功
     */
    int TYPE_SEND_SUCCESS = 0x02;

    /**
     * 发送消息状态-发送失败
     */
    int TYPE_SEND_FAIL = 0x03;
}
