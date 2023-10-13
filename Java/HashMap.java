import java.util.LinkedList;

class KeyValuePair<K, V> {
    K key;
    V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable<K, V> {
    private int capacity;
    private LinkedList<KeyValuePair<K, V>>[] buckets;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % capacity);
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<KeyValuePair<K, V>> bucket = buckets[index];

        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }

        bucket.add(new KeyValuePair<>(key, value));
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<KeyValuePair<K, V>> bucket = buckets[index];

        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }

        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<KeyValuePair<K, V>> bucket = buckets[index];

        KeyValuePair<K, V> toRemove = null;

        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                toRemove = pair;
                break;
            }
        }

        if (toRemove != null) {
            bucket.remove(toRemove);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>(10);

        table.put("John Doe", 28);
        table.put("Jane Doe", 32);
        table.put("Bob Smith", 45);

        System.out.println("John Doe's age is " + table.get("John Doe")); // Output: John Doe's age is 28

        table.put("John Doe", 29);

        System.out.println("John Doe's age is " + table.get("John Doe")); // Output: John Doe's age is 29

        table.remove("Jane Doe");

        System.out.println("Jane Doe's age is " + table.get("Jane Doe")); // Output: Jane Doe's age is null
    }
}
