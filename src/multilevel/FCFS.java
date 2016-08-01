package multilevel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @authors Vikhyat
 *
 */
//The First Come First Serve Algorithm
public class FCFS {

    ArrayList<Process> processes;
    ProcessQueue queue;
    int time;
    int start_time;
    boolean fl;

    FCFS(ArrayList<Process> p, ProcessQueue q, int st) {
        processes = p;
        queue = q;

        start_time = st;
        time = start_time;
        fl = true;
    }

    public ArrayList<Process> executeFCFS() {
        int start = start_time, end;
        ArrayList<Process> remaining = new ArrayList<Process>();

        while (true) {

            while (!queue.isEmpty()) {
                start = time;
                Process current = queue.pop();

                if (fl) {
                    System.out.print("\n" + start + "ms" + "\t\t");
                }
                fl = true;

                System.out.print("Process " + current.pid + " is selected ");
                current.wait_time = time - current.arrival_time;
                time += current.burst_time;
                current.wait_time += time - current.arrival_time - current.burst_time;
                current.actual_execution_time = current.burst_time + current.wait_time;
                end = time;

                SortedMap<Integer, Process> map = new TreeMap<Integer, Process>();
                for (int i = 0; i < processes.size(); ++i) {
                    if (processes.get(i).arrival_time > start && processes.get(i).arrival_time <= end) {
                        map.put(processes.get(i).arrival_time, processes.get(i));
                    }
                }

                ArrayList<Integer> keys = new ArrayList<Integer>(map.keySet());
                for (int i = 0; i < map.size(); ++i) {

                    if (keys.get(i) != end) {
                        System.out.print("\n" + keys.get(i) + "ms" + "\t\t");

                    }
                    queue.push(map.get(keys.get(i)));
                    System.out.print("Process " + map.get(keys.get(i)).pid + " is created. ");
                    System.out.print("Process " + map.get(keys.get(i)).pid + " enters " + "Q" + queue.number + ". ");
                }

            }
            int i = 0;
            for (i = 0; i < processes.size(); ++i) {
                if (processes.get(i).arrival_time > time) {
                    time++;
                    break;
                }

            }

            if (i != processes.size()) {
                enterQueue();
                continue;
            }
            break;
        }
        return remaining;
    }

    public int getTime() {
        return time;
    }

    public void enterQueue() {

        for (int i = 0; i < processes.size(); ++i) {

            if (processes.get(i).arrival_time <= time) {
                if (fl) {
                    System.out.print("\n" + time + "ms" + "\t\t");
                    fl = false;
                }

                queue.push(processes.get(i));
                if (processes.get(i).queue_number != queue.number) {
                    System.out.print("Process " + processes.get(i).pid + " is aged. ");
                    System.out.print("Process " + processes.get(i).pid + " is moved to Q" + queue.number + " . ");
                } else {
                    System.out.print("Process " + processes.get(i).pid + " is created. ");
                    System.out.print("Process " + processes.get(i).pid + " enters " + "Q" + queue.number + ". ");
                }

            }
        }

        if (queue.isEmpty()) {
            time++;
            enterQueue();
        }

    }
}
