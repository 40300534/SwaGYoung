//package com.br.technology;
//
//import com.alibaba.fastjson.JSONArray;
////import org.junit.Test;
////import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TechnologyApplicationTests {
//
//    @Test
//    public void contextLoads() {
//        String str = "[{\"name\":\"服务费\",\"feeType\":\"percent\",\"value\":\"4%\",\"feeTypeId\":2},{\"name\":\"平台运费\",\"feeType\":\"fixed\",\"value\":\"￥16\",\"feeTypeId\":1},{\"name\":\"平台使用费\",\"feeType\":\"percent\",\"value\":\"1%\",\"feeTypeId\":2}]";
//        JSONArray temp = JSONArray.parseArray(str);
//        for (int i = 0; i < temp.size(); i++) {
//            JSONArray obj = (JSONArray) temp.get(i);
//            System.out.println(obj.getString(Integer.parseInt("name")));
//        }
//
//    }
//
//}
