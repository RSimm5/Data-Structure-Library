import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HMTest {
    private MyHashMap<Integer> testMap = new MyHashMap<Integer>("0", 5);

    @BeforeEach
    void setup() {
        for (int i = 1; i <= 9; i++) {
            String key = Integer.toString(i);
            testMap.put(key, i + 5);
        }
    }

    @Test
    void testConstructor() {
        MyHashMap<Integer> iMap = new MyHashMap<Integer>("0", 5);
        MyHashMap<Double> dMap = new MyHashMap<Double>("0", 5.5);
        MyHashMap<Character> cMap = new MyHashMap<Character>("0", 'c');
        MyHashMap<String> sMap = new MyHashMap<String>("0", "abc");

        assertEquals(5, iMap.get("0"), "failed HMap Constructor");
        assertEquals(5.5, dMap.get("0"), "failed HMap Constructor");
        assertEquals('c', cMap.get("0"), "failed HMap Constructor");
        assertEquals("abc", sMap.get("0"), "failed HMap Constructor");

        // size
        assertEquals(1, iMap.size(), "failed HMap Constructor: size");
    }

    @Test
    // tests put, size, get
    void testPut() {
        MyHashMap<Integer> myMap = new MyHashMap<Integer>("0", 5);
        assertEquals(1, myMap.size(), "failed HMap constructor: size");

        for (int i = 1; i <= 9; i++) {
            String key = Integer.toString(i);
            myMap.put(key, i + 4);

            assertEquals(i + 1, myMap.size(), "failed put");
            assertEquals(i + 4, myMap.get(key), "failed put");
        }
    }

    @Test
    void testContains() {
        for (int i = 0; i <= 9; i++) {
            String key = Integer.toString(i);
            testMap.contains(key);
            assertEquals(true, testMap.contains(key), "failed HMap contains");
        }
        assertEquals(false, testMap.contains("key"), "failed HMap contains");
    }

    @Test
    void testGet() {
        for (int i = 0; i <= 9; i++) {
            String key = Integer.toString(i);
            assertEquals(i + 5, testMap.get(key), "failed put");
        }
    }


    @Test
    void testSize() {
        MyHashMap<Integer> myMap = new MyHashMap<Integer>("0", 5);
        assertEquals(1, myMap.size(), "failed HMap constructor: size");

        for (int i = 1; i <= 20; i++) {
            String key = Integer.toString(i);
            myMap.put(key, i + 4);

            assertEquals(i + 1, myMap.size(), "failed size");
        }
    }

    @Test
    void testEmpty() {
        assertEquals(false, testMap.isEmpty(), "failed isEmpty");
    }

    @Test
    void testReplace() {
        for (int i = 0; i <= 9; i++) {
            String key = Integer.toString(i);
            assertEquals(i + 5, testMap.replace(key, i + 2), "failed to return old value");
            assertEquals(i + 2, testMap.get(key), "failed replace: get");
        }
        assertEquals(null, testMap.replace("key", 6), "failed replace");
    }

    @Test
    void testIterator() {
        int sum = 0;
        int count = 1;

        for (Integer integer : testMap) {
            sum += integer;
            count++;
        }
        
        assertEquals(88, sum, "failed iterator");
        assertEquals(testMap.size(), count, "failed iterator");
    }
}

