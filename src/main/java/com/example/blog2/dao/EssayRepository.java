package com.example.blog2.dao;

import com.example.blog2.po.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EssayRepository extends JpaRepository<Essay,Long> {

}
