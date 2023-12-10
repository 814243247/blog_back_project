package com.example.blog2.service;

import com.example.blog2.dao.PictureRepository;
import com.example.blog2.po.Picture;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PictureServiceImpl implements PictureService{
    @Autowired
    PictureRepository pictureRepository;
    @Value("${file.path}")
    String path;
    @Value("${file.url}")
    String picUrl;
    @Transactional
    @Override
    public Result upload(MultipartFile file) {
        if (file.getSize() > 1024*1024*20) {
            return new Result(false,StatusCode.ERROR,"上传文件不能大于20mb");
        }
        Picture picture = new Picture();
        //获取文件名以及后缀名
        String fileName=file.getOriginalFilename();
        fileName = UUID.randomUUID() + fileName;
        //上传到nginx
        String dirPath = path + fileName;
        log.info("上传路径:" + dirPath);
        File filePath=new File(dirPath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        //url:localhost:8080/image/filename
        String url = picUrl + fileName;
        log.info("上传url:" + url);
        picture.setDialogImageUrl(url);
        try{
            pictureRepository.save(picture);
            //将文件写入磁盘
            file.transferTo(new File(dirPath));
            //文件上传成功返回状态信息
            return new Result(true, StatusCode.OK,"图片上传成功",picture);
        }catch (Exception e){
            e.printStackTrace();
            //上传失败，返回失败信息
            return new Result(false,StatusCode.ERROR,"图片上传失败");
        }
    }

    @Override
    public void deleteByName(String filename) {

    }

    @Override
    public Picture deleteByUrl(String url) {
        return pictureRepository.findByDialogImageUrlLike(url);

    }

//    @Override
//    public Result deleteByName(String filename) {
//        pictureRepository.
//    }

    @Override
    public void deleteByID(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public List<Picture> getAll() {
        List<Picture> all = pictureRepository.findAll();
        return all;
    }

}
