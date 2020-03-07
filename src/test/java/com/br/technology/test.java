//package com.br.technology;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.junit.Test;
//
//import java.util.Scanner;
//
///**
// * @author TianXu
// * @create 2019/7/30  15:17
// * @Description TODO
// */
//public class test {
//    public static void main(String[] args) {
//        String str = "[{\"name\":\"服务费\",\"feeType\":\"percent\",\"value\":\"4%\",\"feeTypeId\":2},{\"name\":\"平台运费\",\"feeType\":\"fixed\",\"value\":\"￥16\",\"feeTypeId\":1},{\"name\":\"平台使用费\",\"feeType\":\"percent\",\"value\":\"1%\",\"feeTypeId\":2}]";
//        JSONArray temp = JSONArray.parseArray(str);
//        for (int i = 0; i < temp.size(); i++) {
//            JSONObject obj = (JSONObject) temp.get(i);
//            System.out.println(obj.getString("name"));
//        }
//    }
//
//    @Test
//    public void tse() {
//        System.out.println("请输入出生日期");
//        Scanner sc = new Scanner(System.in);
//        String next = sc.nextLine();
//        System.out.println(next);
//    }
//
//}
