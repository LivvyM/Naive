package com.stfalcon.chatkit.emoji.interfaces;

import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.chatkit.emoji.data.PageEntity;


public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
