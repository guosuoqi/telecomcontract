package com.dx.mapper.user;

import com.dx.model.nav.*;
import com.dx.model.user.UserMain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public interface UserMapper {
    UserMain getUserInfoByLoginNumber(String loginNumber);

    int queryUserCount(HashMap<String, Object> params);

    List<UserMain> queryUser(HashMap<String, Object> params);

    List<RoleBean> queryRole();

    int queryRoleAllCount(HashMap<String, Object> params);

    List<RoleBean> queryRoleAll(HashMap<String, Object> params);

    List<UserRoleBean> queryRoleByUserId(@Param("userId")String userId);

    List<RoleBean> queryRoleByIds(@Param("ids")String ids);

    void deleteUserRole(@Param("userId")String userId);

    void insertUserRole(@Param("list") List<UserRoleBean> list);

    int updateUserByUserId(UserMain user);

    List<UserMain> queryUserByCounty(@Param("county")String county);

    void deleteRole(@Param("userId")String userId);

    void saveRole(ArrayList<UserRoleBean> params);

    void updateUserRoleByUserId(@Param("userId")String userId,@Param("userMain")UserMain userMain);

    int addUser(@Param("userMain")UserMain userMain);

    int delUser(@Param("ids")String[] ids);

    List<NavTree> getNavList();

    List<RoleNavBean> queryRoleNavById(HashMap<String, Object> param);

    void deleteRoleNav(@Param("roleId") String roleId);

    void saveRoleNav(ArrayList<RoleNavBean> params);

    List<NavMenuBean> queryPowerMenuList(NavMenuBean navMenuBean);

    void delPowerMenu(NavMenuBean navMenuBean);

    int addMenu(NavMenuBean navMenuBean);

    List<String> findUserPowerUrl(@Param("userId")String userId);

    void addUserRole(List<UserRoleBean> urlist);

    int addRole(@Param("roleBean")RoleBean roleBean);

    int delRole(@Param("ids")String[] ids);

    void delUserRole(@Param("ids")String[] ids);

    void delRoleNav(@Param("ids")String[] roleIds);

    void delUserRoleByRoleId(@Param("ids")String[] roleIds);
}
