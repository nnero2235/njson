package com.nnero.njson.parse;

import com.nnero.njson.JSONArray;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/4 下午10:35
 * <p/>
 * DESC: json词法分析器:就是一个状态机
 * <p/>
 * ************************************************
 */
public class JSONLexier {
    enum State{
        NORMAL,
        SPACE,
        LEFT_BRACE, //大括号
        RIGHT_BRACE,
        LEFT_BRACKET,
        RIGHT_BRACKET,//中括号
        NAME,
        VALUE,
        COMMA //逗号
    }

    private boolean moveCursor;
    private int index;
    private State mState;
    private char[] originChars;

    public JSONLexier(String str){
        this.mState = State.NORMAL;
        this.moveCursor = true;
        this.index = 0;
        this.originChars = str.toCharArray();
    }

    /**
     * read方法每次都会读 json一段 并生产token给parser
     * @return
     */
    public Token read(){
        return null;
    }
}
