package com.lrm.dao;

import com.lrm.po.Tag;
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
public interface TagRepository {


//    @Query("select t from Type t")
//    List<Type> findTop(Pageable pageable);

    void save(Tag tag);

    Tag findOne(Long id);

    Tag findByName(String name);

//    Page<Type>  findAll();

    List<Tag> findAll();

    void delete(long id);

    void update(Tag tag);

    List<Tag> getTagListByStringArray(String[] idarray);

    List<Tag> getTagListByBlogId(@Param("blogId") long blogId);


    List<Tag> findTop(@Param("size") Integer size);
}
