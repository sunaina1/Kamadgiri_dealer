package com.kamadgiri.dealer.networkReq;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaderUtils {
   Map<String,String> map=new HashMap<>();

    public  void addHeaderParams(String key,String val){
      map.put(key,val);
    }

    public Map<String,String> getHeaderParam(){
        return map;
    }
}
