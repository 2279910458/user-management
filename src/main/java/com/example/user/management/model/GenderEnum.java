package com.example.user.management.model;

import lombok.Getter;

@Getter
public enum GenderEnum {
    MEN(0, "男"),
    WOMEN(1, "女");

    Integer code;

    String description;

    GenderEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
