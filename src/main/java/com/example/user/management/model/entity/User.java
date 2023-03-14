package com.example.user.management.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String phone;

    private String address;

    private Integer deleted;
}
