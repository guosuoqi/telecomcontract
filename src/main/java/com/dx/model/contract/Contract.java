package com.dx.model.contract;

public class Contract {

    private Integer contractId;//合同id

    private String contractName;//合同名字

    private String county;//县

    private String address; //具体地址

    private String yearRental;//年租金

    private String sunRental;//总租金

    private String contractNum;//合同编号

    private String contractFirst;//合同甲方

    private String payee;//收款人

    private String planYear;//拟租年份

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String payEndTime;//付费截止日期

    private Integer roomType;//机房类型

    private String roomTypeName;//机房类型名称

    private String jifangName; //机房名称

    private Integer towerType;//塔栀类型

    private String towerTypeName;//是否有基站

    private Integer contractType;//合同类型

    private String extenxionOperator;//续约经办人

    private String renewOperator;//续费经办人

    private String remark;//备注

    private String contractTypeName;//合同类型名称

    private Integer extenxionStatus;//续约状态

    private Integer renewStatus;//续费状态

    private String roomName;  //站点名称

    private Integer number;//计数

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getYearRental() {
        return yearRental;
    }

    public void setYearRental(String yearRental) {
        this.yearRental = yearRental;
    }

    public String getSunRental() {
        return sunRental;
    }

    public void setSunRental(String sunRental) {
        this.sunRental = sunRental;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getContractFirst() {
        return contractFirst;
    }

    public void setContractFirst(String contractFirst) {
        this.contractFirst = contractFirst;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(String payEndTime) {
        this.payEndTime = payEndTime;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getTowerType() {
        return towerType;
    }

    public void setTowerType(Integer towerType) {
        this.towerType = towerType;
    }

    public String getTowerTypeName() {
        return towerTypeName;
    }

    public void setTowerTypeName(String towerTypeName) {
        this.towerTypeName = towerTypeName;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public Integer getExtenxionStatus() {
        return extenxionStatus;
    }

    public void setExtenxionStatus(Integer extenxionStatus) {
        this.extenxionStatus = extenxionStatus;
    }

    public String getExtenxionOperator() {
        return extenxionOperator;
    }

    public void setExtenxionOperator(String extenxionOperator) {
        this.extenxionOperator = extenxionOperator;
    }

    public String getRenewOperator() {
        return renewOperator;
    }

    public String getJifangName() {
        return jifangName;
    }

    public void setJifangName(String jifangName) {
        this.jifangName = jifangName;
    }

    public void setRenewOperator(String renewOperator) {
        this.renewOperator = renewOperator;
    }

    public Integer getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(Integer renewStatus) {
        this.renewStatus = renewStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
