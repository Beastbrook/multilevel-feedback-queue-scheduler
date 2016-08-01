package multilevel;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @authors Vikhyat
 *
 */
public class Multilevel {

    static int pi = 1;

    public static void main(String args[]) {

        //All the queues, with a total of 20 processes are created and the start time set to 0.
        ArrayList<Process> processes = input(20, 0);

        //Implementation of Queue 0
        System.out.print("Time\t\tDescription");
        ArrayList<Process> processes0 = new ArrayList<Process>();
        for (int i = 0; i < 7; ++i) {
            //The processes are added to the first queue
            processes0.add(processes.get(i));
        }

        //Implementation of Queue 1
        ArrayList<Process> processes1 = new ArrayList<Process>();
        for (int i = 7; i < 10; ++i) {
            //The processes are added to the second queue
            processes.get(i).queue_number = 1;
            processes1.add(processes.get(i));
        }

        //Implementation of Queue 2
        ArrayList<Process> processes2 = new ArrayList<Process>();
        for (int i = 10; i < 20; ++i) {
            //The processes are added to the third queue
            processes.get(i).queue_number = 2;
            processes2.add(processes.get(i));
        }

        ProcessQueue q0 = new ProcessQueue(0);
        //Round-robin on queue 0 is called with a time quantum of 8
        RR r1 = new RR(processes0, q0, 8, 0);
        r1.enterQueue();
        //After one iteration, the processes are added to remaining0 which is added to the second queue.
        ArrayList<Process> remaining0 = r1.executeRR();
        int end_time = r1.getTime();

        ProcessQueue q1 = new ProcessQueue(1);

        for (int i = 0; i < remaining0.size(); ++i) {
            remaining0.get(i).wait_time = end_time - remaining0.get(i).arrival_time - 8;
            remaining0.get(i).actual_execution_time = remaining0.get(i).burst_time + remaining0.get(i).wait_time;
            remaining0.get(i).arrival_time = end_time;

            processes1.add(remaining0.get(i));

        }
        
        //Round-robin on queue 1 is called with a time quantum of 16
        RR r2 = new RR(processes1, q1, 16, r1.getTime());
        r2.enterQueue();

        ArrayList<Process> remaining1 = r2.executeRR();
        int end_time1 = r2.getTime();

        ProcessQueue q2 = new ProcessQueue(2);

        for (int i = 0; i < remaining1.size(); ++i) {
            if (remaining1.get(i).queue_number == 0) {
                remaining1.get(i).wait_time += end_time - remaining1.get(i).arrival_time - 16;
            } else {
                remaining1.get(i).wait_time = end_time - remaining1.get(i).arrival_time - 16;

            }

            remaining1.get(i).actual_execution_time = remaining1.get(i).burst_time + remaining1.get(i).wait_time;
            remaining1.get(i).arrival_time = end_time1;
            processes2.add(remaining1.get(i));

        }
        FCFS r3 = new FCFS(processes2, q2, end_time1);
        r3.enterQueue();
        r3.executeFCFS();

    }

    //Arraylist describing all the processes
    public static ArrayList<Process> input(int n, int qn) {
        ArrayList<Process> processes = new ArrayList<Process>();
        Scanner cin = new Scanner(System.in);
        int bt, at;

        for (int i = 0; i < n; ++i) {
            System.out.println("Enter Burst Time for Process " + (pi));
            bt = cin.nextInt();
            System.out.println("Enter Arrival Time for Process " + (pi));
            at = cin.nextInt();
            processes.add(new Process(pi, at, 0, 0, bt, qn));
            pi++;
        }
        cin.close();
        return processes;
    }
}
