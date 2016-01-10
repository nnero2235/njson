package com.nnero.njson;

import com.nnero.njson.parse.JSONLexer;
import com.nnero.njson.parse.exception.JSONTokenException;
import com.nnero.njson.parse.Token;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/1/9 下午11:16
 * <p/>
 * Function:测试
 * <p/>
 * ************************************************
 */
public class Test {
    public static void main(String[] args) throws JSONTokenException {
        testLexer();
    }

    private static void testLexer() throws JSONTokenException {
        String json = "{\"name\":\"nnero\"}";
        String json1 = "[\n" +
                "    {\"StudentID\":  100   ,\"Name\":null,\"Hometown\":\"china\"},\n" +
                "    {\"StudentID\": 101,\"isName\":false,\"isHometown\": true  }\n" +
                "]";
        JSONLexer lexer = new JSONLexer(json1);
        Token token = null;
        while ((token = lexer.nextToken()) != null){
            if(token.getType() == Token.Type.EOF){
                System.out.println(token.toString());
                break;
            } else if(token.getType() == Token.Type.QUOT){
                lexer.checkoutState(JSONLexer.STATE_STRING);
            } else if(token.getType() == Token.Type.SPECIAL){
                lexer.checkoutState(JSONLexer.STATE_SPECIAL);
            }
            System.out.println(token.toString());
        }
    }

}
