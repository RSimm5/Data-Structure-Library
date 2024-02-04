import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> {
    private ArrayList<GenericQueue<T>> map;

    /* constructor
    initialize the ArrayList map to 10 and add the 
    first key/value pair into the ArrayList map. */
    MyHashMap(String key, T value) {
        map = new ArrayList<GenericQueue<T>>(10);
        for (int i = 0; i < 10; i++) {
            map.add(null);
        }
        put(key, value);
    }

    // creates hash index for map
    private int hash(String key) {
        int hash = Math.abs(key.hashCode());
        return hash % map.size();
    }

    // puts new key/value pair in hashmap
    public void put(String key, T value) {
        int index = hash(key);

        // do not allow 2 of same key/code
        if (this.contains(key)) return; 
        if (map.get(index) == null) { 
            // if no collisions
            map.set(index, new GenericQueue<T>(value, key.hashCode()));
        } else { 
            // if collisions
            map.get(index).enqueue(value, key.hashCode());
        }
    }

    // checks if given key exists
    public boolean contains(String key) {
        int index = hash(key);
        if (map.get(index) == null) return false;
        return map.get(index).contains(key.hashCode()) != -1;
    }

    // returns value at given key or null if it doesn't exist
    public T get(String key) {
        if (!contains(key)) return null;

        int index = hash(key);
        int qindex = map.get(index).contains(key.hashCode());

        return map.get(index).get(qindex);
    }

    // returns number of key-value pairs
    public int size() {
        int count = 0;
        for (GenericQueue<T> queue : map) {
            if (queue != null) {
                count+= queue.getLength();
            }
        }
        return count;
    }

    // returns true if no key-value mappings
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // replaces entry at key if it's mapped to a value
    // returns original value
    public T replace(String key, T value) {
        int index = hash(key);
        GenericQueue<T> queue = map.get(index);

        if (queue != null) {
            // find index of queue, if it exists
            int qindex = queue.contains(key.hashCode());

            if (qindex != -1) { 
                // key exists in queue
                T oldValue = queue.get(qindex);
                queue.set(qindex, value);
                return oldValue;
            }
        }
        return null;
    }
    
    // makes new iterator instance
    public Iterator<T> iterator() {
        return new HMIterator<T>(map);
    }
}
