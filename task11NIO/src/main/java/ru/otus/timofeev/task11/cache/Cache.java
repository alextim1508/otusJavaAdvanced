package ru.otus.timofeev.task11.cache;


import java.nio.Buffer;


public interface Cache<K, V extends Buffer> {

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    V readFile(String path) throws Exception;

    String getContent(V content) throws Exception;
}