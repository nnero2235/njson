package com.nnero.njson.parse;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/10 下午3:33
 * <p/>
 * Function:json解析器 基于LL(2)算法
 * <p/>
 * ************************************************
 */
public class JSONParser extends Parser {

    public JSONParser(Lexer input) {
        super(input, 2);
    }
}
