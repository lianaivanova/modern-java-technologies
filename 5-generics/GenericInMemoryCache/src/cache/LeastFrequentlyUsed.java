package cache;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeastFrequentlyUsed<K, V> extends CacheImpl<K, V> {
    public LeastFrequentlyUsed(long capacity) {
        super(capacity);
    }

    private K getLeastUsed() {
        List<Map.Entry<K, Long>> list = new LinkedList<>(getUsage().entrySet());
        list.sort(new Comparator<Map.Entry<K, Long>>() {
            public int compare(Map.Entry<K, Long> o1,
                               Map.Entry<K, Long> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        return list.get(0).getKey();
    }

    public K keyToReplace() {
        return getLeastUsed();
    }

    @Override
    public long getUsesCount(K key) {
        if (getUsage().containsKey(key)) {
            return getUsage().get(key);
        }
        return 0;
    }
}
