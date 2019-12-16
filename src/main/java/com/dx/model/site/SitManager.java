package com.dx.model.site;

public class SitManager {


    private Integer id;
    /**
     *基站编码
     */
    private String baseCode;
    /**
     * 站點名稱
     */
    private String baseName;
    /**
     * 电费缴纳方
     */
    private String powerMan;
    /**
     * 租赁费缴纳方
     */
    private String rentPayer;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     *基站产权
     */
    private String baseProperty;
    /**
     *电信编码 唯一关联标识
     */
    private String dxCode;
    /**
     *铁塔站址编码
     */
    private String ttCode;
    /**
     *3Gbbu个数
     */
    private Integer threeBbuCount;
    /**
     *4Gbbu个数
     */
    private Integer fourBbuCount;
    /**
     *5Gbbu个数
     */
    private Integer fiveBbuCount;
    /**
     *3Grru个数
     */
    private Integer threeRruCount;
    /**
     *4Grru个数
     */
    private Integer fourRruCount;
    /**
     *5Grru个数
     */
    private Integer fiveAauCount;
    /**
     *olt 个数
     */
    private Integer oltCount;
    /**
     *总耗电量
     */
    private Double powerConsume;

    /**
     * 耗電量VO
     */
    private Double power;

    /**
     * ipRan个数
     */
    private Integer ipranCount;

    /**
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getBaseProperty() {
        return baseProperty;
    }

    public void setBaseProperty(String baseProperty) {
        this.baseProperty = baseProperty;
    }

    public String getDxCode() {
        return dxCode;
    }

    public void setDxCode(String dxCode) {
        this.dxCode = dxCode;
    }

    public String getTtCode() {
        return ttCode;
    }

    public void setTtCode(String ttCode) {
        this.ttCode = ttCode;
    }

    public Integer getThreeBbuCount() {
        return threeBbuCount;
    }

    public void setThreeBbuCount(Integer threeBbuCount) {
        this.threeBbuCount = threeBbuCount;
    }

    public Integer getFourBbuCount() {
        return fourBbuCount;
    }

    public void setFourBbuCount(Integer fourBbuCount) {
        this.fourBbuCount = fourBbuCount;
    }

    public Integer getFiveBbuCount() {
        return fiveBbuCount;
    }

    public void setFiveBbuCount(Integer fiveBbuCount) {
        this.fiveBbuCount = fiveBbuCount;
    }

    public Integer getThreeRruCount() {
        return threeRruCount;
    }

    public void setThreeRruCount(Integer threeRruCount) {
        this.threeRruCount = threeRruCount;
    }

    public Integer getFourRruCount() {
        return fourRruCount;
    }

    public void setFourRruCount(Integer fourRruCount) {
        this.fourRruCount = fourRruCount;
    }

    public Integer getFiveAauCount() {
        return fiveAauCount;
    }

    public void setFiveAauCount(Integer fiveAauCount) {
        this.fiveAauCount = fiveAauCount;
    }

    public Integer getOltCount() {
        return oltCount;
    }

    public void setOltCount(Integer oltCount) {
        this.oltCount = oltCount;
    }

    public Double getPower() {
            return power;
            }

    public void setPower(Double power) {
            this.power = power;
            }

    public Integer getIpranCount() {
            return ipranCount;
            }

    public void setIpranCount(Integer ipranCount) {
            this.ipranCount = ipranCount;
            }

    public Double getPowerConsume() {
        return powerConsume;
    }

    public void setPowerConsume(Double powerConsume) {
        this.powerConsume = powerConsume;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getPowerMan() {
        return powerMan;
    }

    public void setPowerMan(String powerMan) {
        this.powerMan = powerMan;
    }

    public String getRentPayer() {
        return rentPayer;
    }

    public void setRentPayer(String rentPayer) {
        this.rentPayer = rentPayer;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
