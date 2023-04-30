package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
    public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private String delete_flag;

    @Column(nullable = false)
    private String created_at;

    @Column(nullable = false)
    private String updated_at;

    @OneToOne(mappedBy = "employee")
    private Authentication authentication;
}



