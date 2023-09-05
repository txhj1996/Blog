package com.lrm.service;

import com.lrm.dao.BlogRepository;
import com.lrm.po.Blog;
import com.lrm.po.Tag;
import com.lrm.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;



    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getById(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getById(id);
        String content = blog.getContent();
        Blog blogCopy = new Blog();
        BeanUtils.copyProperties(blog,blogCopy);
        MarkdownUtils.markdownToHtml(content);
        blogCopy.setContent(MarkdownUtils.markdownToHtml(content));
        return blogCopy;
    }

    @Override
    public List<Blog> listBlog() {
        return blogRepository.getAll();
    }

    @Override
    public List<Blog> listBlogWithQuary(String query) {
        return blogRepository.getByQuery(query);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return blogRepository.getRecommendBlogs(size);
    }

    @Override
    public Long countBlog() {
        return blogRepository.countAll();
    }

    @Override
    public void saveBlog(Blog blog) {
        if (blog.getId() == null) {
            List<Tag> tags = blog.getTags();
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogRepository.save(blog);
            Long id = blog.getId();//useGeneratedKeys和keyProperty作用的原因，这里能获取到数据库表中生成id
            System.out.println("返回的id值为："+id); // 自增的id


            //再上面执行完中插入blog并返回自增的id后---》执行中间表的同步插入。
            blogRepository.insertTBlogTags(id,tags);
        } else {
            blog.setUpdateTime(new Date());
        }

    }

    @Override
    public void updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogRepository.deleteBlogTags(blog.getId());

        blogRepository.insertTBlogTags(blog.getId(),blog.getTags());
        blogRepository.update(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public List<Blog> getBlogsByTypeId(Long id) {
        return blogRepository.getByType(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> blogCreateYears = blogRepository.getBlogCreateYears();
        HashMap<String, List<Blog>> yearBlogMap = new HashMap<>();
        for (String blogCreateYear : blogCreateYears) {
            yearBlogMap.put(blogCreateYear,blogRepository.getByYear(blogCreateYear));
        }
        return yearBlogMap;
    }

    @Override
    public List<Blog> getBlogsByTagId(Long id) {
        return blogRepository.getBlogsByTagId(id);
    }


}
