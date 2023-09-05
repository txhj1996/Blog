package com.lrm.dao;

import com.lrm.po.Blog;
import com.lrm.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
@Mapper
@Repository
public interface BlogRepository {


    Blog getById(long id);

    List<Blog> getAll();

    List<Blog> getByQuery(String query);


//    void updateViews(long id);

    void save(Blog blog);

    void update(Blog blog);

    void delete(long id);

    List<Blog> getByType(@Param("typeId") Long id);

    List<String> getBlogCreateYears();

    List<Blog> getByYear(@Param("createYear") String blogCreateYear);

    Long countAll();

    void insertTBlogTags(@Param("blog_id")Long blog_id,@Param("tag_id") List<Tag> tag_id);

    void deleteBlogTags(@Param("blogs_id") Long id);

    List<Blog> getBlogsByTagId(@Param("tag_id")Long id);

    List<Blog> getRecommendBlogs(@Param("size") Integer size);
}
