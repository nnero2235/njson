package com.nnero.njson;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/3 下午10:41
 * <p/>
 * Function: 解析主类  外部主要使用这个类 来解析
 * <p/>
 * ************************************************
 */
public class NJson {
    /**
     * 将 一个bean对象 转化为一个json字符串
     * @param o
     * @param <T>
     * @return
     */
    public static <T> String toJsonString(T o){
        //TODO:将一个实例对象o 转化为json字符串
        return null;
    }

    /**
     * 将一个json 字符串 转化为一个bean对象
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T fromJsonString(String json){
        //TODO:将json字符串 转化为 一个对象
        return null;
    }

    /**
     * 从一个json字符串 获得一个obj
     * @param json
     * @return
     */
    public static JSONObject getJsonObj(String json){
        return null;
    }

    /**
     * 从一个json字符串 获得一个array
     * @param json
     * @return
     */
    public static JSONArray getJsonArray(String json){
        return null;
    }
}
