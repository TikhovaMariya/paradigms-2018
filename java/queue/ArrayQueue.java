package queue;

public class ArrayQueue {
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private Object[] elements = new Object[10];

    public void enqueue(Object element) {
        ensureCapacity(size + 1);
        size++;
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    public Object element() {
        return elements[head];
    }

    public Object dequeue() {
        size--;
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = tail = size = 0;
        elements = new Object[10];
    }

    private void ensureCapacity(int n) {
        if (n <= elements.length) {
            return;
        }
        Object[] newElements = new Object[n * 2];
        int length = Math.min(size, elements.length - head);
        System.arraycopy(elements, head, newElements, 0, length);
        System.arraycopy(elements, 0, newElements, length, size - length);
        elements = newElements;
        head = 0;
        tail = size;
    }


}