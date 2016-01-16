package com.nnero.njson;

import java.util.ArrayList;
import java.util.List;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/3 下午10:43
 * <p/>
 * Function: json数组
 * <p/>
 * ************************************************
 */
public class JSONArray {

    private List<Object> values;

    public JSONArray(){
        this.values = new ArrayList<Object>();
    }

    public JSONObject getObj(int index){
        Object o = values.get(index);
        return o instanceof JSONObject ? (JSONObject)o : null;
    }

    public int getInt(int index){
        Object o = values.get(index);
        return o instanceof String ? Integer.parseInt((String)o) : -1;
    }

    public String getString(int index){
        Object o = values.get(index);
        return o instanceof String ? (String)o : null;
    }

    public boolean getBoolean(int index){
        Object o = values.get(index);
        return o instanceof String ? Boolean.valueOf((String) o) : false;
    }

    public int size(){
        return values.size();
    }

    public void put(Object o){
        values.add(o);
    }
}
