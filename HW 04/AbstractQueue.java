package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert size > 0;
        return peekImpl();
    }

    protected abstract Object enqueueImpl();

    public Object dequeue() {
        assert size != 0;
        return dequeueImpl();
    }

    protected abstract Object dequeueImpl();


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        assert size != 0;
        return toArray(size);
    }

    public abstract Object[] toArray(int array_size);
}