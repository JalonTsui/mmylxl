package org.jalontsui.datastructures.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * @author JalonTsui
 * @Date 23:08 2025/5/28
 *
 * 关键在于addBefore和getNode的实现与运用
 **/

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> beginMarker;
    private Node<T> endMarker;
    private int innerSize;

    // 链表创建后，对该链表所做的修改次数
    private int modCount;

    public MyLinkedList() {
        doClear();
    }

    @Override
    public void clear() {
        doClear();
    }

    /**
     * 初始化和清空链表
     */
    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.setNext(endMarker);
        innerSize = 0;
        modCount++;
    }

    @Override
    public int size() {
        return innerSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T get(int index) {
        return getNode(index).getData();
    }

    @Override
    public T set(int index, T newVal) {
        Node<T> node = getNode(index);
        T oldVal = node.getData();
        node.setData(newVal);
        return oldVal;
    }

    public boolean add(T item) {
        add(size(), item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        addBefore(getNode(index, 0, size()), item);
    }

    /**
     * 往指定的targetNode前面添加新的节点
     *
     * @param targetNode 目标节点
     * @param val        新节点的值
     */
    private void addBefore(Node<T> targetNode, T val) {
        Node<T> prevNode = targetNode.getPrev();
        Node<T> newNode = new Node<>(val, prevNode, null);
        prevNode.setNext(newNode);
        innerSize++;
        modCount++;
    }

    /**
     * 获取指定索引节点，上界默认是链表的长度
     *
     * @param index
     * @return
     */
    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    /**
     * 获取指定索引的节点
     *
     * @param index 索引
     * @param lower 指定的下界
     * @param upper 指定的上界
     * @return 查询到的节点
     */
    private Node<T> getNode(int index, int lower, int upper) {
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> p;
        if (index < size() / 2) {
            p = beginMarker;
            for (int i = 0; i < index; i++) {
                p = p.getNext();
            }
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.getPrev();
            }
        }
        return p;
    }

    @Override
    public T remove(int index) {
        return remove(getNode(index));
    }

    private T remove(Node<T> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        innerSize--;
        modCount++;
        return node.getData();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    /**
     * iterator的基本原则
     * 1. 在迭代的循环过程中要保证list不会被处iterator的remove方法以外的方法修改
     */
    private class MyLinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.getNext();
        private int expectedModCount = modCount; // 保证迭代器在迭代过程中，list没有被iterator中remove方法以外的方法改变
        private boolean okToRemove = false; // 防止通过next方法拿到对象后二次调用remove

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.getData();
            current = current.getNext();
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            /**
             * 1. 调用next后，current已经变成了要remove元素的下一个节点
             * 2. MyLinkedList的remove方法会改变modCount，所以expectedModCount也要自增
             * 3. iterator的remove方法调用后okToRemove要置为false，保证remove方法只能执行一次
             */
            MyLinkedList.this.remove(current.getPrev());
            expectedModCount++;
            okToRemove = false;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;
    }
}
