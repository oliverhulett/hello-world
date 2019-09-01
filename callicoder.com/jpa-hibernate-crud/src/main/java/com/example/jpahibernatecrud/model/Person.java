package com.example.jpahibernatecrud.model;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.jpahibernatecrud.model.Person")
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Integer id;
  @Column(name = "\"name\"", nullable = false)
  private String name;
  @Column(name = "\"address\"", nullable = true)
  private String address;
}