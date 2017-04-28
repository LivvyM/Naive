package com.stfalcon.chatkit.data.models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.stfalcon.chatkit.data.models.MessageAction.ACTION_DELETE;
import static com.stfalcon.chatkit.data.models.MessageAction.ACTION_SEND;
import static com.stfalcon.chatkit.data.models.MessageAction.ACTION_UPDATE;

/**
 *
 * Created by livvy on 17-4-28.
 */
@IntDef({ACTION_SEND,ACTION_DELETE,ACTION_UPDATE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageAction {

    int ACTION_SEND = 0x01;

    int ACTION_DELETE = 0x02;

    int ACTION_UPDATE = 0x03;
}
