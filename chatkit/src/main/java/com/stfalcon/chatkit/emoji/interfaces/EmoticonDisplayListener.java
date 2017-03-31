package com.stfalcon.chatkit.emoji.interfaces;

import android.view.ViewGroup;

import com.stfalcon.chatkit.emoji.adpater.EmoticonsAdapter;


public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
