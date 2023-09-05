package com.lrm.po;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limi on 2017/10/14.
 */


public class Tag {


    private Long id;
    private String name;

    private int tagCount;


    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public int getTagCount() {
        return tagCount;
    }

    public void setTagCount(int tagCount) {
        this.tagCount = tagCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagCount=" + tagCount +
                ", blogs=" + blogs +
                '}';
    }
}
