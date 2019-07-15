package com.dx.model.user;

public class UserMain {

    private Integer id; //id

    private String loginNumber; //账号

    private String password;//密码

    private String userName;//用户名

    private String mobile;//手机号

    private String email;//邮箱

    private String userstate;//用户状态

    private String makedate;//创建时间

    private String orgCode;//管理机构

    private String roleNames;//管理机构

    private String operator;//操作人

    private String wechatUserId;//对应微信id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public String getMakedate() {
        return makedate;
    }

    public void setMakedate(String makedate) {
        this.makedate = makedate;
    }

    public String getComcode() {
        return orgCode;
    }

    public void setComcode(String comcode) {
        this.orgCode = comcode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId;
    }


}
