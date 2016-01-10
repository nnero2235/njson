package com.nnero.njson.parse;

import com.nnero.njson.parse.exception.JSONTokenException;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/10 下午3:19
 * <p/>
 * Function: 语法解析器 基于LL(*) 算法
 * <p/>
 * ************************************************
 */
public abstract class Parser {

    protected Lexer mInput;
    protected Token[] mLookahead;
    protected int mLookaheadIndex;

    public Parser(Lexer input,int lookaheadNumber) {
        this.mInput = input;
        this.mLookahead = new Token[lookaheadNumber];
        this.mLookaheadIndex = 0;
    }

}
