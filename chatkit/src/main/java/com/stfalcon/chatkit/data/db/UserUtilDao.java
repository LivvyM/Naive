package com.stfalcon.chatkit.data.db;

import com.stfalcon.chatkit.data.models.User;

/**
 *
 * Created by livvy on 17-4-28.
 */

public class UserUtilDao {

    private static UserUtilDao instance = new UserUtilDao();

    private UserUtilDao(){}

    public static UserUtilDao getInstance(){
        return instance;
    }

    /**
     * 查询用户id
     *
     */
    public Long getUser(User user){
        return GreenDaoHelper.getDaoSession().getUserDao().getKey(user);
    }

    /**
     * 新增用户
     * 返回用户id
     */
    public long insertUser(User user){
        return GreenDaoHelper.getDaoSession().getUserDao().insert(user);
    }

    /**
     * 查询or新增用户
     *
     * 如果查询数据库中没有该用户.则需要向DB中新增user
     */
    public Long getOrInsertUser(User user){
        Long id = getUser(user);
        if( id == null){
            return insertUser(user);
        }
        return id;
    }
}
