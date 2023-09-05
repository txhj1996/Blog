package com.lrm.po;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by limi on 2017/10/14.
 */

public class Type {


    private Long id;


    private String name;
    private List<Blog> blogs;

    private int typeCount;

    public int getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }


    public Type(String name) {
        this.name = name;
    }

    public Type() {
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
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeCount=" + typeCount +
                '}';
    }
}
