package com.dx.service.user;

import com.dx.mapper.contract.ContractMapper;
import com.dx.mapper.user.UserMapper;
import com.alibaba.fastjson.JSONObject;
import com.dx.model.contract.Contract;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import com.dx.model.user.UserMain;
import com.dx.model.contract.ContractExtension;
import com.dx.mapper.task.TaskMapper;
import com.dx.mapper.user.UserMapper;
import com.dx.model.Task.TaskModel;
import com.dx.model.common.TaskEnum;
import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
import com.dx.model.user.UserMain;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContractMapper contractMapper;

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
    public void sendContractTaskToEmail() {
        TaskModel model;
        Contract contract = new Contract();
        contract.setExtenxionStatus(1);
        List<Contract>list=contractMapper.queryContractByStatus(contract);
        if(list!=null && !list.isEmpty()){

        }
    }
    public void sendTaskToEmail(List<Contract>list){
        UserMain user=null;
        TaskModel taskModel=null;
        JSONObject body=null;
        for (Contract con:list) {
            if(con.getNumber()==0){
                continue;
            }
            if(!con.getRenewOperator().isEmpty()){
                user=userMapper.queryUserByName(con.getRenewOperator());
                body= getCommJson(user,1,con.getNumber());
            }else if(!con.getExtenxionOperator().isEmpty()){
                user=userMapper.queryUserByName(con.getExtenxionOperator());
                body= getCommJson(user,2,con.getNumber());
            }
            if(user==null){
                continue;
            }
            taskModel.setStatus(0);
            taskModel.setType(TaskEnum.SEND_EMAIL.getKey());
            taskModel.setUser(user.getUserName());
            taskModel.setContent(body.toJSONString());
        }
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
