package com.nnero.njson.parse;

import com.nnero.njson.JSONArray;
import com.nnero.njson.JSONObject;
import com.nnero.njson.parse.exception.JSONGrammarException;
import com.nnero.njson.parse.exception.JSONTokenException;
import com.nnero.njson.util.ExceptionUtil;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

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

    private static final String NULL = "null";
    private String mTempString;
    private String mTempBoolean;
    private String mNumberString;

    public JSONParser(Lexer input) throws JSONTokenException {
        super(input, 2);
        init();
    }

    private void init() throws JSONTokenException {
        for(int i=1;i<=2;i++){
            lookahead();
        }
    }

    /**
     * 解析为obj
     * @return
     * @throws JSONTokenException
     * @throws JSONGrammarException
     */
    public JSONObject parseObj() throws JSONTokenException, JSONGrammarException {
        return parseObj(new JSONObject());
    }

    /**
     * 解析为arr
     * @return
     * @throws JSONGrammarException
     * @throws JSONTokenException
     */
    public JSONArray parseArr() throws JSONGrammarException, JSONTokenException {
        return parseArr(new JSONArray());
    }


    private JSONObject parseObj(JSONObject obj) throws JSONTokenException, JSONGrammarException {
        match(Token.Type.L_BRACE);
        maps(obj);
        match(Token.Type.R_BRACE);
        return obj;
    }

    private JSONArray parseArr(JSONArray arr){
        return null;
    }

    private void maps(JSONObject obj) throws JSONTokenException, JSONGrammarException {
        map(obj);
        if(getLookaheadType(1) == Token.Type.COMMA){
            match(Token.Type.COMMA);
            maps(obj);
        }
    }

    private void map(JSONObject obj) throws JSONTokenException, JSONGrammarException {
        match(Token.Type.QUOT);
        match(Token.Type.STRING);
        String name = mTempString;

        match(Token.Type.COLON);

        if(getLookaheadType(1) == Token.Type.QUOT){
            match(Token.Type.QUOT);
            match(Token.Type.STRING);
            String value = mTempString;
            obj.put(name,value);
        } else if(getLookaheadType(1) == Token.Type.FALSE){
            match(Token.Type.FALSE);
            String value = mTempBoolean;
            obj.put(name,value);
        } else if(getLookaheadType(1) == Token.Type.TRUE){
            match(Token.Type.TRUE);
            String value = mTempBoolean;
            obj.put(name,value);
        } else if(getLookaheadType(1) == Token.Type.NULL){
            match(Token.Type.NULL);
            obj.put(name,NULL);
        } else if(getLookaheadType(1) == Token.Type.NUMBER){
            match(Token.Type.NUMBER);
            String value = mNumberString;
            obj.put(name,value);
        } else {
            throw new JSONGrammarException("excepting null or false or true or \"string\" or number" + "but "+getLookaheadType(1)+" found");
        }
    }
    
//*************************************************************

    private void lookahead() throws JSONTokenException {
        mLookahead[mLookaheadIndex] = mInput.nextToken();
        mLookaheadIndex = (mLookaheadIndex + 1)%2;
    }

    private void match(Token.Type type) throws JSONTokenException, JSONGrammarException {
        Token token = getLookahead(1);
        if(type == token.getType()){
            saveTokenValue(token);
            lookahead();
        } else {
            throw new JSONGrammarException("excepting : "+mInput.getTokenString(type)+"but "+getLookaheadType(1)+" found");
        }
    }

    private void saveTokenValue(Token token){
        if(token.getType() == Token.Type.STRING){ //STRING要保存值
            mTempString = token.getValue();
        } else if(token.getType() == Token.Type.FALSE){
            mTempBoolean = token.getValue();
        } else if(token.getType() == Token.Type.TRUE){
            mTempBoolean = token.getValue();
        } else if(token.getType() == Token.Type.NUMBER){
            mNumberString = token.getValue();
        }
    }
    // i=1 表示第一个字符 i=2表示第二个
    private Token getLookahead(int i) throws JSONTokenException {
        return mLookahead[(mLookaheadIndex+i-1)%2];
    }

    private Token.Type getLookaheadType(int i) throws JSONTokenException {
        return getLookahead(i).getType();
    }
}
