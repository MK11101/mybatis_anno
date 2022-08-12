package com.MK;

import com.MK.Dao.OrderMapper;
import com.MK.Dao.UserMapper;
import com.MK.domain.Order;
import com.MK.domain.User;
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

public class MyBatisTest2 {

    private UserMapper mapper;
    @Before
    public void before() throws IOException {
        InputStream resources=Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder().build(resources);
        SqlSession sqlSession = sqlSessionFactoryBuilder.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void test(){
        List<User> list = mapper.findorder();
        for (User user : list) {
            System.out.println(user);
        }
    }


}
