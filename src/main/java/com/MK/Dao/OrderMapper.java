package com.MK.Dao;

import com.MK.domain.Order;
import com.MK.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT *,o.id oid,u.id uid FROM orders o,user u where o.uid=u.id")
    @Results({
            @Result(column = "oid",property = "id"),
            @Result(column = "ordertime",property = "ordertime"),
            @Result(column = "total",property = "total"),
            @Result(column = "uid",property = "user.id"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "password",property = "user.password"),
            @Result(column = "birthday",property = "user.birthday"),

    })
    public List<Order> findOrderAndUser();


    @Select("SELECT * FROM orders")
    @Results({
//            先封装自身属性
            @Result(column = "id",property = "id"),
            @Result(column = "ordertime",property = "ordertime"),
            @Result(column = "total",property = "total"),
            @Result(
                    property = "user",//封装自身中的哪一个属性
                    column = "uid",//根据自身哪一个属性查找另一张表中的数据
                    javaType = User.class,//要封装的实体类型
                    //根据哪一个接口中的方法查询数据
                    one=@One(select = "com.MK.Dao.UserMapper.findByid")
            )

    })
    public List<Order> findOrderAndUser2();


    @Select("select * from orders where uid=#{id}")
    public List<Order> findByUid(int id);

}
