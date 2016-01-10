package com.nnero.njson.parse;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/9 下午8:50
 * <p/>
 * Function:词法分析器 接口定义
 * <p/>
 * ************************************************
 */
public interface Lexer {

    /**
     * 下一个词素
     * @return 词素
     */
    public Token nextToken() throws JSONTokenException;
}
