package com.codecool.advanced.tw.fourth.hashmap;

public class Main {

    public static void main(String[] args) throws Exception {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 1000; i++) {
            myHashMap.add(String.valueOf(i), i);
        }
        for (int i = 0; i < 1000; i++) {
            myHashMap.remove(String.valueOf(i));
        }
    }
}
