package queue;

// Inv: (a.size >= 0) && (a[i] != null for i = 1..a.size)
public interface Queue{
    // Pre: element != null
    public void enqueue(Object element);
    // Post: (a.size == a'.size + 1) && (a[i] == a[i - 1] for i = 1..a.size) && a[1] == element

    // Pre: a.size > 0
    public Object element();
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a[1]

    // Pre: a.size > 0
    public Object dequeue();
    // Post: (a.size == a'.size - 1) && (for i = 1..a.size: a[i] == a'[i + 1])
    // Result == a'[1]

    // Pre: true
    public int size();
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == a.size

    // Pre: true
    public boolean isEmpty();
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])
    // Result == (a.size == 0)

    // Pre: true
    public void clear();
    // Post: a.size == 0

    //Pre: a.size > 0
    public Object[] toArray();
    // Post: (a.size == a'.size) && (for i = 1..a.size: a[i] == a'[i])

}