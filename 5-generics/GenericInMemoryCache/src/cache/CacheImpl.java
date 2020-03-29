package cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CacheImpl<K, V> implements Cache<K, V> {
    private Map<K, V> cache;
    private Map<K, Long> usage;
    private long capacity;
    private long size;
    private int successfulHits;
    private int allHits;
    private static final long ONE_USAGE = 1;


    public CacheImpl(long capacity) {
        this.cache = new HashMap<>();
        this.usage = new LinkedHashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.successfulHits = 0;
        this.allHits = 0;
    }

    public long getSize() {
        return this.size;
    }


    public Map<K, Long> getUsage() {
        return this.usage;
    }

    public Map<K, V> getCache() {
        return this.cache;
    }

    @Override
    public void set(K key, V value) {
        if (key != null && value != null) {
            if (this.cache.containsKey(key)) {
                this.cache.replace(key, value);
                this.usage.replace(key, this.usage.get(key) + ONE_USAGE);
            } else {
                if (isFull()) {
                    K toReplace = keyToReplace();
                    remove(toReplace);
                    this.usage.remove(toReplace);
                }
                this.cache.put(key, value);
                this.size++;
                this.usage.put(key, ONE_USAGE);
            }
        }
    }

    @Override
    public boolean remove(K key) {
        if (this.cache.containsKey(key)) {
            this.cache.remove(key);
            this.usage.remove(key);
            this.size--;
            return true;
        }
        return false;
    }


    private boolean isFull() {
        return this.size == this.capacity;
    }

    @Override
    public V get(K key) {
        if (cache.containsKey(key)) {
            this.successfulHits++;
            this.allHits++;
            this.usage.replace(key, this.usage.get(key) + ONE_USAGE);
            return cache.get(key);
        }
        this.allHits++;
        return null;
    }

    @Override
    public long size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.cache = new HashMap<>();
        this.usage = new LinkedHashMap<>();
        this.size = 0;
        this.allHits = 0;
        this.successfulHits = 0;
    }

    @Override
    public double getHitRate() {
        if (allHits == 0) {
            return 0;
        }
        return ((double) this.successfulHits) / this.allHits;
    }

    public abstract K keyToReplace();
}
