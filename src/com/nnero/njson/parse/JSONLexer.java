package com.nnero.njson.parse;

import com.nnero.njson.parse.Token.Type;
import com.nnero.njson.parse.exception.JSONTokenException;
import com.nnero.njson.util.ExceptionUtil;

import static com.nnero.njson.parse.Token.Type.*;

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

    @Override
    public String getTokenString(Type type) {
        switch (type){
        case L_BRACE:
            return "{";
        case R_BRACE:
            return "}";
        case L_BRACKET:
            return "[";
        case R_BRACKET:
            return "]";
        case COLON:
            return ":";
        case COMMA:
            return ",";
        case QUOT:
            return "\"";
        case NULL:
            return "null";
        case TRUE:
            return "true";
        case FALSE:
            return "false";
        default:
            return "no token content";
        }
    }

    @Override
    public Token nextToken() throws JSONTokenException {
        if(mState == STATE_NORMAL){
            return nextNormalToken();
        } else if(mState == STATE_STRING){
            return nextStringToken();
        } else {
            throw new RuntimeException("no state of jsonlexer");
        }
    }

    private Token nextNormalToken() throws JSONTokenException {
        while (mIndex < mLength) {
            if(mChar == ' '||mChar == '\t' || mChar == '\n' || mChar == '\r') {
                next();
            } else if(mChar == '{') {
                next();
                return Token.createToken(L_BRACE);
            } else if(mChar == '}') {
                next();
                return Token.createToken(Type.R_BRACE);
            } else if(mChar == '\"') {
                mState = STATE_STRING;
                next();
                return Token.createToken(Type.QUOT);
            } else if(mChar == ':') {
                next();
                return Token.createToken(Type.COLON);
            } else if(mChar == ',') {
                next();
                return Token.createToken(Type.COMMA);
            } else if(mChar == '[') {
                next();
                return Token.createToken(Type.L_BRACKET);
            } else if(mChar == ']') {
                next();
                return Token.createToken(Type.R_BRACKET);
            } else if(mChar == 'f' || mChar == 't' || mChar == 'n') {
                return nextSpecialToken();
            } else if(Character.isDigit(mChar)) {
                return nextNumberToken();
            } else {
                throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(JSONTokenException.Type.INVALID_CHAR, String.valueOf(mChar)));
            }
        }
        return Token.createToken(Type.EOF);
    }

    private Token nextStringToken() throws JSONTokenException {
        StringBuilder sb = new StringBuilder();
        while (mIndex<mLength){
            if(mChar == '\"'){
                next();
                mState = STATE_NORMAL;
                return Token.createToken(Type.STRING,sb.toString());
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
                return Token.createToken(Type.TRUE,"true");
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
                return Token.createToken(Type.FALSE,"false");
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
                return Token.createToken(Type.NULL, "null");
            } else {
                throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                        JSONTokenException.Type.INVALID_NULL, sb.toString()));
            }
        }
        throw new JSONTokenException(ExceptionUtil.createJSONTokenExceptionMsg(
                JSONTokenException.Type.NOT_COMPLETE, "value : null"));
    }

    private Token nextNumberToken(){
        StringBuilder sb = new StringBuilder();
        do{
            sb.append(mChar);
            next();
        }while (Character.isDigit(mChar));
        return Token.createToken(Type.NUMBER,sb.toString());
    }

    //下一个字符
    private void next(){
        if(++mIndex < mLength)
            mChar = mInput.charAt(mIndex);
    }
}
