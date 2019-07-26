package com.dx.service.user;

import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
import com.dx.model.user.UserMain;
import com.dx.util.PageResult;

import java.util.List;

public interface UserService {
    UserMain getUserInfoByLoginNumber(String loginNumber);

    PageResult queryUser(Integer page, Integer rows, UserMain userMain);

    List<RoleBean> queryRole();

    PageResult queryRoleAll(Integer page, Integer rows, RoleBean roleBean);

    List<UserRoleBean> queryRoleByUserId(String userId);

   //int saveUserRole(String userId, String ids);

    void saveRole(String userId, Integer[] roleId,UserMain userMain);

    int addUser(UserMain userMain);

    int delUser(String ids);
}
