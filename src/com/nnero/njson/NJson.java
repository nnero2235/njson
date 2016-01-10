package com.nnero.njson;

import com.nnero.njson.parse.JSONLexer;
import com.nnero.njson.parse.JSONParser;
import com.nnero.njson.parse.exception.JSONGrammarException;
import com.nnero.njson.parse.exception.JSONTokenException;

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
    public static <T> T fromJsonString(String json,Class<T> clazz){
        //TODO:将json字符串 转化为 一个对象
        return null;
    }

    /**
     * 从一个json字符串 获得一个obj
     * @param json
     * @return
     */
    public static JSONObject getJsonObj(String json) throws JSONGrammarException, JSONTokenException {
        JSONParser parser = new JSONParser(new JSONLexer(json));
        return parser.parseObj();
    }

    /**
     * 从一个json字符串 获得一个array
     * @param json
     * @return
     */
    public static JSONArray getJsonArray(String json) throws JSONTokenException, JSONGrammarException {
        JSONParser parser = new JSONParser(new JSONLexer(json));
        return parser.parseArr();
    }
}
