package com.stfalcon.chatkit.data.models;

import com.stfalcon.chatkit.commons.models.IUser;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * user model
 *
 * Created by livvy on 17-4-21.
 */
@Entity
public class User implements IUser{

    @Id(autoincrement = true)
    Long uId;
    String name;
    String avatar;
    boolean online;

    @Generated(hash = 1486309143)
    public User(Long uId, String name, String avatar, boolean online) {
        this.uId = uId;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUId() {
        return this.uId;
    }
    public void setUId(Long uId) {
        this.uId = uId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public boolean getOnline() {
        return this.online;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }
    @Override
    public String getId() {
        return null;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getAvatar() {
        return this.avatar;
    }
}
