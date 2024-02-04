import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class GQTest {
    private GenericQueue<Integer> testList = new GenericQueue<Integer>(0, 1);

    @BeforeEach
    void setup() {
        for (int i = 1; i <= 10; i++) {
            testList.enqueue(i, i + 1);
        }
    }

    @Test
    //tests types of constructor
    void testConstructor() {
        // declare
        GenericQueue<Integer> testInt = new GenericQueue<Integer>(10);
        GenericQueue<Double> testDouble = new GenericQueue<Double>(10.5);
        GenericQueue<Character> testChar = new GenericQueue<Character>('c');
        GenericQueue<String> testStr = new GenericQueue<String>("abc");

        // heads
        assertEquals(10, testInt.getHead().data, "failed constructor"); 
        assertEquals(10.5, testDouble.getHead().data, "failed constructor");
        assertEquals('c', testChar.getHead().data, "failed constructor");
        assertEquals("abc", testStr.getHead().data, "failed constructor");

        // tails
        assertEquals(10, testInt.getTail().data, "failed constructor"); 
        assertEquals(10.5, testDouble.getTail().data, "failed constructor");
        assertEquals('c', testChar.getTail().data, "failed constructor");
        assertEquals("abc", testStr.getTail().data, "failed constructor");

        // length
        assertEquals(1, testInt.getLength(), "failed constructor");
    }

    @Test
    //test head, tail, length, data
    void testEnqueue() {
        GenericQueue<Integer> list = new GenericQueue<Integer>(0);
        for (int i = 1; i <= 10; i++) {
            list.enqueue(i);
            assertEquals(0, list.getHead().data, "failed enqueue: head");
            assertEquals(i, list.getTail().data, "failed enqueue: tail");
            assertEquals(i + 1, list.getLength(), "failed enqueue: length");
        }

        GenericList<Integer>.Node<Integer> currNode = list.getHead();

        int expected = 0;
        while (currNode != null) {
            assertEquals(expected, currNode.data, "failed enqueue");
            currNode = currNode.next;
            expected++;
        }
    }

    @Test
    void testCode() {
        GenericQueue<Integer> list = new GenericQueue<Integer>(0, 55);

        // constructors
        assertEquals(55, list.getHead().code, "failed code: constructor");
        assertEquals(55, list.getTail().code, "failed code: constructor");

        assertEquals(0, list.getHead().data, "failed data: constructor");
        assertEquals(0, list.getTail().data, "failed data: constructor");

        assertEquals(1, list.getLength(), "failed code: add");
        
        // add
        list.enqueue(1, 50);
        assertEquals(55, list.getHead().code, "failed code: add");
        assertEquals(50, list.getHead().next.code, "failed code: add");
        assertEquals(50, list.getTail().code, "failed code: add");

        assertEquals(0, list.getHead().data, "failed data: add");
        assertEquals(1, list.getHead().next.data, "failed data: add");
        assertEquals(1, list.getTail().data, "failed data: add");

        assertEquals(2, list.getLength(), "failed code: add");
    }

    @Test
    void testDequeue() {
        assertEquals(10, testList.getTail().data, "failed equals");

        //delete everything
        for (int i = 10; i >= 0; i--) {
            assertEquals(i, testList.getTail().data, "failed dequeue");
            assertEquals(i + 1, testList.getLength(), "failed dequeue: length");
            testList.dequeue();
        }
        assertEquals(null, testList.getTail(), "failed dequeue");
        assertEquals(null, testList.getHead(), "failed dequeue");
        assertEquals(0, testList.getLength(), "failed dequeue");
    }

    @Test
    void testIterator() {
        Iterator<Integer> it = testList.iterator();
        for (int i = 0; i <= 10; i++) {
            assertEquals(true, it.hasNext(), "failed iterator");
            assertEquals(i, it.next(), "failed iterator");
        }
        assertEquals(false, it.hasNext(), "failed iterator");
    }

    @Test
    void testIterator2() {
        int counter = 0;
        for (Integer i : testList) {
            assertEquals(counter, i, "failed forEach");
            counter++;
        }
    }    

    @Test
    void testReverseIterator() {
        Iterator<Integer> it = testList.descendingIterator();

        for (int i = 10; i >= 0; i--) {
            assertEquals(true, it.hasNext(), "failed reverse iterator"); 
            assertEquals(i, it.next(), "failed reverse iterator");
        }
        assertEquals(false, it.hasNext(), "failed reverse iterator");
    }

    @Test
    void testReverseIterator2() {
        Iterator<Integer> it = testList.descendingIterator();
        int revCounter = 10;
        while (it.hasNext()) {
            assertEquals(revCounter, it.next(), "failed reverse forEach");
            revCounter--;
        }
    }

    @Test
    void testPrint() {
        // setup to convert output to string
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        testList.print();

        // replace is system dependent
        String actual = outputStream.toString().replace("\r\n", "\n");
        String expected = "0 1 2 3 4 5 6 7 8 9 10 \n";

        assertEquals(expected, actual, "failed print output");

    }

    @Test
    void testDumpList() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i <= 10; i++) {
            values.add(i);
        }

        testList.dumpList();
        assertEquals(values, testList.dumpList(), "ArrayLists not equal");
    }

    @Test
    void testSetGetLength() {
        // test length
        assertEquals(11, testList.getLength(), "wrong length");
        testList.setLength(20);
        assertEquals(20, testList.getLength(), "wrong length");
        testList.setLength(12);
        assertEquals(12, testList.getLength(), "wrong length");
    }

    @Test
    void testSetGetHead() {
        GenericList<Integer>.Node<Integer> headNode = testList.getHead();
        GenericList<Integer>.Node<Integer> tailNode = testList.getTail();

        // test head
        testList.setHead(null);
        assertEquals(null, testList.getHead(), "wrong head");
        testList.setHead(tailNode);
        assertEquals(tailNode, testList.getHead(), "wrong head");
        testList.setHead(headNode);
        assertEquals(headNode, testList.getHead(), "wrong head");
    }

    @Test
    void testSetGetTail() {
        GenericList<Integer>.Node<Integer> headNode = testList.getHead();
        GenericList<Integer>.Node<Integer> tailNode = testList.getTail();

        // test tail
        testList.setTail(null);
        assertEquals(null, testList.getTail(), "wrong tail");
        testList.setTail(headNode);
        assertEquals(headNode, testList.getTail(), "wrong tail");
        testList.setTail(tailNode);
        assertEquals(tailNode, testList.getTail(), "wrong tail");
    }

    @Test
    void testGet() {
        // test get
        for (int i = 0; i < testList.getLength(); i++) {
            assertEquals(i, testList.get(i), "get failed");
        }

        // out of bounds
        assertEquals(null, testList.get(-17), "get failed");
        assertEquals(null, testList.get(215), "get failed");
    }

    @Test
    void testSet() {
        // test set
        for (int i = 0; i < testList.getLength(); i++) {
            assertEquals(i, testList.set(i, i + 5), "set failed");
            assertEquals(i + 5, testList.get(i), "get failed");
        }
    }

    @Test
    void testContains() {
        for (int i = 0; i < testList.getLength(); i++) {
            assertEquals(i, testList.contains(i + 1), "contains failed");
        }

        // doesn't exist
        assertEquals(-1, testList.contains(318), "contains failed");
        assertEquals(-1, testList.contains(-20), "contains failed");
    }
}
