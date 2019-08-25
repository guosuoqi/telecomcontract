package com.dx.model.site;

public class SitManager {


    private Integer id;
    /**
     *基站编码
     */
    private String baseCode;
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
     *直流耗电量
     */
    private Integer tributaryPowerConsume;
    /**
     *pue值
     */
    private Integer pue;
    /**
     *总耗电量
     */
    private String PowerConsume;

    /**
     * 耗電量VO
     */
    private Double power;

    /**
     * ipRan个数
     */
    private Integer ipranCount;

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

    public Integer getTributaryPowerConsume() {
        return tributaryPowerConsume;
    }

    public void setTributaryPowerConsume(Integer tributaryPowerConsume) {
        this.tributaryPowerConsume = tributaryPowerConsume;
    }

    public Integer getPue() {
        return pue;
    }

    public void setPue(Integer pue) {
        this.pue = pue;
    }

    public String getPowerConsume() {
        return PowerConsume;
    }

    public void setPowerConsume(String powerConsume) {
        PowerConsume = powerConsume;
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
}
