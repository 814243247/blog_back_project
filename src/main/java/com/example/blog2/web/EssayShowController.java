package com.example.blog2.web;

import com.example.blog2.po.Essay;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class EssayShowController {
    @Autowired
    private EssayService essayService;
    @GetMapping("/essays")
    public Result essays() {
        return new Result(true, StatusCode.OK, "获取随笔列表成功", essayService.listEssay());
    }
}
