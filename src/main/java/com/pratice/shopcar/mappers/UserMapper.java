package com.pratice.shopcar.mappers;

import com.pratice.shopcar.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    Integer insert(User user);
    User findByUsername(User user);

    User findByUid(Integer uid);

    void updateUser(User user);
    Integer updatePassword(Integer uid, String password, @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);

    Integer updateAvatar(Integer uid, String avatar, String modifiedUser, Date modifiedTime);

    List<User> findAllUser();

    void updateUserIsDelete(Integer uid);
}
