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
//新增用户
    @Override
    public int addUser(UserMain userMain) {
        userMain.setPassword(StringUtils.getMD5String(userMain.getPassword()));
        return userMapper.addUser(userMain);
    }
//删除用户
    @Override
    public int delUser(String ids) {
        return userMapper.delUser(ids);
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
    public List<String> findUserPowerUrl(Integer userId) {
        List<String> userPowerUrl = userMapper.findUserPowerUrl(userId);
        return userPowerUrl;
    }


    public List<UserMain> queryUserByCounty(String userName){
        return userMapper.queryUserByCounty(userName);
    }
    public void sendContractTaskToEmail() {
        TaskModel model;
        Contract contract = new Contract();
        contract.setExtenxionStatus(1);
        List<Contract>list=contractMapper.queryContractByStatus(contract);
        if(list!=null && !list.isEmpty()){

        }
    }
    public void sendTaskToEmail(List<Contract>list){
        List<UserMain> users=null;
        List<TaskModel> taskModels=new ArrayList<>();
        TaskModel taskModel=null;
        JSONObject body=null;
        for (Contract con:list) {
            if(con.getNumber()==0){
                continue;
            }
            if(!con.getRenewOperator().isEmpty()){
                users=userMapper.queryUserByCounty(con.getCounty());
                for (UserMain user:users) {
                    body= getCommJson(user,1,con.getNumber());
                    SendTask(taskModel,user,body,taskModels);
                }

            }else if(!con.getExtenxionOperator().isEmpty()){
                users=userMapper.queryUserByCounty(con.getCounty());
                for (UserMain user:users) {
                    body= getCommJson(user,2,con.getNumber());
                    SendTask(taskModel,user,body,taskModels);
                }
            }
            //todo 新增任务落库
            //insertTaskModels(taskModels);
        }
    }

    private void SendTask(TaskModel taskModel, UserMain user, JSONObject body,List<TaskModel>taskModels) {
        taskModel=new TaskModel();
        taskModel.setStatus(0);
        taskModel.setType(TaskEnum.SEND_EMAIL.getKey());
        taskModel.setUser(user.getUserName());
        taskModel.setContent(body.toJSONString());
        taskModels.add(taskModel);
    }

    private JSONObject getCommJson(UserMain user, int type,Integer number) {
        JSONObject body =new JSONObject();
        body.put("userEmail",user.getEmail());
        if(type==1){
            body.put("subject","续费待处理通知");
            body.put("content",user.getUserName()+"您好：有"+number+"笔合同已进入续费阶段，请尽快进行处理！！！\n \n \n  本条信息为系统信息，请勿回复！");
        }else {
            body.put("subject","续约待处理通知");
            body.put("subject",user.getUserName()+"您好：有"+number+"笔合同已进入续费阶段，请尽快进行处理！！！\n \n \n  本条信息为系统信息，请勿回复！");
        }
        return body;
    }

}
