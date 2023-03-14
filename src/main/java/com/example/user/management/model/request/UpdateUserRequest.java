package com.example.user.management.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class UpdateUserRequest implements Serializable {
    private String name;

    private Integer age;

    private Integer gender;

    private String phone;

    private String address;
}
