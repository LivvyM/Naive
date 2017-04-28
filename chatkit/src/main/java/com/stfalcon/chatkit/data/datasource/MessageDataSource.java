package com.stfalcon.chatkit.data.datasource;

import com.stfalcon.chatkit.data.db.IMUtils;
import com.stfalcon.chatkit.data.models.User;

/**
 * 数据层
 * <p>
 * Created by livvy on 17-4-24.
 */

public class MessageDataSource implements IMessageDataSource {

    private final User mineUser;

    public MessageDataSource(User mineUser) {
        this.mineUser = mineUser;
    }

    @Override
    public void send(String message, int type, long roomId) {
        if (mineUser == null) {
            return;
        }
        /**
         * 消息发送
         *
         * 先生成DB数据后，在通过socket发送。
         * socket收到反馈之后，修改DB数据状态为success。
         * socket长时间未收到反馈后，修改DB数据状态为fail。
         */
        boolean isCompleteDb = IMUtils.getInstance().insertMessage(message, type, roomId, mineUser);
        if (isCompleteDb) {
            /**
             * 新增入DB成功
             */
            try {
                /**
                 * 模拟网路传递
                 */
                Thread.sleep(1000 * 3);

            } catch (Throwable ex) {

            }
        } else {
            /**
             * 新增入DB失败
             */
        }
    }

    @Override
    public void delete(int messageId) {
    }

    @Override
    public void getMessages(int page) {
    }

    @Override
    public void getDialogs() {
    }
}
