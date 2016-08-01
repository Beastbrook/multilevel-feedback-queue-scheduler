package multilevel;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @authors Vikhyat
 *
 */
//The queue in which the processes are added and removed from
public class ProcessQueue {

    Queue<Process> queue;
    int size;
    public int number;

    ProcessQueue(int n) {
        queue = new LinkedList<Process>();
        size = 0;
        number = n;
    }

    //Adding a process to the queue
    public void push(Process p) {
        queue.add(p);
    }

    //Removing a process to the queue
    public Process pop() {
        return queue.remove();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
