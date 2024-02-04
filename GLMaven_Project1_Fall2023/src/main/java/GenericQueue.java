
public class GenericQueue<T> extends GenericList<T> {
    private Node<T> tail;

    // constructor
    public GenericQueue(T data) {
        Node<T> newnode = new Node<T>(data);
        this.setHead(newnode);
        this.tail = newnode;
        this.setLength(1);
    }

    // constructor
    public GenericQueue(T data, int code) {
        Node<T> newnode = new Node<T>(data, code);
        this.setHead(newnode);
        this.tail = newnode;
        this.setLength(1);
    }

    // adds to back of list 
    public void add(T data) {
        Node<T> newnode = new Node<T>(data);
        if (this.getHead() == null) { //if empty
            this.setHead(newnode);
            this.tail = newnode;
        }
        else {
            this.tail.next = newnode;
            this.tail = newnode;
        }
        this.setLength(getLength() + 1);
    }

    // adds to back of list
    public void add(T data, int code) {
        Node<T> newnode = new Node<T>(data, code);
        if (this.getHead() == null) { //if empty
            this.setHead(newnode);
            this.tail = newnode;
        }
        else {
            this.tail.next = newnode;
            this.tail = newnode;
        }
        this.setLength(getLength() + 1);
    }

    // deletes last node and returns its value
    public T delete() { 
        if (this.getHead() == null) { // empty
            return null;
        }

        T value = this.tail.data;

        if (this.getHead() == this.tail) { // only one node
            this.setHead(null);
            this.tail = null;
        } 
        else { // multiple nodes
            Node<T> currNode = this.getHead();
            while (currNode.next != this.tail) {
                currNode = currNode.next;
            }
            this.tail = currNode;
            currNode.next = null;
        }

        this.setLength(getLength() - 1);
        return value;
    }

    //calls add function
    public void enqueue(T data) {
        this.add(data);
    }

    //calls add function
    public void enqueue(T data, int code) {
        this.add(data, code);
    }

    //calls delete function
    public T dequeue() {
        return delete();
    }

    //setter
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    //getter
    public Node<T> getTail() {
        return tail;
    }
    
}
