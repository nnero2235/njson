package com.nnero.njson.parse;

import com.nnero.njson.parse.exception.JSONTokenException;
import com.nnero.njson.util.ExceptionUtil;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/9 下午8:51
 * <p/>
 * Function: json词法分析器
 * <p/>
 * ************************************************
 */
public class JSONLexer implements Lexer {

    public static final int STATE_NORMAL = 1;
    public static final int STATE_STRING = 2;
    public static final int STATE_SPECIAL = 3;

    private int mState;
    private String mInput;
    private char mChar;
    private int mIndex;
    private int mLength; //input长度

    public JSONLexer(String input){
        this.mInput = input;
        this.mLength = input.length();
        this.mChar = mInput.charAt(0);
        this.mState = STATE_NORMAL;
    }

    /**
     * 切换状态
     * @param state 状态机状态
     */
    @Override
    public void checkoutState(int state){
        mState = state;
    }

    @Override
    public Token nextToken() throws JSONTokenException {
        if(mState == STATE_NORMAL){
            return nextNormalToken();
        } else if(mState == STATE_STRING){
            return nextStringToken();
        } else if(mState == STATE_SPECIAL) {
            return nextSpecialToken();
        } else {
            throw new RuntimeException("no state of jsonlexer");
        }
    }

    private Token nextNormalToken() throws JSONTokenException {
        while (mIndex < mLength) {
            if(mChar == ' '||mChar == '\t' || mChar == '\n' || mChar == '\b') {
                next();
            } else if(mChar == '{') {
                next();
                return Token.createToken(Token.Type.L_BRACE);
            } else if(mChar == '}') {
                next();
                return Token.createToken(Token.Type.R_BRACE);
            } else if(mChar == '\"') {
                next();
                return Token.createToken(Token.Type.QUOT);
            } else if(mChar == ':') {
                next();
                return Token.createToken(Token.Type.COLON);
            } else if(mChar == ',') {
                next();
                return Token.createToken(Token.Type.COMMA);
            } else if(mChar == '[') {
                next();
                return Token.createToken(Token.Type.L_BRACKET);
            } else if(mChar == ']') {
                next();
                return Token.createToken(Token.Type.R_BRACKET);
            } else if(mChar == 'f' || mChar == 't' || mChar == 'n') {
                return Token.createToken(Token.Type.SPECIAL);
            } else if(Character.isDigit(mChar)) {
                Token token = Token.createToken(Token.Type.NUMBER, String.valueOf(mChar));
                next();
                return token;
            } else {
                throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(JSONTokenException.Type.INVALID_CHAR, String.valueOf(mChar)));
            }
        }
        return Token.createToken(Token.Type.EOF);
    }

    private Token nextStringToken() throws JSONTokenException {
        StringBuilder sb = new StringBuilder();
        while (mIndex<mLength){
            if(mChar == '\"'){
                next();
                mState = STATE_NORMAL;
                return Token.createToken(Token.Type.STRING,sb.toString());
            } else {
                sb.append(mChar);
            }
            next();
        }
        throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                JSONTokenException.Type.INVALID_STRING, sb.toString()));
    }

    private Token nextTrueToken() throws JSONTokenException {
        StringBuilder sb = new StringBuilder();
        if(mIndex + 3 < mLength) {
            for(int i=0;i<4;i++) {
                sb.append(mChar);
                next();
            }
            if("true".equals(sb.toString())){
                mState = STATE_NORMAL;
                return Token.createToken(Token.Type.TRUE,"true");
            } else {
                throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                        JSONTokenException.Type.INVALID_TRUE,sb.toString()));
            }
        }
        throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                JSONTokenException.Type.NOT_COMPLETE,"value : true"));
    }

    private Token nextSpecialToken()throws JSONTokenException{
        if(mChar == 't'){
            return nextTrueToken();
        } else if(mChar == 'f'){
            return nextFalseToken();
        } else if(mChar == 'n'){
            return nextNullToken();
        } else {
            throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(JSONTokenException.Type.NOT_EXISTS,String.valueOf(mChar)));
        }
    }

    private Token nextFalseToken() throws JSONTokenException {
        StringBuilder sb = new StringBuilder();
        if(mIndex + 4 < mLength) {
            for(int i=0;i<5;i++) {
                sb.append(mChar);
                next();
            }
            if("false".equals(sb.toString())){
                mState = STATE_NORMAL;
                return Token.createToken(Token.Type.FALSE,"false");
            } else {
                throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                        JSONTokenException.Type.INVALID_FALSE,sb.toString()));
            }
        }
        throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                JSONTokenException.Type.NOT_COMPLETE,"value : false"));
    }

    private Token nextNullToken() throws JSONTokenException {
        StringBuilder sb = new StringBuilder();
        if (mIndex + 3 < mLength) {
            for (int i = 0; i < 4; i++) {
                sb.append(mChar);
                next();
            }
            if ("null".equals(sb.toString())) {
                mState = STATE_NORMAL;
                return Token.createToken(Token.Type.NULL, "null");
            } else {
                throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                        JSONTokenException.Type.INVALID_NULL, sb.toString()));
            }
        }
        throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                JSONTokenException.Type.NOT_COMPLETE, "value : null"));
    }


    //下一个字符
    private void next(){
        if(++mIndex < mLength)
            mChar = mInput.charAt(mIndex);
    }
}
