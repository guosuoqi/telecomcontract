package com.dx.model.common;

import java.util.HashMap;
import java.util.Map;

public enum SiteEnum {

	T_BBU(3,"3GBBu"),
	F_BBU(4,"4GBBu"),
	FIVE_BBU(5,"5GBBu");

    private Integer key;

    private String desc;

    SiteEnum(Integer key, String desc){
        this.key=key;this.desc=desc;
    }

    public static SiteEnum getEnumByKey(Integer key){
        if(key!=null){
            for(SiteEnum item: SiteEnum.values()){
                if(item.getKey().equals(key)){
                    return item;
                }
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

	public static Map<Object,Object> getEnumMap(){
		Map<Object,Object> map = new HashMap<Object,Object>();
		for(SiteEnum item : SiteEnum.values()){
			map.put(item.getKey(),item.getDesc());
		}
		return map;
	}
}
