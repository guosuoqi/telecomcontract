package com.dx.controller.user;


import com.dx.model.contract.Contract;
import com.dx.model.nav.RoleBean;
import com.dx.model.nav.UserRoleBean;
import com.dx.model.user.UserMain;
import com.dx.service.user.UserService;
import com.dx.util.ExceptionPrintUtil;
import com.dx.util.PageResult;
import com.dx.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("toIndex")
    public String toIndex(Model model, HttpServletRequest request) {
        HttpSession session =request.getSession();
        model.addAttribute("user",session.getAttribute(session.getId()));
        return "view/menu";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> login(UserMain user, HttpServletRequest request, Model model) {
        HashMap<String, String> result = new HashMap<String, String>();
        HttpSession session =request.getSession();
        //根据账号查询用户信息
        UserMain userInfo =userService.getUserInfoByLoginNumber(user.getLoginNumber());
        if(userInfo==null){
            result.put("code","1");
            result.put("msg", "用户名错误");
            logger.info(this.getClass() + "，用户名错误！");
            return result;
        }

        if (!userInfo.getPassword().equals(StringUtils.getMD5String(user.getPassword()))){
            result.put("code", "2");
            result.put("msg", "用户名或密码错误");
            logger.info(this.getClass() + "，用户名或密码错误！");
            return result;
        }
        session.setAttribute(session.getId(), userInfo);
        result.put("code", "200");
        result.put("msg", "登录成功");
        return result;
    }

    //注销
    @RequestMapping(value = "exitLogin")
    public String exitLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())!=null){
            session.removeAttribute(session.getId());
        }
        return "login";
    }

    @RequestMapping(value = "queryUser")
    @ResponseBody
    public PageResult queryUser(Integer page, Integer rows, UserMain userMain) {
        PageResult pageResult = userService.queryUser(page, rows, userMain);
        return pageResult;
    }

    @RequestMapping(value = "queryRole")
    @ResponseBody
    public List<RoleBean> queryRole() {
        List<RoleBean> roleBeans = userService.queryRole();
        return roleBeans;
    }
    @RequestMapping(value = "queryRoleByUserId")
    @ResponseBody
    public  String []  queryRoleByUserId(String userId) {
        List<UserRoleBean> roleBeans = userService.queryRoleByUserId(userId);
        String [] ids =new String[roleBeans.size()];
        for (int i = 0; i <roleBeans.size() ; i++) {
            ids[i]= String.valueOf(roleBeans.get(i).getRoleId());
        }
        return ids;
    }

/*    //更改指定人
    @RequestMapping("updateRoleById")
    @ResponseBody
    public void updateRoleById(RoleBean roleBean){

        dealReportService.updateRoleById(roleBean);
    }*/

    //查询合同管理页面
    @RequestMapping(value = "queryRoleAll", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryRoleAll(Integer page, Integer rows, RoleBean roleBean) {
        PageResult pageResult = userService.queryRoleAll(page, rows, roleBean);
        return pageResult;
    }
   /* //修改用户角色
    @RequestMapping(value = "saveUserRole", method = RequestMethod.POST)
    @ResponseBody
    public int saveUserRole(Integer userId,String ids) {
        return userService.saveUserRole(userId,ids);
    }*/

    @RequestMapping("saveUserRole")
    @ResponseBody
    public boolean saveRole(String userId,Integer [] roleId,UserMain userMain){
        try {
            userService.saveRole(userId,roleId,userMain);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //用户页面新增
    @RequestMapping("addUser")
    @ResponseBody
    public HashMap<String, String>  addUser(UserMain userMain) {
        HashMap<String, String> result= new HashMap<String, String>();;
        try {
            if ((userMain != null )) {
                UserMain userInfoByLoginNumber = userService.getUserInfoByLoginNumber(userMain.getLoginNumber());
                if(userInfoByLoginNumber==null){
                    int count =   userService.addUser(userMain);
                    if (count == 0) {
                        result.put("code", "1");
                        result.put("msg", "用户新增失败！");
                        logger.info(this.getClass() + "，用户新增失败！");
                        return result;
                }
                }else{
                    result.put("code", "1");
                    result.put("msg", "用户账号重复,请更换！");
                    logger.info(this.getClass() + "，用户账号重复,请更换！！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "用户新增失败！");
                logger.info(this.getClass() + "，用户新增失败+对象为空！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "用户新增失败！");
            logger.info(this.getClass() + "，用户新增失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }


    //用户批量删除
    @RequestMapping("delUser")
    @ResponseBody
    public  HashMap<String, String>  delAll(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   userService.delUser(ids);;
                if (count == 0) {
                    result = new HashMap<String, String>();
                    result.put("code", "1");
                    result.put("msg", "用户删除失败！");
                    logger.info(this.getClass() + "，用户删除失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "用户删除失败！");
                logger.info(this.getClass() + "，用户删除失败！");
            }

        } catch (Exception e) {
            result = new HashMap<String, String>();
            result.put("code", "3");
            result.put("msg", "用户删除失败！");
            logger.info(this.getClass() + "，用户删除失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
        return result;
    }

}