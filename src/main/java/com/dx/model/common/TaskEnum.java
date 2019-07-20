package com.dx.model.common;

import java.util.HashMap;
import java.util.Map;

public enum TaskEnum {

	SEND_EMAIL(1,"发送邮件通知");

    private Integer key;

    private String desc;

    TaskEnum(Integer key, String desc){
        this.key=key;this.desc=desc;
    }

    public static TaskEnum getEnumByKey(Integer key){
        if(key!=null){
            for(TaskEnum item:TaskEnum.values()){
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
		for(TaskEnum item : TaskEnum.values()){
			map.put(item.getKey(),item.getDesc());
		}
		return map;
	}
}
