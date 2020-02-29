package com.dx.model.common;

import java.util.HashMap;
import java.util.Map;

public enum PoiTypeEnum {

	POI_TYPE_CONTRACT(1,0,"poi合同类型"),
    POI_TYPE_3G_BBU(2,3,"3GBBU"),
    POI_TYPE_4G_BBU(3,4,"4GBBU"),
    POI_TYPE_5G_BBU(4,5,"5GBBU"),
    POI_TYPE_3G_RRU(5,3,"3GRRU"),
    POI_TYPE_4G_RRU(6,4,"4GRRU"),
    POI_TYPE_5G_AAU(7,5,"5GAAU"),
    POI_TYPE_OLT(8,0,"OLT"),
    POI_TYPE_IPRAN(9,5,"ipran"),
    POI_TYPE_SITE(10,0,"site"),
    POI_TYPE_BoFen(11,0,"波分");

    private Integer key;

    private Integer num;

    private String desc;

    PoiTypeEnum(Integer key,Integer num, String desc){
        this.key=key;this.num=num;this.desc=desc;
    }

    public static PoiTypeEnum getEnumByKey(Integer key){
        if(key!=null){
            for(PoiTypeEnum item: PoiTypeEnum.values()){
                if(item.getKey().equals(key)){
                    return item;
                }
            }
        }
        return null;
    }

    public Integer getNum() {
        return num;
    }
    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

	public static Map<Object,Object> getEnumMap(){
		Map<Object,Object> map = new HashMap<Object,Object>();
		for(PoiTypeEnum item : PoiTypeEnum.values()){
			map.put(item.getKey(),item.getDesc());
		}
		return map;
	}
}
