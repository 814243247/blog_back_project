package com.example.blog2.web.admin;

import com.example.blog2.dao.ProjectRepository;
import com.example.blog2.po.Essay;
import com.example.blog2.po.Project;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/admin")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @GetMapping("/project/{id}/delete")
    public Result delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new Result(true, StatusCode.OK, "删除随笔成功",null );
    }

    @GetMapping("/projects")
    public Result projects() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }

    @PostMapping("/project")
    public Result post(@RequestBody Map<String, Project> para){
        System.out.println(para);
        Project project = para.get("project");
        Project p;
        if (project.getId() == null){
            p = projectService.saveProject(project);
        } else {
            p = projectService.updateProject(project.getId(),project);
        }
        if (p == null) {
            return new Result(false,StatusCode.ERROR,"操作失败");
        }
        return new Result(true,StatusCode.OK,"操作成功");
    }
}
