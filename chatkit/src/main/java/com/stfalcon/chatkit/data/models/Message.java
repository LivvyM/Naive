package com.stfalcon.chatkit.data.models;

import com.stfalcon.chatkit.commons.models.IMessage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

/**
 *
 * message model
 *
 * Created by livvy on 17-4-21.
 */
@Entity
public class Message implements IMessage{

    @Id(autoincrement = true)
    Long mId;
    /**
     * 内容
     */
    String text;
    /**
     * 用户
     */
    long uId;

    @ToOne(joinProperty = "uId")
    User user;


    /**
     * 房间
     */
    long rId;
    @ToOne(joinProperty = "rId")
    Room room;

    /**
     * 发送时间
     */
    Date createdAt;
    /**
     * 消息类型
     */
    int messageType;

    /**
     * 发送状态
     */
    int sendStatus;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 859287859)
    private transient MessageDao myDao;
    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;
    @Generated(hash = 170076450)
    private transient Long room__resolvedKey;

    @Generated(hash = 2034400673)
    public Message(Long mId, String text, long uId, long rId, Date createdAt, int messageType,
            int sendStatus) {
        this.mId = mId;
        this.text = text;
        this.uId = uId;
        this.rId = rId;
        this.createdAt = createdAt;
        this.messageType = messageType;
        this.sendStatus = sendStatus;
    }

    @Generated(hash = 637306882)
    public Message() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getSendStatus() {
        return this.sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public long getUId() {
        return this.uId;
    }

    public void setUId(long uId) {
        this.uId = uId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 283961491)
    public User getUser() {
        long __key = this.uId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
                user__resolvedKey = __key;
            }
        }
        return user;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 524410202)
    public void setUser(@NotNull User user) {
        if (user == null) {
            throw new DaoException(
                    "To-one property 'uId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            uId = user.getUId();
            user__resolvedKey = uId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    @Override
    public String getId() {
        return String.valueOf(mId);
    }

    public long getRId() {
        return this.rId;
    }

    public void setRId(long rId) {
        this.rId = rId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1646141523)
    public Room getRoom() {
        long __key = this.rId;
        if (room__resolvedKey == null || !room__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RoomDao targetDao = daoSession.getRoomDao();
            Room roomNew = targetDao.load(__key);
            synchronized (this) {
                room = roomNew;
                room__resolvedKey = __key;
            }
        }
        return room;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1174084230)
    public void setRoom(@NotNull Room room) {
        if (room == null) {
            throw new DaoException(
                    "To-one property 'rId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.room = room;
            rId = room.getRId();
            room__resolvedKey = rId;
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 747015224)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMessageDao() : null;
    }
}
