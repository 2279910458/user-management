package com.example.user.management.service;

import com.example.user.management.model.request.AddUserRequest;
import com.example.user.management.model.request.PageUserFilter;
import com.example.user.management.model.request.UpdateUserRequest;
import com.example.user.management.model.response.UserResponse;
import com.example.user.management.util.PageRequest;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void save(AddUserRequest request);

    void delete(Long id);

    void delete(List<Long> ids);

    void update(Long id, UpdateUserRequest request);

    UserResponse getInfoById(Long id);

    Page<UserResponse> page(PageRequest<PageUserFilter> request);
}
