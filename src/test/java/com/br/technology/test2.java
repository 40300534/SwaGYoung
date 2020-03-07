package com.br.technology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author TianXu
 * @create 2019/12/27  16:14
 * @Description TODO
 */
public class test2 {
    public static void main(String[] args) throws ParseException {
        System.out.println("请输入出生日期");
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date bornDate = simpleDateFormat.parse(scanner.nextLine());
        long bornDateTime = bornDate.getTime();
        long time = new Date().getTime();
        long result = time - bornDateTime;
        System.out.println(result /1000/60/60/24+":天");
    }
}
