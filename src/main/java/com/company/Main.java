package com.company;

public class Main {

    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = "hello " + "world";//new String("world");  "world"
        System.out.println(str1 == str2);


        String a = "a2";

        String a2 = "a"+2;

        //�ڱ�����ֵ��ȷ���ľ���a2��ֻ�б����ڱ���a�����a2ֵ������ǲ����
        System.out.println(a==a2);
    }

    /*public static void main(String[] args) {
        String str1 = "ab";
        func1(str1);
        System.out.println(str1);
    }*/

    public static void func1(String s) {
        s += "cd";
    }
}
