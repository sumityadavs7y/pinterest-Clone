package com.example.idrive.Util;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InMemoryCache<K, T> {

    private final HashMap<K,CacheObject> cacheMap;
    private final int maxSize;

    protected class CacheObject {

        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CacheObject(T value) {
            this.value = value;
        }
    }

    public InMemoryCache(int maxItems) {
        maxSize=maxItems;
        cacheMap = new HashMap<>();
    }

    public void put(K key, T value) {
        synchronized (cacheMap) {
            if(cacheMap.size()>=maxSize && !cacheMap.containsKey(key)) {
                Log.i("DEBUG","deleted why?");
                deleteLateaccessed();
            }
            cacheMap.put(key, new CacheObject(value));
        }
    }

    public void deleteLateaccessed(){
        long firstAccessed = Long.MAX_VALUE;
        K keyToBeDeleted = null;
        Iterator<Map.Entry<K, CacheObject>> it = cacheMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, CacheObject> set = (Map.Entry<K, CacheObject>) it.next();
            if(firstAccessed > set.getValue().lastAccessed){
                firstAccessed = set.getValue().lastAccessed;
                keyToBeDeleted = set.getKey();
            }
        }
        if(keyToBeDeleted!=null){
            cacheMap.remove(keyToBeDeleted);
        }
    }

    public T get(K key) {
        synchronized (cacheMap) {
            CacheObject c;
            c = (CacheObject) cacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    public void clean(){
        synchronized (cacheMap){
            cacheMap.clear();
        }
    }
}
