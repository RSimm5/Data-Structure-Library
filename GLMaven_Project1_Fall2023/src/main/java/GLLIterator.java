import java.util.Iterator;

public class GLLIterator<E> implements Iterator<E> {

    private GenericList<E>.Node<E> currNode;

    // constructor
    public GLLIterator(GenericList<E>.Node<E> head) {
        currNode = head;
    }

    public boolean hasNext() {
        return currNode != null;
    }

    public E next() {
        if (!hasNext()) return null;

        E data = currNode.data;
        currNode = currNode.next;

        return data;
    }
}
