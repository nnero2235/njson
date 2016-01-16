package com.nnero.njson;

import com.nnero.njson.parse.JSONLexer;
import com.nnero.njson.parse.exception.JSONGrammarException;
import com.nnero.njson.parse.exception.JSONTokenException;
import com.nnero.njson.parse.Token;

import java.util.ArrayList;
import java.util.List;

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
    public static void main(String[] args) throws JSONTokenException, JSONGrammarException {
//        testLexer();
//        testParser();
        testFullParser();
    }

    private static void testFullParser() throws JSONGrammarException, JSONTokenException {
        String json = "[\n" +
                "    {\"StudentID\":  100   ,\"Name\":{\"haha\":222,\"xixi\":false},\"Hometown\":[2,5,0],\"like\":[true,false],\"week\":[\"周六\",\"周五\"]},\n" +
                "    {\"StudentID\": 101,\"Name\":{\"haha\":555,\"xixi\":true},\"Hometown\": [1,2,4],\"like\":[true,true],\"week\":[\"周三\",\"monday\"]}\n" +
                "]";
        JSONArray arr = NJson.getJsonArray(json);
        for(int i=0;i<arr.size();i++){
            JSONObject o = arr.getObj(i);
            System.out.println(o.getInt("StudentID"));
            JSONObject o1 = o.getJsonObj("Name");
            System.out.println(o1.getInt("haha"));
            System.out.println(o1.getBoolean("xixi"));
            JSONArray arr1 = o.getJsonArray("Hometown");
            for(int j=0;j<arr1.size();j++){
                System.out.println(arr1.getInt(j));
            }
            JSONArray arr2 = o.getJsonArray("like");
            for(int j=0;j<arr2.size();j++){
                System.out.println(arr2.getBoolean(j));
            }
            JSONArray arr3 = o.getJsonArray("week");
            for(int j=0;j<arr3.size();j++){
                System.out.println(arr3.getString(j));
            }
        }
    }

    private static void testLexer() throws JSONTokenException {
        String json = "{\"name\":\"nnero\"}";
        String json1 = "[\n" +
                "    {\"StudentID\":  100   ,\"Name\":null,\"Hometown\":\"china\"},\n" +
                "    {\"StudentID\": 101,\"isName\":false,\"isHometown\": true  }\n" +
                "]";
        String json2 = "{\"StudentID\":100,\"Name\":null,\"Hometown\":\"china\",\"boolean\":false,\"nnero\":true}";
        JSONLexer lexer = new JSONLexer(json2);
        Token token = null;
        while ((token = lexer.nextToken()) != null){
            if(token.getType() == Token.Type.EOF){
                System.out.println(token.toString());
                break;
            }
            System.out.println(token.toString());
        }
    }

    private static void testParser() throws JSONTokenException, JSONGrammarException {
        String json = "{\"StudentID\":100," +
                "\"Name\":null," +
                "\"Hometown\":\"china\"," +
                "\"boolean\":false," +
                "\"nnero\":true," +
                "\"like\" : { \"hello\":\"Niin\",\"count\":122,\"haha\":{\"wa\":true,\"maybe\":false}}" +
                "}";
        JSONObject obj = NJson.getJsonObj(json);
        System.out.println(obj.getInt("StudentID"));
//        System.out.println(obj.getInt("Name"));
        System.out.println(obj.getString("Hometown"));
        System.out.println(obj.getBoolean("boolean"));
        System.out.println(obj.getBoolean("nnero"));

        //nested obj
        JSONObject o = obj.getJsonObj("like");
        System.out.println(o.getString("hello"));
        System.out.println(o.getInt("count"));

        JSONObject o1 = o.getJsonObj("haha");
        System.out.println(o1.getBoolean("wa"));
        System.out.println(o1.getBoolean("maybe"));
    }

}
