package com.company;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.TIntObjectHashMap;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/25.
 */
public class TroveCollectionTool {
    @Test
    public void useIntIntList() {
        TIntArrayList ints = new TIntArrayList(3);
        ints.addAll(new int[]{1, 23, 33, 43});
        System.out.println(ints);
    }

    @Test
    public void useIntObjectMap() {
        TIntObjectHashMap intObjects = new TIntObjectHashMap();
        intObjects.put(1, new Object());
        intObjects.put(2, new Object());
        intObjects.put(3, new Object());
        intObjects.put(4, new Object());
        System.out.println(intObjects);
    }

    @Test
    public void uset() {
        //警惕：不要使用Integer和Integer进行值比较
        int value = 45;
        Integer i = Integer.valueOf(value);
        System.out.println(i == Integer.valueOf(value));
    }

}
