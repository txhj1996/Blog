package com.lrm.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrm.po.Type;
import com.lrm.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,10);
        List<Type> list = typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("page",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddPage(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

//    @PostMapping("/types")
//    public String addType(@RequestParam("name")String name) {
//        Type type = new Type(name);
//        typeService.saveType(type);
//        return "redirect:/admin/types";
//    }

    @PostMapping("/types")
    public String addType(Type type,RedirectAttributes attributes) {
//        System.out.println("==========================");
//        System.out.println(type);
//        System.out.println("==========================");
        if ("".equals(type.getName())){
            attributes.addFlashAttribute("message","分类名不能为空");
            return "redirect:/admin/types/input";
        }else{
            Type type1 = typeService.getTypeByName(type.getName());
            if (type1 != null){
                attributes.addFlashAttribute("message","分类名重复");
                return "redirect:/admin/types/input";
            }else {
                typeService.saveType(type);
                attributes.addFlashAttribute("message","添加成功");
                return "redirect:/admin/types";
            }
        }

    }

    @GetMapping("/types/input/{id}")
    public String toUpdatePage(@PathVariable() long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(@PathVariable Long id, Type type,RedirectAttributes attributes) {
        if ("".equals(type.getName())){
            attributes.addFlashAttribute("message","分类名不能为空");
            return "redirect:/admin/types/input/"+id;
        }else {
            Type type1 = typeService.getTypeByName(type.getName());
            if (type1 != null){
                attributes.addFlashAttribute("message","分类名重复");
                return "redirect:/admin/types/input/"+id;
            }else {
                type.setId(id);
                typeService.updateType(id,type);
                attributes.addFlashAttribute("message","修改成功");
                return "redirect:/admin/types";
            }
        }
    }

    @GetMapping("/types/delete/{id}")
    public String delete(@PathVariable() long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }





}
