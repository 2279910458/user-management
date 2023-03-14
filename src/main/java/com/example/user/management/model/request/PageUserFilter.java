package com.example.user.management.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageUserFilter implements Serializable {

    private String name;
    private String phone;
    private String address;
}
