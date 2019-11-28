package com.dx.service.user;

import com.alibaba.fastjson.JSON;
import com.dx.mapper.contract.ContractMapper;
import com.dx.mapper.nav.NavMapper;
import com.dx.mapper.user.UserMapper;
import com.alibaba.fastjson.JSONObject;
import com.dx.model.contract.Contract;
import com.dx.model.nav.*;
import com.dx.service.nav.NavService;
import com.dx.util.StringUtil;
import com.dx.util.StringUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.poi.util.ArrayUtil;
import org.springframework.transaction.annotation.Transactional;
import com.dx.model.user.UserMain;
import com.dx.model.Task.TaskModel;
import com.dx.model.common.TaskEnum;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private NavService navService;

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

/*    @Override
    @Transactional(noRollbackFor=Exception.class)
    public int saveUserRole(String userId,String ids){
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
    }*/

    @Override
    public void saveRole(String userId, Integer[] roleId,UserMain userMain) {
        //删除角色原有权限
        userMapper.deleteRole(userId);
        //新增角色新修改的权限
        ArrayList<UserRoleBean> params = new ArrayList<UserRoleBean>();
        for (Integer integer : roleId) {
            UserRoleBean userRoleBean = new UserRoleBean();
            userRoleBean.setId(StringUtil.getUUId());
            userRoleBean.setRoleId(integer);;
            userRoleBean.setUserId(userId);
            params.add(userRoleBean);
        }
        userMapper.saveRole(params);

        userMapper.updateUserRoleByUserId(userId,userMain);
    }
    @Override
    public int addRole(RoleBean roleBean) {
        roleBean.setId(StringUtil.getUUId());
        return userMapper.addRole(roleBean);

    }
//新增用户
    @Override
    @Transactional
    public int addUser(UserMain userMain) {
        int i=0;
        userMain.setPassword(StringUtils.getMD5String(userMain.getPassword()));
        if(org.apache.commons.lang.StringUtils.isNotBlank(userMain.getId())){
            userMapper.updateUserByUserId(userMain);
        }else {
            String uuid=StringUtil.getUUId();
            userMain.setId(uuid);
            i = userMapper.addUser(userMain);
        }
        if(i!=0 && org.apache.commons.lang.StringUtils.isNotBlank(userMain.getRole())){
            UserRoleBean userRoleBean;
            List<UserRoleBean> urlist = new ArrayList<>();
            Integer[] roleIds = userMain.getRoleId();
            for (int j = 0; j < roleIds.length; j++) {
                userRoleBean= new UserRoleBean();
                userRoleBean.setRoleId(roleIds[j]);
                userRoleBean.setUserId(userMain.getId());
                userRoleBean.setId(StringUtil.getUUId());
                urlist.add(userRoleBean);
            }
            userMapper.addUserRole(urlist);
        }
        return i;
    }
//删除用户
    @Override
    public int delUser(String[] userIds) {
        int i = userMapper.delUser(userIds);
        if(i!=0){
            userMapper.delUserRole(userIds);
            return i;
        }
        return 0;
    }
   @Override
    public int delRole(String[] roleIds) {
       int i = userMapper.delRole(roleIds);
       if(i!=0){
           userMapper.delUserRoleByRoleId(roleIds);
           userMapper.delRoleNav(roleIds);
           return i;
       }
       return 0;
    }

    @Override
    public List<NavTree> queryRoleNav(HashMap<String, Object> param) {
        //查询所有的权限树节点
        List<NavTree> list = navService.toShowTree();
        //查询传过来角色所拥有的权限
        List<RoleNavBean>roleNavBeans=userMapper.queryRoleNavById(param);
        for (NavTree navTree : list) {
            for (RoleNavBean roleNavBean : roleNavBeans) {
                if(navTree.getId().equals( roleNavBean.getNavId())){
                    navTree.setState();
                }
                List<NavTree> nodes = navTree.getNodes();
                for (NavTree nav : nodes){
                    if(nav.getId().equals( roleNavBean.getNavId()) ){
                        nav.setState();
                    }
                }
            }

        }
        return list;
    }

    @Override
    public void saveRoleNav(String roleId, Integer[] navIds) {
        //删除角色原有权限
        userMapper.deleteRoleNav(roleId);
        //新增角色新修改的权限
        ArrayList<RoleNavBean> params = new ArrayList<RoleNavBean>();
        for (Integer integer : navIds) {
            RoleNavBean roleNavBean = new RoleNavBean();
            roleNavBean.setId(StringUtil.getUUId());
            roleNavBean.setRoleId(roleId);
            roleNavBean.setNavId(integer);
            params.add(roleNavBean);
        }
        System.out.println(params);
        userMapper.saveRoleNav(params);
    }

    @Override
    public List<NavMenuBean> queryPowerMenuList(NavMenuBean navMenuBean) {
        // TODO Auto-generated method stub
        return userMapper.queryPowerMenuList(navMenuBean);
    }
    @Override
    public void delPowerMenu(NavMenuBean navMenuBean) {
         userMapper.delPowerMenu(navMenuBean);
    }

    @Override
    public int addMenu(NavMenuBean navMenuBean) {
        return userMapper.addMenu(navMenuBean);
    }

    @Override
    public List<String> findUserPowerUrl(String userId) {
        List<String> userPowerUrl = userMapper.findUserPowerUrl(userId);
        return userPowerUrl;
    }


    public List<UserMain> queryUserByCounty(String userName){
        return userMapper.queryUserByCounty(userName);
    }



}
