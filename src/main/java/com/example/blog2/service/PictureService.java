package com.example.blog2.service;

import com.example.blog2.po.Picture;
import com.example.blog2.po.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {
    Result upload(MultipartFile file);

    void deleteByName(String filename);

    void deleteByID(Long id);

    List<Picture> getAll();

    Picture deleteByUrl(String url);
}
