package multilevel;

/**
 *
 * @authors Vikhyat
 *
 */
//The Process Control Block (PCB)
public class Process {

    public int pid;
    public int arrival_time;
    public int wait_time;
    public int actual_execution_time;
    public int burst_time;
    public int queue_number;

    Process(int p, int a, int w, int aet, int bt, int qn) {
        pid = p;
        arrival_time = a;
        wait_time = w;
        actual_execution_time = aet;
        burst_time = bt;
        queue_number = qn;
    }
}
