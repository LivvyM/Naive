package com.stfalcon.chatkit.data.observer;

import com.stfalcon.chatkit.data.models.Message;

/**
 * 观察者接口
 *
 * Created by livvy on 17-4-21.
 */

public interface MessageObserverListener {

    void observerUpdate(int action, Message message);//刷新操作
}
