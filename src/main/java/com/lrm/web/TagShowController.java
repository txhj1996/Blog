package com.lrm.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrm.po.Blog;
import com.lrm.po.Tag;
import com.lrm.service.BlogService;
import com.lrm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, Model model) {
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);

        PageHelper.startPage(pageNum,5,"create_time desc");
        List<Blog> blogs =blogService.getBlogsByTagId(id);
        PageInfo<Blog> pages = new PageInfo<>(blogs);
        model.addAttribute("page",pages);


//        model.addAttribute("page", blogService.listBlog(id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
