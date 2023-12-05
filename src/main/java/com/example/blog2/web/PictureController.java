package com.example.blog2.web;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.blog2.po.Picture;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


@RestController()
@CrossOrigin
@Slf4j
public class PictureController {
    @Autowired
    PictureService  pictureService;
    @Value("${server.port}")
    String port;
    @PostMapping(value = "/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        log.info("上传图片:" + file);
        return pictureService.upload(file);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除图片:" + id);
        pictureService.deleteByID(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    @GetMapping(value = "/get_all")
    @ResponseBody
    public Result getAll() {
        List<Picture> all = pictureService.getAll();
        return new Result(true,StatusCode.OK,"已获取全部图片",all);
    }
}
