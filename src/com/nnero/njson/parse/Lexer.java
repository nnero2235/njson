package com.nnero.njson.parse;

import com.nnero.njson.parse.exception.JSONTokenException;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/9 下午8:50
 * <p/>
 * Function:词法分析器 接口定义 lexer也是有限状态机
 * <p/>
 * ************************************************
 */
public interface Lexer {

    /**
     * 下一个词素
     * @return 词素
     */
    Token nextToken() throws JSONTokenException;

    /**
     * 切换状态
     * @param state 状态
     */
    void checkoutState(int state);
}
