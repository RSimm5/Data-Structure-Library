import java.util.ArrayList;
import java.util.Iterator;

public class HMIterator<V> implements Iterator<V> {
    private int mapIndex = 0;
    private ArrayList<GenericQueue<V>> map;
    private Iterator<V> queueIterator = null;

    // constructor
    public HMIterator(ArrayList<GenericQueue<V>> map) {
        this.map = map;
    }
    
    public boolean hasNext() {
        if (mapIndex >= map.size()) return false;

        // if queue has more elements
        if (queueIterator != null && queueIterator.hasNext()) {
            return true;
        }

        // move to next map index
        mapIndex++;

        // move to next non-null queue
        while (mapIndex < map.size() && map.get(mapIndex) == null) {
            mapIndex++;
        }

        if (mapIndex < map.size()) {
            queueIterator = map.get(mapIndex).iterator();
            return true;
        }
        
        return false;
    }

    public V next() {
        if (!hasNext()) return null;

        // consider queue
        if (queueIterator != null && queueIterator.hasNext()) {
            return queueIterator.next();
        }
        
        return null;
    }
}
