package com.example.jpahibernatecrud.model;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.jpahibernatecrud.model.Answer")
@Table(name = "answer")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Integer id;
  @Column(name = "\"question_id\"", nullable = false)
  private Integer questionId;
  @Column(name = "\"text\"", nullable = true)
  private String text;
  @Column(name = "\"createdAt\"", nullable = true)
  private Timestamp createdat;
  @Column(name = "\"updatedAt\"", nullable = true)
  private Timestamp updatedat;
}