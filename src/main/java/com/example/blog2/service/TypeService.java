package com.example.blog2.service;

import com.example.blog2.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    List<Type> listByNameExceptSelf(Long id,String name);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

}
