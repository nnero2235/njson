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
    enum Type{
        OBJ,
        ARR,
        NUMBER,
        STRING,
        NULL,
        TRUE,
        FALSE
    }

    private Type mType;
    private String mEntity;

    private Token(Type type,String entity){
        this.mType = type;
        this.mEntity = entity;
    }

    public Type getType() {
        return mType;
    }

    public String getEntity() {
        return mEntity;
    }

    /**
     * 创建token 只能通过该方法
     * @param type
     * @param entity
     * @return
     */
    public static Token createToken(Type type,String entity){
        return new Token(type,entity);
    }
}
