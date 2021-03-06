package queue;

public class ArrayQueue extends AbstractQueue {
    private int head = 0;
    private int tail = 0;
    private int ARRAY_SIZE = 10;
    private Object[] elements = new Object[ARRAY_SIZE];

    public void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = increaseIndex(tail);
    }

    public Object elementImpl() {
        return elements[head];
    }

    public Object dequeueImpl() {
        Object result = elements[head];
        elements[head] = null;
        head = increaseIndex(head);
        return result;
    }

    public void clear() {
        head = tail = size = 0;
        elements = new Object[10];
    }

    private void ensureCapacity(int n) {
        if (n <= elements.length) {
            return;
        }
        elements = toArray(n * 2);
        head = 0;
        tail = size;
    }

    public Object[] toArray(int array_size) {
        Object[] newElements = new Object[array_size];
        int length = Math.min(size, elements.length - head);
        System.arraycopy(elements, head, newElements, 0, length);
        System.arraycopy(elements, 0, newElements, length, size - length);
        return newElements;
    }

    private int increaseIndex(int x) {
        return (x + 1) % elements.length;
    }
}