package com.example.blog2.service;

import com.example.blog2.po.Essay;

import java.util.List;



public interface EssayService {
    List<Essay> listEssay();

    void deleteEssay(Long id);

    Essay saveEssay(Essay essay);

    Essay updateEssay(Long id,Essay essay);
}
