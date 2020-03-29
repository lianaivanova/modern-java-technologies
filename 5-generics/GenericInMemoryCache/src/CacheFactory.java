import cache.LeastFrequentlyUsed;
import cache.RandomReplacement;
import enums.EvictionPolicy;
import cache.Cache;

public interface CacheFactory {
    long MAX_CAPACITY = 10000;

    static <K, V> Cache<K, V> getInstance(long capacity, EvictionPolicy policy) throws IllegalArgumentException {
        if (capacity <= 0 || policy == null) {
            throw new IllegalArgumentException();
        }
        Cache<K, V> cache = null;
        if (policy == EvictionPolicy.LEAST_FREQUENTLY_USED) {
            cache = new LeastFrequentlyUsed<>(capacity);
        } else if (policy == EvictionPolicy.RANDOM_REPLACEMENT) {
            cache = new RandomReplacement<>(capacity);
        }
        return cache;
    }

    static <K, V> Cache<K, V> getInstance(EvictionPolicy policy) {
        return getInstance(MAX_CAPACITY, policy);
    }

    ;
}
