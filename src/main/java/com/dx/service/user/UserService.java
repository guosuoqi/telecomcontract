package com.dx.service.user;

import com.dx.model.nav.NavTree;
import com.dx.model.user.UserMain;

import java.util.List;

public interface UserService {
    UserMain getUserInfoByLoginNumber(String loginNumber);

}
