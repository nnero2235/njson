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

    private Map<String,Object> map;

    public JSONObject(){
        map = new HashMap<String, Object>();
    }

    /**
     * 根据name 获得jsonobj
     * @param name
     * @return
     */
    public JSONObject getJsonObj(String name){
        Object o = checkNull(name);
        return (JSONObject)o;
    }

    /**
     * 根据name 获得jsonarray
     * @param name
     * @return
     */
    public JSONArray getJsonArray(String name){
        Object o = checkNull(name);
        return o instanceof JSONArray ? (JSONArray)o : null;
    }

    /**
     * 获得string
     * @param name
     * @return
     */
    public String getString(String name){
        Object o = checkNull(name);
        return (String) o;
    }

    /**
     * 获得int
     * @param name
     * @return
     */
    public int getInt(String name){
        Object o = checkNull(name);
        int value = Integer.parseInt((String) o);
        return value;
    }

    /**
     * 获得boolean值
     * @param name
     * @return
     */
    public boolean getBoolean(String name){
        Object obj = checkNull(name);
        boolean b = Boolean.valueOf((String) obj);
        return b;
    }

    public void put(String name,Object value){
        map.put(name,value);
    }

    private Object checkNull(String name){
        Object o = map.get(name);
        if (o == null){
            return null;
        } else if(o instanceof String && "null".equals(o)){
            return null;
        } else {
            return o;
        }
    }
}
