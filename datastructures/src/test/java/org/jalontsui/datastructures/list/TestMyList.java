package org.jalontsui.datastructures.list;

import org.jalontsui.datastructures.list.impl.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * @author JalonTsui
 * @Date 0:58 2025/5/28
 **/

public class TestMyList {

    @Test
    public void testList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        int[] ints = {10, 20, 30, 40, 50};
        for (int i : ints) {
            list.add(i);
        }
        int index = 0;
        // 测试增强for循环
        for (Integer item : list) {
            Assertions.assertEquals(item, ints[index++]);
        }
    }
}
