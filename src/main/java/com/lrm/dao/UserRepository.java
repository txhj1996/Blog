package com.lrm.dao;

import com.lrm.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by limi on 2017/10/15.
 */
@Mapper
@Repository
public interface UserRepository{

    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
