package com.example.jpahibernatecrud.model;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.jpahibernatecrud.model.Question")
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Integer id;
  @Column(name = "\"title\"", nullable = false)
  private String title;
  @Column(name = "\"description\"", nullable = true)
  private String description;
  @Column(name = "\"createdAt\"", nullable = true)
  private Timestamp createdat;
  @Column(name = "\"updatedAt\"", nullable = true)
  private Timestamp updatedat;
}