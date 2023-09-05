package com.lrm.dao;

import com.lrm.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
@Mapper
@Repository
public interface TypeRepository{


//    @Query("select t from Type t")
//    List<Type> findTop(Pageable pageable);

    void save(Type type);

    Type findOne(Long id);

    Type findByName(String name);

//    Page<Type>  findAll();

    List<Type> findAll();

    void delete(long id);

    void update(Type type);

    List<Type> findTop(@Param("size") int size);
}
