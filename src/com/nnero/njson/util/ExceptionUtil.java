package com.nnero.njson.util;

import com.nnero.njson.parse.exception.JSONGrammarException;
import com.nnero.njson.parse.exception.JSONTokenException;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/9 下午9:39
 * <p/>
 * Function: 生产异常的工具类
 * <p/>
 * ************************************************
 */
public class ExceptionUtil {

    public static String createJSONTokenExceptionMsg(JSONTokenException.Type type, String msg){
        if(type == JSONTokenException.Type.INVALID_CHAR){
            return String.format(JSONTokenException.INVALID_CHAR_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.NOT_COMPLETE){
            return String.format(JSONTokenException.NOT_COMPLETE_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.INVALID_STRING){
            return String.format(JSONTokenException.INVALID_CHAR_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.INVALID_NUMBER){
            return String.format(JSONTokenException.INVALID_NUMBER_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.INVALID_NULL){
            return String.format(JSONTokenException.INVALID_NULL_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.INVALID_FALSE){
            return String.format(JSONTokenException.INVALID_FALSE_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.INVALID_TRUE){
            return String.format(JSONTokenException.INVALID_TRUE_EXCEPTION,msg);
        } else if(type == JSONTokenException.Type.NOT_EXISTS){
            return String.format(JSONTokenException.NOT_EXISTS_EXCEPTION,msg);
        } else {
            return "ERROR:JSONTokenException!";
        }
    }

    public static String createJSONGrammarExceptionMsg(JSONGrammarException.Type type,String msg){
        if(type == JSONGrammarException.Type.NOT_JSON_OBJ){
            return String.format("source json is not jsonObj,start char invalid");
        } else if(type == JSONGrammarException.Type.NOT_JSON_ARR){
            return String.format("source json is not jsonArray , start char invalid");
        } else {
            return "ERROR:JSONGrammarException";
        }
    }
}
