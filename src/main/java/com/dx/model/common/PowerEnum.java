package com.dx.model.common;

import java.util.HashMap;
import java.util.Map;

public enum PowerEnum {

	T_BBU_POWER(3.0,"3GBBu耗电量"),
	F_BBU_POWER(4.0,"4GBBu耗电量"),
	FIVE_BBU_POWER(5.0,"5GBBu耗电量"),
    T_RRU_POWER(3.0,"3GBBu耗电量"),
    F_RRU_POWER(4.0,"4GBBu耗电量"),
    FIVE_AAU_POWER(5.0,"5GBBu耗电量");
    private Double key;

    private String desc;

    PowerEnum(Double key, String desc){
        this.key=key;this.desc=desc;
    }

    public Double getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

	public static Map<Object,Object> getEnumMap(){
		Map<Object,Object> map = new HashMap<Object,Object>();
		for(PowerEnum item : PowerEnum.values()){
			map.put(item.getKey(),item.getDesc());
		}
		return map;
	}
}
