package org.jalontsui.datastructures.list;

/*
 * @author JalonTsui
 * @Date 0:36 2025/5/27
 **/

public interface MyList<T> extends Iterable<T> {

    /**
     * 清空list
     */
    void clear();

    /**
     * 获取list当前元素个数
     * @return 当前元素个数
     */
    int size();

    /**
     * 判断list是否为空
     * @return list是否为空
     */
    boolean isEmpty();

    /**
     * 获取list索引对应值
     * @param index 索引
     * @return 索引对应值
     */
    T get(int index);

    /**
     * 给list指定索引赋值
     * @param index 索引
     * @param newVal 插入的元素
     * @return 若该索引已经有值则返回久的值
     */
    T set(int index, T newVal);

    /**
     * 指定位置插入元素
     * @param index 索引
     * @param item 插入的值
     */
    void add(int index, T item);

    /**
     * 移除指定索引值
     * @param index
     * @return
     */
    T remove(int index);


}
