package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class ProjectShowController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/projects")
    public Result projects() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }
}
