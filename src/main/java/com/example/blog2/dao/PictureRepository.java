package com.example.blog2.dao;

import com.example.blog2.po.Message;
import com.example.blog2.po.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
    Picture findByDialogImageUrlLike(String dialogImageUrl);
}
