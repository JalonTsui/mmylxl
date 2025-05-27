package org.jalontsui.datastructures.list.impl;

import org.jalontsui.datastructures.list.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * @author JalonTsui
 * @Date 0:38 2025/5/27
 **/

public class MyArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int innerSize;
    private T[] innerArr;

    public MyArrayList() {
        doClear();
    }

    private void doClear() {
        innerSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 扩容方法
     *
     * @param newCapacity
     */
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < innerSize) {
            return;
        }
        T[] old = innerArr;
        innerArr = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            innerArr[i] = old[i];
        }
    }

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public int size() {
        return innerSize;
    }

    @Override
    public boolean isEmpty() {
        return innerSize == 0;
    }

    /**
     * 如果确定了不需要再修改list，可以调用此方法清除多余的内存空间占用
     */
    public void trimToSize() {
        ensureCapacity(size());
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return innerArr[index];
    }

    @Override
    public T set(int index, T newVal) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldVal = innerArr[index];
        innerArr[index] = newVal;
        return oldVal;
    }

    /**
     * 往末尾插入元素
     *
     * @param item
     * @return
     */
    public boolean add(T item) {
        add(size(), item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        // 1. 判断当前数组容量是否需要扩容
        if (innerArr.length == size()) {
            ensureCapacity(2 * size() + 1);
        }
        // 2. 移动index后面的元素
        for (int i = innerSize; i > index; i--) {
            innerArr[innerSize] = innerArr[innerSize - 1];
        }
        // 3. 插入元素
        innerArr[index] = item;
        innerSize++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removeVal = innerArr[index];
        for (int i = index; i < innerSize - 1; i++) {
            innerArr[i] = innerArr[i + 1];
        }
        innerSize--;
        return removeVal;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * 内部类实现构造器，方便访问类的属性和方法，且迭代器本就应该能访问调用时对象的状态，所以用内部类实现很合适
     */
    private class MyArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return innerArr[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
