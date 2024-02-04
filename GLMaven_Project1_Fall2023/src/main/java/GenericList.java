import java.util.ArrayList;
import java.util.Iterator;

public abstract class GenericList<T> implements Iterable<T> {

    private Node<T> head;
    private int length;

    // constructor
    public GenericList() {
        head = null;
        length = 0;
    }

    // node class
    public class Node<T> {
        public T data;
        public int code;
        public Node<T> next;

        Node(T data) { 
            this.data = data;
            next = null;
        }
        Node(T data, int code) { 
            this.data = data;
            this.code = code;
            next = null;
        }
    }

    // makes new iterator instance
    public Iterator<T> iterator() {
        return new GLLIterator<T>(head);
    }

    // makes new reverse iterator instance
    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator<T>(head);
    }

    // prints items in list
    public void print() {
        Node<T> currNode = this.head;
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    // adds value to list
    abstract void add(T data);

    // deletes node in list
    abstract T delete();

    // stores and returns all values in list to ArrayList
    public ArrayList<T> dumpList() {
        ArrayList<T> values = new ArrayList<T>();

        for (T value : this) {
            values.add(value);
        }

        return values;
    } 

    // returns value at index or null if out of bounds
    public T get(int index) {
        if (index > length || index < 0) return null;

        Node<T> currNode = this.head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        return currNode.data;
    }

    // replaces element at index, returns original element or null if out of bounds
    public T set (int index, T data) {
        if (index > length) return null;

        Node<T> currNode = this.head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        T original = currNode.data;
        currNode.data = data;

        return original;
    }

    // finds if list contains code, returns index
    // if it doesn't, return -1
    public int contains(int code) {
        int index = 0;
        Node<T> currNode = this.head;
        while (currNode != null) {
            if (currNode.code == code) return index;
            currNode = currNode.next;
            index++;
        }
        return -1;
    }

    //getters and setters
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

}