package org.jalontsui.datastructures.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;

/*
 * @author JalonTsui
 * @Date 23:08 2025/5/28
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
        return null;
    }

    @Override
    public T set(int index, T newVal) {
        return null;
    }

    @Override
    public void add(int index, T item) {

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
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
