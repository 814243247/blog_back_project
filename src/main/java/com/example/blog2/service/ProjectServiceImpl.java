package com.example.blog2.service;

import com.example.blog2.dao.ProjectRepository;
import com.example.blog2.po.Project;
import com.example.blog2.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> listProject() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project p = projectRepository.getOne(id);
        BeanUtils.copyProperties(project,p, MyBeanUtils.getNullPropertyNames(project));
        return projectRepository.save(p);
    }
}
