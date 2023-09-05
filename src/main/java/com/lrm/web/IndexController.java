package com.lrm.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrm.po.Blog;
import com.lrm.po.Tag;
import com.lrm.po.Type;
import com.lrm.service.BlogService;
import com.lrm.service.TagService;
import com.lrm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum")int pageNum, Model model){
        List<Type> types = typeService.listTypeTop(3);
        model.addAttribute("types",typeService.listTypeTop(10));
        List<Tag> tags = tagService.listTagTop(10);
        model.addAttribute("tags",tags);
        List<Blog> recommendBlogs = blogService.listRecommendBlogTop(8);
        model.addAttribute("recommendBlogs",recommendBlogs);

        PageHelper.startPage(pageNum,6,"create_time desc");
        List<Blog> blogs = blogService.listBlog();
        PageInfo<Blog> page = new PageInfo<>(blogs);
        model.addAttribute("page",page);

        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam String query, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Blog> blogs = blogService.listBlogWithQuary("%" + query + "%");
        PageInfo<Blog> page = new PageInfo<>(blogs);
        model.addAttribute("page",page);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/search")
    public String search1(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam String query, Model model) {

        return search(pageNum,query,model);
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        Blog blog = blogService.getAndConvert(id);
        List<Tag> tags = tagService.getTagsByBlogId(id);
        blog.setTags(tags);

        model.addAttribute("blog",blog );
        return "blog";
    }
}
