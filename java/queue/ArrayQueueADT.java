package queue;

public class ArrayQueueADT {
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private Object[] elements = new Object[10];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        ensureCapacity(queue, queue.size + 1);
        queue.size++;
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        queue.size--;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return result;
    }

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.head = queue.tail = queue.size = 0;
        queue.elements = new Object[10];
    }

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


}

