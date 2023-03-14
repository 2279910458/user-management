package com.example.user.management.util;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageRequest<T> implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private T filter;
}
