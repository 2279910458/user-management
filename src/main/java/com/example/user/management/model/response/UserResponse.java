package com.example.user.management.model.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private Integer gender;
    private String phone;
    private String address;
}
