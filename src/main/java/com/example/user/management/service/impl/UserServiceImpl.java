package com.example.user.management.service.impl;

import com.example.user.management.mapper.UserMapper;
import com.example.user.management.model.request.AddUserRequest;
import com.example.user.management.model.request.PageUserFilter;
import com.example.user.management.model.request.UpdateUserRequest;
import com.example.user.management.model.response.UserResponse;
import com.example.user.management.service.UserService;
import com.example.user.management.util.PageRequest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(AddUserRequest request) {
        userMapper.save(request);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public void delete(List<Long> ids) {
        userMapper.deleteList(ids);
    }

    @Override
    public void update(Long id, UpdateUserRequest request) {
        userMapper.update(id, request);
    }

    @Override
    public UserResponse getInfoById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public Page<UserResponse> page(PageRequest<PageUserFilter> request) {
        return PageHelper.startPage(request.getPageNum(), request.getPageSize())
                .doSelectPage(() -> userMapper.listUser(request.getFilter()));
    }
}
