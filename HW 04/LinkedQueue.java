package queue;

public class LinkedQueue extends AbstractQueue{
    private Node head;
    private Node tail;

    public void enqueueImpl(Object element) {
        Node previous = tail;
        tail = new Node(element, null);
        if(size == 0) {
            head = tail;
        } else {
            previous.next = tail;
        }
        size++;
    }

    public Object elementImpl() {
        assert size != 0;
        return head.value;
    }

    public Object dequeueImpl() {
        assert size != 0;
        size--;
        Object result = head.value;
        head = head.next;
        return result;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Object[] toArray(int array_size) {
        Object[] newElements = new Object[array_size];
        Node current = head;
        for(int i = 0; i < array_size; i++) {
            newElements[i] = current.value;
            current = current.next;
        }
        return newElements;
    }
}