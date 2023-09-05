package com.lrm.service;

import com.lrm.po.Blog;
import com.lrm.po.Tag;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    List<Blog> listBlog();
    List<Blog> listBlogWithQuary(String query);

    List<Blog> listRecommendBlogTop(Integer size);

    Long countBlog();

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> getBlogsByTypeId(Long id);

    Map<String,List<Blog>> archiveBlog();

    List<Blog> getBlogsByTagId(Long id);
}
