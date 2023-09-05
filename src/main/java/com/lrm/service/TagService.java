package com.lrm.service;

import com.lrm.po.Tag;

import java.util.List;

public interface TagService {
    void saveTag(Tag type);

    Tag getTag(Long id);

    Tag getTagByName(String name);



    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    List<Tag> getTagsByBlogId(long blogId);

    void updateTag(Long id, Tag tag);

    void deleteTag(Long id);


}
