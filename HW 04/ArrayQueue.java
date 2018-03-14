package queue;

public class ArrayQueue extends AbstractQueue{
    private int head = 0;
    private int tail = 0;
    private int ARRAY_SIZE = 10;
    private Object[] elements = new Object[ARRAY_SIZE];

    public void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    public Object elementImpl() {
        return elements[head];
    }

    public Object dequeueImpl() {
        assert size != 0;
        size--;
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
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
        Object[] newElements = new Object[n * 2];
        int length = Math.min(size, elements.length - head);
        System.arraycopy(elements, head, newElements, 0, length);
        System.arraycopy(elements, 0, newElements, length, size - length);
        elements = newElements;
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
}