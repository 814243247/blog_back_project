package com.example.blog2.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_picture")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Picture {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ElementCollection
   private List<String> pictureList;


   private String dialogImageUrl;


}

