package queue;

// Inv: (a.size >= 0) && (a[i] != null for i = 1..a.size)
public class LinkedQueue extends AbstractQueue{
    private int size = 0;
    private Node head;
    private Node tail;

    // Pre: element != null
    public void enqueue(Object element) {
        Node previous = tail;
        tail = new Node(element, null);
        if(size == 0) {
            head = tail;
        } else {
            previous.next = tail;
        }
        size++;
    }
    // Post: (a.size == a'.size + 1) && (a[i] == a[i - 1] for i = 1..a.size) && a[1] == element

    // Pre: a.size > 0
    public Object element() {
        return head.value;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a[1]

    // Pre: a.size > 0
    public Object dequeue() {
        size--;
        Object result = head.value;
        head = head.next;
        return result;
    }
    // Post: (a.size == a'.size - 1) && (for i = 1..a.size: a[i] == a'[i + 1])
    // Result == a'[1]

    // Pre: true
    public int size() {
        return size;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a.size

    // Pre: true
    public boolean isEmpty() {
        return size == 0;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == (a.size == 0)

    // Pre: true
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    // Post: a.size == 0

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}