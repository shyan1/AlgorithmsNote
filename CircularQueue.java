public class CircularQueue {
    private String[] items;
    private int n = 0;

    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        // 队列满
        if ((tail + 1) % n == head) return false;

        items[tail] = item;
        tail = (tail + 1) % n;

        return true;
    }

    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) return null; 

        String ret = items[head];
        head = (head + 1) % n;

        return ret;
    }
}