package com.dx.model.site;

public class EquipmentBBU {

    private Integer id;
    /**
     *电信编码-唯一关联标识
     */
    private String dxCode;
    /**
     *bbu编码
     */
    private String bbuCode;
    /**
     *Bbu名称
     */
    private String bbuName;
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

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

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

    public String getBbuCode() {
        return bbuCode;
    }

    public void setBbuCode(String bbuCode) {
        this.bbuCode = bbuCode;
    }

    public String getBbuName() {
        return bbuName;
    }

    public void setBbuName(String bbuName) {
        this.bbuName = bbuName;
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
}
