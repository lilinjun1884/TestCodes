package com.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Administrator on 2016/1/23.
 */
@Data
@ToString(callSuper=true,exclude="field2")
@EqualsAndHashCode(exclude={"field2"})
public class Lombok {
    private String field1="test1";
    private String field2;

    //测试 Lombok的使用
    //lombok 提供了简单的注解的形式来帮助我们简化消除一些必须有但显得很臃肿的 java 代码
    public static void main(String[] args) {
        System.out.println(new Lombok().getField1());
        System.out.println(new Lombok());
    }
}
