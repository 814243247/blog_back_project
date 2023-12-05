package com.example.blog2.dao;

import com.example.blog2.po.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

}
