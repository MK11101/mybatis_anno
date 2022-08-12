package com.MK.Dao;

import com.MK.domain.Order;
import com.MK.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    public List<User> findAll();
    @Select("select * from user where id=#{id}")
    public User findByid(int id);

    public List<User> findByids(List list);
    public User findByCondition(User user);

    @Insert("insert into user values (#{id},#{username},#{password},#{birthday})")
    public void savebirthday(User user);
    public User selectbirthday(int id);

    @Update("update user set username=#{username},password=#{password},birthday=#{birthday} where id=#{id}")
    public void updateBirthday(User user);

    @Delete("delete from user where id=#{id}")
    public void delectuser(int id);


    //一对多查询
    @Select("select * from user")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "birthday",property = "birthday"),
            @Result(
                    property = "orderList",
                    column = "id",
                    javaType =List.class,
                    many = @Many(select = "com.MK.Dao.OrderMapper.findByUid")
            )

    })
    public List<User> findorder();
}
