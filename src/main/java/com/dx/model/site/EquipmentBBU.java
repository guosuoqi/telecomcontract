package com.dx.model.site;

public class EquipmentBBU {

    private Integer id;

    private String dxCode;

    private String bbuCode;

    private String bbuName;

    private Integer netCareId;

    private String netCareName;

    private Integer networkType;

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
