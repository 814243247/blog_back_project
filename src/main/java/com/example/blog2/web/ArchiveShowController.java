package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@CrossOrigin
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archiveBlog")
    public Result<Model> archives(){
        return new Result(true, StatusCode.OK, "查询博客列表成功", blogService.archiveBlog());
    }

    @GetMapping("/countBlog")
    public Result count(){
        return new Result(true, StatusCode.OK, "查询博客列表成功", blogService.countBlog());
    }
}
