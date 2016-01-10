package com.nnero.njson.parse;

import com.nnero.njson.JSONArray;
import com.nnero.njson.JSONObject;
import com.nnero.njson.parse.exception.JSONGrammarException;
import com.nnero.njson.parse.exception.JSONTokenException;
import com.nnero.njson.util.ExceptionUtil;

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
//        maps();
        match(Token.Type.R_BRACE);
        return obj;
    }

    private JSONArray parseArr(JSONArray arr){
        return null;
    }
    

    private void lookahead() throws JSONTokenException {
        mLookahead[mLookaheadIndex] = mInput.nextToken();
        mLookaheadIndex = (mLookaheadIndex + 1)%2;
    }

    private void match(Token.Type type) throws JSONTokenException, JSONGrammarException {
        if(type == getLookaheadType(1)){
            lookahead();
        } else {
            throw new JSONGrammarException("excepting : "+mInput.getTokenString(type)+"but "+getLookaheadType(1)+" found");
        }
    }

    private Token getLookahead(int i){
        return mLookahead[(mLookaheadIndex+i-1)%2];
    }

    private Token.Type getLookaheadType(int i){
        return getLookahead(i).getType();
    }
}
