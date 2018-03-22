package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    public void enqueueImpl(Object element) {
        Node previous = tail;
        tail = new Node(element, null);
        if (size == 0) {
            head = tail;
        } else {
            previous.next = tail;
        }
    }

    public Object elementImpl() {
        return head.value;
    }

    public Object dequeueImpl() {
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

    /*public Object[] toArray() {
        Object[] newElements = new Object[size];
        Node current = head;
        for(int i = 0; i < size; i++) {
            newElements[i] = current.value;
            current = current.next;
        }
        return newElements;
    }*/
}