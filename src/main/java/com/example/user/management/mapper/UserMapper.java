package com.example.user.management.mapper;

import com.example.user.management.model.request.AddUserRequest;
import com.example.user.management.model.request.PageUserFilter;
import com.example.user.management.model.request.UpdateUserRequest;
import com.example.user.management.model.response.UserResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    void save(@Param("request") AddUserRequest request);

    void delete(@Param("id") Long id);

    void deleteList(@Param("ids") List<Long> ids);

    void update(@Param("id") Long id, @Param("request") UpdateUserRequest request);

    UserResponse getById(@Param("id") Long id);

    List<UserResponse> listUser(@Param("filter") PageUserFilter filter);
}
