package com.nnero.njson.parse.exception;

import java.util.concurrent.ExecutorCompletionService;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/10 下午9:33
 * <p/>
 * Function:json语法异常
 * <p/>
 * ************************************************
 */
public class JSONGrammarException extends Exception {

    public enum Type{
        NOT_JSON_OBJ,
        NOT_JSON_ARR
    }

    public JSONGrammarException(String msg){
        super(msg);
    }
}
