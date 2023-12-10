package com.example.blog2.service;

import com.example.blog2.po.Blog;
import com.example.blog2.po.Result;
import com.example.blog2.po.Tag;
import com.example.blog2.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface BlogService {
//    根据id查询
    Blog getBlog(Long id);
//    分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    Result listRecommendBlogTop(Integer size);

    Result saveBlog(Blog blog);

    Result updateBlog(Blog blog);

    void deleteBlog(Long id);

    Blog getAndConvert(Long id);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

    Long countViews();

    List<String> ViewCountByMonth();

    List<String> BlogCountByMonth();

    List<String> appreciateCountByMonth();

    Long countAppreciate();

    Long countComment();

    void deleteBlogTag(Tag id);
}
