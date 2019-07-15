package com.dx.model.contract;

public class ContractExtension {

    private Integer extensionId;//续费合同id

    private String  contractName;//合同名称

    private String cost;//费用

    private String extensionEnd;//续费截止日期

    private String operator;//经办人

    private String remark;//备注

    private String startTime;//开始时间

    private String endTime;//结束时间

    public Integer getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(Integer extensionId) {
        this.extensionId = extensionId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getExtensionEnd() {
        return extensionEnd;
    }

    public void setExtensionEnd(String extensionEnd) {
        this.extensionEnd = extensionEnd;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
