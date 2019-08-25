package com.dx.model.site;

public class EquipmentOLT {

    private Integer id;
    /**
     *电信编码-唯一关联标识
     */
    private String dxCode;
    /**
     *olt编码
     */
    private String oltCode;
    /**
     *olt名称
     */
    private String oltName;
    /**
     *管理员id
     */
    private Integer netCareId;
    /**
     *管理员名字
     */
    private String netCareName;
    /**
     *类型 (3/4/5 G)
     */
    private Integer networkType;
    /**
     * 耗电量
     */
    private Double power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDxCode() {
        return dxCode;
    }

    public void setDxCode(String dxCode) {
        this.dxCode = dxCode;
    }

    public String getOltCode() {
        return oltCode;
    }

    public void setOltCode(String oltCode) {
        this.oltCode = oltCode;
    }

    public String getOltName() {
        return oltName;
    }

    public void setOltName(String oltName) {
        this.oltName = oltName;
    }

    public Integer getNetCareId() {
        return netCareId;
    }

    public void setNetCareId(Integer netCareId) {
        this.netCareId = netCareId;
    }

    public String getNetCareName() {
        return netCareName;
    }

    public void setNetCareName(String netCareName) {
        this.netCareName = netCareName;
    }

    public Integer getNetworkType() {
        return networkType;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
