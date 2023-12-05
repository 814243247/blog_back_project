package com.example.blog2.web.admin;

import com.alibaba.fastjson.JSON;
import com.example.blog2.po.*;
import com.example.blog2.service.BlogService;
import com.example.blog2.service.TagService;
import com.example.blog2.service.TypeService;
import com.example.blog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @PostMapping("/getBlogList")
    public Result getBlogList(@RequestBody Map<String, Object> para) {
        int pagenum = (int) para.get("pagenum");
        int pagesize = (int) para.get("pagesize");
        BlogQuery blogQuery = new BlogQuery();
        if (para.get("typeId") != null){
            blogQuery.setTypeId(Long.valueOf(para.get("typeId").toString()));
        }
        blogQuery.setTitle((String) para.get("title"));
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pagenum - 1,pagesize, sort);
        return new Result(true, StatusCode.OK, "获取博客列表成功", blogService.listBlog(pageable,blogQuery));
    }


    @PostMapping("/blogs")
    public Result post(@RequestBody Map<String, Blog> para) {
        Blog blog = para.get("blog");
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            System.out.println("修改");
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            return new Result(false,StatusCode.ERROR,"操作失败");
        }
        return new Result(true,StatusCode.OK,"操作成功");
    }

    @GetMapping("/search")
    public Result search(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query) {
        System.out.println(query);
        return new Result(true, StatusCode.OK, "获取搜索博客成功", blogService.listBlog("%" + query + "%", pageable));
    }

    @GetMapping("/blogs/{id}/delete")
    public Result delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return new Result();
    }

    @GetMapping("/dealDeletedTag/{id}")
    public Result dealDeletedTag(@PathVariable Long id){
        Tag tag = tagService.getTag(id);
        if (tag.getBlogs().size()==0){
            System.out.println("去除无用标签");
            tagService.deleteTag(id);
        }
        return new Result(true, StatusCode.OK, "处理标签成功", null);
    }
}
