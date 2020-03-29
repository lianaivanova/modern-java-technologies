package cache;

import java.util.Map;
import java.util.Random;

public class RandomReplacement<K, V> extends CacheImpl<K, V> {

    public RandomReplacement(long capacity) {
        super(capacity);
    }

    public K keyToReplace() {
        Random dice = new Random();
        int position = dice.nextInt((int) getSize());
        K keyToReplace = null;
        for (Map.Entry<K, V> entry : getCache().entrySet()) {
            if (position == 0) {
                keyToReplace = entry.getKey();
                break;
            }
            position--;
        }
        return keyToReplace;
    }

    @Override
    public long getUsesCount(K key) {
        throw new UnsupportedOperationException();
    }

}
