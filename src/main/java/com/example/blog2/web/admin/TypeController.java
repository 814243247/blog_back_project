package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Type;
import com.example.blog2.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin
@Slf4j
public class TypeController {

    @Autowired
    private TypeService typeService;

//    新增或删除type
    @PostMapping("/types")
    public Result post(@RequestBody Map<String, Type> para) {
        Type type = para.get("type");
        log.info("收到:"+type);
        if (type.getId() == null) {
            Type search = typeService.getTypeByName(para.get("type").getName());
            if (search != null) {
                return new Result(false, StatusCode.ERROR, "不能添加重复的分类", null);
            }else {
                typeService.saveType(type);
                return new Result(true, StatusCode.OK, "保存成功", null);
            }
        } else {
            List<Type> typeList = typeService.listByNameExceptSelf(type.getId(),type.getName());
            if (typeList.size() > 0 ) {
                return new Result(false, StatusCode.ERROR, "分类名称已存在", null);
            }else {
                Type t = typeService.updateType(type.getId(), type);
                if (t == null) {
                    return new Result(false, StatusCode.ERROR, "修改失败", null);
                }
                return new Result(true, StatusCode.OK, "修改成功", null);
            }
        }

    }


    @GetMapping("/types/{id}/delete")
    public Result delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}
