package com.MK;

import com.MK.Dao.OrderMapper;
import com.MK.Dao.UserMapper;
import com.MK.domain.Order;
import com.MK.domain.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    private UserMapper mapper;
    private OrderMapper orderMapper;
    @Before
    public void before() throws IOException {
        InputStream resources=Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder().build(resources);
        SqlSession sqlSession = sqlSessionFactoryBuilder.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
        orderMapper=sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void test1(){
        List<User> userList = mapper.findAll();
        System.out.println(userList);
    }
    @Test
    public void test2(){
        User user = mapper.findByid(2);
        System.out.println(user);
    }
    @Test
    public void test3(){
        User user=new User();
        user.setUsername("tom");
        user.setPassword("1234");
        mapper.savebirthday(user);
    }
    @Test
    public void test4(){
        User user=new User();
        user.setId(9);
        user.setUsername("tom");
        user.setPassword("1233");
        user.setBirthday(new Date());
        mapper.updateBirthday(user);
    }

    @Test
    public void test5(){
        mapper.delectuser(10);
    }

    @Test
    public void test6(){
        List<Order> list = orderMapper.findOrderAndUser();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void test7(){
        List<Order> list=orderMapper.findOrderAndUser2();
        for (Order order : list) {
            System.out.println(order);
        }
    }
}
