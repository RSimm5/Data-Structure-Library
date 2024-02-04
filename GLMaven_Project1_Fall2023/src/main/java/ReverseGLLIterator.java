import java.util.Iterator;

public class ReverseGLLIterator<E> implements Iterator<E> {
    private GenericList<E>.Node<E> head;
    private GenericList<E>.Node<E> currNode;

    // constructor
    public ReverseGLLIterator(GenericList<E>.Node<E> head) {
        this.head = head;
        this.currNode = head;

        // set currNode to tail
        while (currNode != null && currNode.next != null) {
            currNode = currNode.next;
        }
    }

    public boolean hasNext() {
        return currNode != null;
    }

    public E next() {
        if (!hasNext()) return null;

        E data = currNode.data;

        if (currNode == head) { // one element left
            currNode = null;
            return data;
        }

        // set currNode to previous node
        GenericList<E>.Node<E> prevNode = head;
        while (prevNode.next != currNode) {
            prevNode = prevNode.next;
        }
        currNode = prevNode;

        return data;
    }
}
