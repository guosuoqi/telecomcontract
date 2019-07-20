package com.dx.service.user;

import com.dx.model.nav.NavTree;
import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
import com.dx.model.user.UserMain;
import com.dx.util.PageResult;

import java.security.SecureRandom;
import java.util.List;

public interface UserService {
    UserMain getUserInfoByLoginNumber(String loginNumber);

    PageResult queryUser(Integer page, Integer rows, UserMain userMain);

    List<RoleBean> queryRole();

    PageResult queryRoleAll(Integer page, Integer rows, RoleBean roleBean);

    List<UserRoleBean> queryRoleByUserId(String userId);

    int saveUserRole(Integer userId, String ids);
}
