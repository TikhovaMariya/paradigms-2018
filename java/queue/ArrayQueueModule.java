package queue;

public class ArrayQueueModule {
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;
    private static Object[] elements = new Object[10];

    public static void enqueue(Object element) {
        ensureCapacity(size + 1);
        size++;
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    public static Object element() {
        return elements[head];
    }

    public static Object dequeue() {
        size--;
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return result;
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        head = tail = size = 0;
        elements = new Object[10];
    }

    private static void ensureCapacity(int n) {
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

