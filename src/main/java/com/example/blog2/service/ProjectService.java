package com.example.blog2.service;

import com.example.blog2.po.Project;

import java.util.List;


public interface ProjectService {
    List<Project> listProject();

    void deleteProject(Long id);

    Project saveProject(Project project);

    Project updateProject(Long id,Project project);
}
