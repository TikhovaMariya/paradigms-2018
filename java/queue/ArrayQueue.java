package queue;

// Inv: (a.size >= 0) && (a[i] != null for i = 1..a.size)
public class ArrayQueue extends AbstractQueue{
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private Object[] elements = new Object[10];

    // Pre: element != null
    public void enqueue(Object element) {
        ensureCapacity(size + 1);
        size++;
        elements[tail] = element;
        tail = increaseIndex(tail);
    }
    // Post: (a.size == a'.size + 1) && (a[i] == a[i - 1] for i = 1..a.size) && a[1] == element

    // Pre: a.size > 0
    public Object element() {
        return elements[head];
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a[1]

    // Pre: a.size > 0
    public Object dequeue() {
        size--;
        Object result = elements[head];
        elements[head] = null;
        head = increaseIndex(head);
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
        head = tail = size = 0;
        elements = new Object[10];
    }
    // Post: a.size == 0

    // Pre: n >= 0
    private void ensureCapacity(int n) {
        if (n <= elements.length) {
            return;
        }
        elements = toArray(n * 2);
        head = 0;
        tail = size;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i]) && (elements.length >= n)

    private int increaseIndex(int i) {
        return (i + 1) % elements.length;
    }

    //Pre: a.size > 0
    public Object[] toArray() {
        return toArray(size);
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])

    private Object[] toArray(int array_size) {
        Object[] newElements = new Object[array_size];
        int length = Math.min(size, elements.length - head);
        System.arraycopy(elements, head, newElements, 0, length);
        System.arraycopy(elements, 0, newElements, length, size - length);
        return newElements;
    }
}