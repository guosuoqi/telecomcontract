package com.dx.mapper.user;

import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
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

    void updateUserByUserId(UserMain user);

    UserMain queryUserByName(@Param("userName")String userName);

    void deleteRole(@Param("userId")String userId);

    void saveRole(ArrayList<UserRoleBean> params);

    void updateUserRoleByUserId(@Param("userId")String userId,@Param("userMain")UserMain userMain);

    int addUser(@Param("userMain")UserMain userMain);

    int delUser(@Param("ids")String ids);
}
