package com.lrm.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrm.po.Blog;
import com.lrm.po.Tag;
import com.lrm.po.Type;
import com.lrm.po.User;
import com.lrm.service.BlogService;
import com.lrm.service.TagService;
import com.lrm.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String listBlog(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        List<Type> types = typeService.listType();

        model.addAttribute("types", types);

        PageHelper.startPage(pageNum, 10, "update_time desc");
        List<Blog> blogs = blogService.listBlog();
        PageInfo<Blog> page = new PageInfo<>(blogs);
        model.addAttribute("page", page);
        return "admin/blogs";

    }

    @GetMapping("/blogs/input")
    public String toAddBlogPage(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session, @RequestParam("type.id") long typeId, @RequestParam("tagIds") String tagIds) {
        blog.setUser((User) session.getAttribute("user"));
        System.out.println(typeId);
        blog.setType(typeService.getType(typeId));

        System.out.println(tagIds);
        List<Tag> tags = tagService.listTag(blog.getTagIds());
        blog.setTags(tags);
        if (blog.getId() == null) {
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }

//        if (b == null ) {
//            attributes.addFlashAttribute("message", "操作失败");
//        } else {
//            attributes.addFlashAttribute("message", "操作成功");
//        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/input/{id}")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlog(id);
        List<Tag> tags = blog.getTags();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            sb.append(tag.getId());
            if (i != tags.size()-1){
                sb.append(",");
            }
        }
        String tagIds = sb.toString();
        model.addAttribute("tagIds",tagIds);

        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


}
