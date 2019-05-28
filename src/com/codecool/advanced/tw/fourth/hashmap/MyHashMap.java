package com.codecool.advanced.tw.fourth.hashmap;

import java.util.LinkedList;
import java.util.List;

public class MyHashMap<T, U> {

    private class KeyValue<T, U> {
        private T key;
        private U value;

        public KeyValue(T key, U value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public U getValue() {
            return value;
        }

        public void setValue(U value) {
            this.value = value;
        }
    }

    private int size = 16;
    private List<KeyValue>[] elements = new LinkedList[size];
    private int elementCount = 0;

    public void add(T key, U value) throws Exception{
        resizeIfNeeded();
        KeyValue keyValue = new KeyValue<>(key, value);
        int index = key.hashCode() % size;

        if (elements[index] == null) {
            elements[index] = new LinkedList<>();
        }

        if(elements[index].contains(keyValue)) throw new Exception("Key already in map.");

        elements[index].add(keyValue);
        elementCount++;
    }

    private void resizeIfNeeded() {
        if (elementCount >= size) {
            size*=2;
        } else if (elementCount < size / 2 && elementCount>16) {
            size/=2;
        }

        List<KeyValue>[] newElements = new LinkedList[size];

        for (List list : elements) {
            if (list != null) {
                for (Object keyValue : list) {
                    if (keyValue instanceof KeyValue) {
                        int newIndex = ((KeyValue) keyValue).getKey().hashCode() % size;
                        if(newElements[newIndex] == null) newElements[newIndex] = new LinkedList<>();
                        newElements[newIndex].add((KeyValue) keyValue);
                    }
                }
            }
        }

        elements = newElements;
    }

    public U get(T key) {
        int index = key.hashCode() % size;
        if(elements[index] == null) return null;

        for (KeyValue iterator : elements[index]) {
            if (iterator.getKey().equals(key)) {
                return (U) iterator.getValue();
            }
        }
        return null;
    }

    public void remove(T key) {
        int index = key.hashCode() % size;
        if(elements[index] == null) return;

        elements[index].removeIf(keyValue -> keyValue.getKey().equals(key));
        elementCount--;
//        KeyValue elementToDelete = null;
//        for (KeyValue iterator : elements[index]) {
//            if (iterator.getKey().equals(key)) {
//                elementToDelete = iterator;
//                break;
//            }
//        }
//        if (elementToDelete != null) {
//            elements[index].remove(elementToDelete);
//            elementCount--;
//        }
    }

    public void clearAll() {
        size = 16;
        elements = new LinkedList[size];
    }

}
