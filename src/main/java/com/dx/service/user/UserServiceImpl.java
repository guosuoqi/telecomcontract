package com.dx.service.user;

import com.dx.mapper.user.UserMapper;
import com.dx.model.user.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserMain getUserInfoByLoginNumber(String loginNumber) {
        return userMapper.getUserInfoByLoginNumber(loginNumber);
    }
}
