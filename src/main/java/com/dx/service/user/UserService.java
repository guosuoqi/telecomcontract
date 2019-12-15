package com.dx.service.user;

import com.dx.model.nav.NavMenuBean;
import com.dx.model.nav.NavTree;
import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
import com.dx.model.user.UserMain;
import com.dx.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    UserMain getUserInfoByLoginNumber(String loginNumber);

    PageResult queryUser(Integer page, Integer rows, UserMain userMain);

    List<RoleBean> queryRole();

    PageResult queryRoleAll(Integer page, Integer rows, RoleBean roleBean);

    List<UserRoleBean> queryRoleByUserId(String userId);

   //int saveUserRole(String userId, String ids);

    void saveRole(String userId, String[] roleId,UserMain userMain);

    int addUser(UserMain userMain);

    int delUser(@Param("ids")String[] ids);

    List<NavTree> queryRoleNav(HashMap<String, Object> param);

    void saveRoleNav(String roleId, Integer[] navIds);

    List<NavMenuBean> queryPowerMenuList(NavMenuBean navMenuBean);

    void delPowerMenu(NavMenuBean navMenuBean);

    int addMenu(NavMenuBean navMenuBean);

    List<String> findUserPowerUrl(String userId);

    int addRole(RoleBean roleBean);

    int delRole(String[] split);
}
