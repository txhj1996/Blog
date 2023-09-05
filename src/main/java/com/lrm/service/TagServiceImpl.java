package com.lrm.service;

import com.lrm.NotFoundException;
import com.lrm.dao.TagRepository;
import com.lrm.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        return tagRepository.findTop(size);
    }

    @Override
    public List<Tag> listTag(String ids) {
        String[] idarray = ids.split(",");
        return tagRepository.getTagListByStringArray(idarray);
    }

    @Override
    public List<Tag> getTagsByBlogId(long blogId) {
        return tagRepository.getTagListByBlogId(blogId);
    }

    @Override
    public void updateTag(Long id, Tag tag) {
        Tag tag1 = tagRepository.findOne(id);
        if (tag1 == null) {
            throw new NotFoundException("该tag不存在");
        }
        BeanUtils.copyProperties(tag,tag1);
        tagRepository.update(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }
}
