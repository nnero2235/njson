package com.nnero.njson;

import java.util.HashMap;
import java.util.Map;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/3 下午10:43
 * <p/>
 * Function: json对象
 * <p/>
 * ************************************************
 */
public class JSONObject {

    private Map<String,String> map;

    public JSONObject(){
        map = new HashMap<String, String>();
    }

    /**
     * 根据name 获得jsonobj
     * @param name
     * @return
     */
    public JSONObject getJsonObj(String name){
        return null;
    }

    /**
     * 根据name 获得jsonarray
     * @param name
     * @return
     */
    public JSONArray getJsonArray(String name){
        return null;
    }

    /**
     * 获得string
     * @param name
     * @return
     */
    public String getString(String name){
        return checkNull(name);
    }

    /**
     * 获得int
     * @param name
     * @return
     */
    public int getInt(String name){
        int value = Integer.parseInt(checkNull(name));
        return value;
    }

    public boolean getBoolean(String name){
        boolean b = Boolean.valueOf(checkNull(name));
        return b;
    }

    public void put(String name,String value){
        map.put(name,value);
    }

    private String checkNull(String name){
        if ("null".equals(map.get(name))){
            return null;
        } else {
            return map.get(name);
        }
    }
}
