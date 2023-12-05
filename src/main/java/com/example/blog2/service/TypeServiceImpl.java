package com.example.blog2.service;


import com.example.blog2.dao.TypeRepository;
import com.example.blog2.po.Blog;
import com.example.blog2.po.Type;
import com.example.blog2.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        //springboot2.0将findone改成了getone
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        List<Type> types = typeRepository.findAll();
        return getTypes(types);
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        List<Type> types = typeRepository.findTop(pageable);
        return getTypes(types);
    }

    private List<Type> getTypes(List<Type> types) {
        types.forEach(type -> {
            List<Blog> blogs = type.getBlogs();
            blogs.forEach(blog -> {
                blog.setContent("");
                blog.setComments(null);
                blog.setTags(null);
            });
            type.setBlogs(blogs);
        });
        return types;
    }

    @Override
    public List<Type> listByNameExceptSelf(Long id, String name) {
        return typeRepository.findByNameExceptSelf(id,name);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        BeanUtils.copyProperties(type,t, MyBeanUtils.getNullPropertyNames(type));
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
