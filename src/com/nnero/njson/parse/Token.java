package com.nnero.njson.parse;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/4 下午10:30
 * <p/>
 * DESC: 字符流 token
 * <p/>
 * ************************************************
 */
public class Token {
    //token type
    public enum Type{
        L_BRACE, R_BRACE, L_BRACKET, R_BRACKET, //{} []
        COMMA, QUOT, COLON,  //,":
        EOF, SPECIAL, //这2个并非实际存在 只是一种标志
        NUMBER, STRING, NULL, TRUE, FALSE //值
    }

    private Type mType;
    private String mValue;

    private Token(Type type,String value){
        this.mType = type;
        this.mValue = value;
    }

    public Type getType() {
        return mType;
    }

    public String getValue() {
        return mValue;
    }

    /**
     * 创建token 有值情况
     * @param type
     * @param value
     * @return
     */
    public static Token createToken(Type type,String value){
        return new Token(type,value);
    }

    /**
     * 创建token 无值情况
     * @param type
     * @return
     */
    public static Token createToken(Type type){
        return new Token(type,null);
    }

    @Override
    public String toString() {
        if(mValue != null)
            return "<"+mType+","+mValue+">";
        else
            return "<"+mType+">";
    }
}
