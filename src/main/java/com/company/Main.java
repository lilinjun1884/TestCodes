package com.company;

public class Main {

    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = "hello " + "world";//new String("world");  "world"
        System.out.println(str1 == str2);


        String a = "a2";

        String a2 = "a"+2;

        //在编译期值是确定的就是a2。只有编译期变量a与变量a2值相等他们才相等
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
