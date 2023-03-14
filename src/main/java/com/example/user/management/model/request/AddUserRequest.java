package com.example.user.management.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class AddUserRequest implements Serializable {
    @NotBlank
    private String name;
    private Integer age;
    private Integer gender;
    private String phone;
    private String address;
}
