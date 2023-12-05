package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TagShowController {
    @Autowired
    private TagService tagService;

    @GetMapping("/getTagList")
    public Result getTagList() {
        return new Result(true, StatusCode.OK, "获取博客标签成功", tagService.listTagTop(10));
    }

    @GetMapping("/getFullTagList")
    public Result getFullTagList() {
        return new Result(true, StatusCode.OK, "获取所有博客标签成功", tagService.listTag());
    }

}
