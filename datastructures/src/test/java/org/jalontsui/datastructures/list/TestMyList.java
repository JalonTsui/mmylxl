package org.jalontsui.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

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

    @Test
    public void testLinkedList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            Assertions.assertEquals(index++, next);
            iterator.remove();
        }
        Assertions.assertTrue(list.isEmpty());
    }
}
