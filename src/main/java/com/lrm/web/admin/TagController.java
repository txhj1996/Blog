package com.lrm.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrm.po.Tag;
import com.lrm.po.Type;
import com.lrm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,10);
        List<Tag> list = tagService.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        model.addAttribute("page",pageInfo);
        return "admin/tags";
    }


    @GetMapping("/tags/input")
    public String toAddTagPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addTag(Tag tag,RedirectAttributes attributes){
        System.out.println(tag);
        if ("".equals(tag.getName())){
            attributes.addFlashAttribute("message","标签名不能为空");
            return "redirect:/admin/tags/input";
        }else {
            Tag tagByName = tagService.getTagByName(tag.getName());
            if (tagByName != null) {
                attributes.addFlashAttribute("message","标签名重复");
                return "redirect:/admin/tags/input";
            }else{
                tagService.saveTag(tag);
                attributes.addFlashAttribute("message","添加成功");
                return "redirect:/admin/tags";
            }
        }
    }

    @GetMapping("/tags/input/{id}")
    public String toUpdatePage(@PathVariable() long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String updateTag(@PathVariable() long id,Tag tag,RedirectAttributes attributes){
        if ("".equals(tag.getName())){
            attributes.addFlashAttribute("message","标签名不能为空");
            return "redirect:/admin/tags/input/"+id;
        }else {
            Tag tag1 = tagService.getTagByName(tag.getName());
            if (tag1 != null){
                attributes.addFlashAttribute("message","标签名重复");
                return "redirect:/admin/tags/input/"+id;
            }else {
                tag.setId(id);
                tagService.updateTag(id,tag);
                attributes.addFlashAttribute("message","修改成功");
                return "redirect:/admin/tags";
            }
        }
    }

    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable() long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }





}
