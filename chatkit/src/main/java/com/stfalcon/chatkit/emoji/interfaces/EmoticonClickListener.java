package com.stfalcon.chatkit.emoji.interfaces;

public interface EmoticonClickListener<T> {

    void onEmoticonClick(T t, int actionType, boolean isDelBtn);
}
