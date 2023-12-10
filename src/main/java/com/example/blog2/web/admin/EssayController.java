package com.example.blog2.web.admin;

import com.example.blog2.po.Blog;
import com.example.blog2.po.Essay;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/admin")
public class EssayController {
    @Autowired
    private EssayService essayService;

    @GetMapping("/essay/{id}/delete")
    public Result delete(@PathVariable Long id) {
        essayService.deleteEssay(id);
        return new Result(true, StatusCode.OK, "删除随笔成功",null );
    }

    @PostMapping("/essay")
    public Result post(@RequestBody Map<String, Essay> para){
        Essay essay = para.get("essay");
        if (essay.getId() == null) {
            essayService.saveEssay(essay);
            return new Result(true,StatusCode.OK,"添加成功",essay);
        } else {
            essayService.updateEssay(essay.getId(),essay);
            return new Result(true,StatusCode.OK,"更新成功",essay);
        }
    }

    @GetMapping("/essays")
    public Result essays() {
        return new Result(true, StatusCode.OK, "获取随笔列表成功", essayService.listEssay());
    }
}
