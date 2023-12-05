package com.example.blog2.service;

import com.example.blog2.dao.EssayRepository;
import com.example.blog2.po.Essay;
import com.example.blog2.util.MarkdownUtils;
import com.example.blog2.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EssayServiceImpl implements EssayService{

    @Autowired
    private EssayRepository essayRepository;


    @Override
    public List<Essay> listEssay() {
        return essayRepository.findAll();
    }

    @Override
    public void deleteEssay(Long id) {
        essayRepository.deleteById(id);
    }

    @Override
    public Essay saveEssay(Essay essay) {
        essay.setCreateTime(new Date());
        return essayRepository.save(essay);
    }

    @Override
    public Essay updateEssay(Long id, Essay essay) {
        Essay e = essayRepository.getOne(id);
        BeanUtils.copyProperties(essay,e, MyBeanUtils.getNullPropertyNames(essay));
        return essayRepository.save(e);
    }


}
