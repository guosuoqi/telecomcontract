package com.dx.model.site;

public class EquipmentRRUAAU {

    private Integer id;
    /**
     *电信编码 唯一关联标识
     */
    private String dxCode;
    /**
     *rru编码
     */
    private String rruCode;
    /**
     *rru名字
     */
    private String rruName;
    /**
     *管理员id
     */
    private Integer netCareId;
    /**
     *管理员名字
     */
    private String netCareName;
    /**
     *工作类型 3/4/5  G
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

    public String getRruCode() {
        return rruCode;
    }

    public void setRruCode(String rruCode) {
        this.rruCode = rruCode;
    }

    public String getRruName() {
        return rruName;
    }

    public void setRruName(String rruName) {
        this.rruName = rruName;
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
