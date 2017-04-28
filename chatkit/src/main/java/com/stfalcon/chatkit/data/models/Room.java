package com.stfalcon.chatkit.data.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 房间
 *
 * Created by livvy on 17-4-28.
 */
@Entity
public class Room {

    @Id(autoincrement = true)
    Long rId;

    String roomName;

    String roomContent;

    String roomPicture;

    Date newTime;

    int roomType;

    int unReadNum = 0;

    int sort;

    @Generated(hash = 1450022691)
    public Room(Long rId, String roomName, String roomContent, String roomPicture,
            Date newTime, int roomType, int unReadNum, int sort) {
        this.rId = rId;
        this.roomName = roomName;
        this.roomContent = roomContent;
        this.roomPicture = roomPicture;
        this.newTime = newTime;
        this.roomType = roomType;
        this.unReadNum = unReadNum;
        this.sort = sort;
    }

    @Generated(hash = 703125385)
    public Room() {
    }

    public Long getRId() {
        return this.rId;
    }

    public void setRId(Long rId) {
        this.rId = rId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomContent() {
        return this.roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public String getRoomPicture() {
        return this.roomPicture;
    }

    public void setRoomPicture(String roomPicture) {
        this.roomPicture = roomPicture;
    }

    public Date getNewTime() {
        return this.newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public int getRoomType() {
        return this.roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getUnReadNum() {
        return this.unReadNum;
    }

    public void setUnReadNum(int unReadNum) {
        this.unReadNum = unReadNum;
    }

}
