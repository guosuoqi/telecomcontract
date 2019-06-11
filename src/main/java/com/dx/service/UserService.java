package com.dx.service;

import com.dx.model.user.UserMain;

public interface UserService {
    UserMain getUserInfoByLoginNumber(String loginNumber);
}
