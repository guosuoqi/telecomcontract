package com.dx.service.user;

import com.dx.mapper.user.UserMapper;
import com.dx.model.contract.ContractExtension;
import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
import com.dx.model.user.UserMain;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserMain getUserInfoByLoginNumber(String loginNumber) {
        return userMapper.getUserInfoByLoginNumber(loginNumber);
    }

    @Override
    public PageResult queryUser(Integer page, Integer rows, UserMain userMain) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userMain",userMain);
        int count=userMapper.queryUserCount(params);
        pageResult.setTotal(count);
        PageUtil<UserMain> pageUtil = new PageUtil<UserMain>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<UserMain> list = userMapper.queryUser(params);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public List<RoleBean> queryRole() {
        return userMapper.queryRole();
    }

    @Override
    public PageResult queryRoleAll(Integer page, Integer rows, RoleBean roleBean) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("roleBean",roleBean);
        int count=userMapper.queryRoleAllCount(params);
        pageResult.setTotal(count);
        PageUtil<RoleBean> pageUtil = new PageUtil<RoleBean>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<RoleBean> list = userMapper.queryRoleAll(params);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public List<UserRoleBean>queryRoleByUserId(String userId){
        return userMapper.queryRoleByUserId(userId);
    }

    @Override
    @Transactional(noRollbackFor=Exception.class)
    public int saveUserRole(Integer userId,String ids){
        try {
            List<RoleBean> list = userMapper.queryRoleByIds(ids);
            String roleNames=null;
            for (RoleBean role:list) {
                roleNames+=roleNames==null?role.getName():","+role.getName();
            }
            userMapper.deleteUserRole(userId);
            List<UserRoleBean> UserRoleList = new ArrayList<>();
            String[] split = ids.split(",");
            for (int i = 0; i <split.length ; i++) {
                UserRoleBean bean = new UserRoleBean();
                bean.setUserId(userId);
                bean.setRoleId(split[i]);
                UserRoleList.add(bean);
            }
            userMapper.insertUserRole(UserRoleList);
            UserMain user = new UserMain();
            user.setId(userId);
            user.setRoleNames(roleNames);
            userMapper.updateUserByUserId(user);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public UserMain queryUserByName(String userName){
       return userMapper.queryUserByName(userName);
    }


}
