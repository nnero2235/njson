package com.nnero.njson.parse.exception;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/9 下午9:32
 * <p/>
 * Function:json解析异常
 * <p/>
 * ************************************************
 */
public class JSONTokenException extends Exception {

    public enum Type{
        INVALID_CHAR,
        NOT_EXISTS,
        INVALID_STRING,
        INVALID_NUMBER,
        INVALID_TRUE,
        INVALID_FALSE,
        INVALID_NULL,
        NOT_COMPLETE
    }

    public static final String INVALID_CHAR_EXCEPTION="invalid char %s";
    public static final String INVALID_FORMAT_EXCEPTION = "invalid format %s";
    public static final String INVALID_STRING_EXCEPTION = "string is not ended: %s";
    public static final String INVALID_NUMBER_EXCEPTION = "invalid number %s";
    public static final String INVALID_NULL_EXCEPTION = "invalid null %s";
    public static final String INVALID_TRUE_EXCEPTION = "invalid true %s";
    public static final String INVALID_FALSE_EXCEPTION = "invalid false %s";
    public static final String NOT_COMPLETE_EXCEPTION = "json not complete: %s";
    public static final String NOT_EXISTS_EXCEPTION = "%s is not exists";

    public JSONTokenException(String msg){
        super(msg);
    }
}
