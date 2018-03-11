package queue;

// Inv: (a.size >= 0) && (a[i] != null for i = 1..a.size)
public class ArrayQueueADT {
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private Object[] elements = new Object[10];

    // Pre: queue != null && element != null
    public static void enqueue(ArrayQueueADT queue, Object element) {
        ensureCapacity(queue, queue.size + 1);
        queue.size++;
        queue.elements[queue.tail] = element;
        queue.tail = increaseIndex(queue, queue.tail);
    }
    // Post: (a.size == a'.size + 1) && (a[i] == a[i - 1] for i = 1..a.size) && a[1] == element

    // Pre: queue != null && a.size > 0
    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a[1]

    // Pre: queue != null && a.size > 0
    public static Object dequeue(ArrayQueueADT queue) {
        queue.size--;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = increaseIndex(queue, queue.head);
        return result;
    }
    // Post: (a.size == a'.size - 1) && (for i = 1..a.size: a[i] == a'[i + 1])
    // Result == a'[1]

    // Pre: queue != null && true
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a.size

    // Pre:queue != null && true
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == (a.size == 0)

    // Pre:  queue != null && true
    public static void clear(ArrayQueueADT queue) {
        queue.head = queue.tail = queue.size = 0;
        queue.elements = new Object[10];
    }
    // Post: a.size == 0

    // Pre: queue != null && n >= 0
    private static void ensureCapacity(ArrayQueueADT queue, int n) {
        if (n <= queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[n * 2];
        int length = Math.min(queue.size, queue.elements.length - queue.head);
        System.arraycopy(queue.elements, queue.head, newElements, 0, length);
        System.arraycopy(queue.elements, 0, newElements, length, queue.size - length);
        queue.elements = newElements;
        queue.head = 0;
        queue.tail = queue.size;
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i]) && (elements.length >= n)

    private static int increaseIndex(ArrayQueueADT queue, int i) {
        return (i + 1) % queue.elements.length;
    }

    //Pre: queue != null && a.size > 0
    public static Object[] toArray(ArrayQueueADT queue) {
        return toArray(queue, queue.size);
    }
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])

    private static Object[] toArray(ArrayQueueADT queue, int array_size) {
        Object[] newElements = new Object[array_size];
        int length = Math.min(queue.size, queue.elements.length - queue.head);
        System.arraycopy(queue.elements, queue.head, newElements, 0, length);
        System.arraycopy(queue.elements, 0, newElements, length, queue.size - length);
        return newElements;
    }
}

