import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        //simpleQueueTest();
        scheduleTasks("taskset1.txt");
        scheduleTasks("taskset2.txt");
        scheduleTasks("taskset3.txt");
        scheduleTasks("taskset4.txt");
        //scheduleTasks("taskset5.txt");

        System.out.println();
    }

    public static void scheduleTasks(String taskFile) {
        // TODO: Uncomment code here as you complete the tasks and scheduling algorithm
        ArrayList<Task> tasksByDeadline = new ArrayList<>();
        ArrayList<Task> tasksByStart = new ArrayList<>();
        ArrayList<Task> tasksByDuration = new ArrayList<>();

        readTasks(taskFile, tasksByDeadline, tasksByStart, tasksByDuration);

        schedule("Deadline Priority : "+ taskFile, tasksByDeadline);
        schedule("Startime Priority : " + taskFile, tasksByStart);
        schedule("Duration priority : " + taskFile, tasksByDuration);
    }

    public static void simpleQueueTest() {
        // TODO: Uncomment code here for a simple test of your priority queue code
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(9);
        queue.enqueue(7);
        queue.enqueue(15);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(10);

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        queue.printTree();
    }

    /**
     * Reads the task data from a file, and creates the three different sets of tasks for each
     */
    public static void readTasks(String filename,
                                 ArrayList<Task> tasksByDeadline,
                                 ArrayList<Task> tasksByStart,
                                 ArrayList<Task> tasksByDuration) {
        // TODO: Write your task reading code here

        File file = new File(filename);

        try (Scanner input = new Scanner(file)){
            //System.out.println("Should be reading...");
            int id = 1;
            while (input.hasNextLine()){
                String inp = input.nextLine();
                //System.out.println(inp);
                //System.out.println()
                String f1 = inp.replaceAll("\\s", " ");
                String[] f2 = f1.split(" ");
                //System.out.println(f1);
                //System.out.println(f2[0]);
                int tempStart = Integer.parseInt(f2[0]);
                int tempDeadline = Integer.parseInt(f2[1]);
                int tempDuration = Integer.parseInt(f2[2]);
                TaskByStart tbs = new TaskByStart(id,tempStart,tempDeadline,tempDuration);
                TaskByDeadline tbdl = new TaskByDeadline(id,tempStart,tempDeadline,tempDuration);
                TaskByDuration tbd = new TaskByDuration(id,tempStart,tempDeadline,tempDuration);
                tasksByStart.add(tbs);
                tasksByDeadline.add(tbdl);
                tasksByDuration.add(tbd);
                //System.out.println(tasksByStart);
                id++;
            }
        }

        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the file: " + ex);
        }
    }

    /**
     * Given a set of tasks, schedules them and reports the scheduling results
     */
    public static void schedule(String label, ArrayList<Task> tasks) {
        // TODO: Write your scheduling algorithm here
        System.out.println(label);
        PriorityQueue<Task> jobs = new PriorityQueue<>();
        //First, add all of the tasks to the queue.
        for (Task thing : tasks){
            jobs.enqueue(thing);
        }
        //boolean done = false;
        int time = 1;
        int latetasks = 0;
        int lateness = 0;
        while (!jobs.isEmpty()){
            String msg = "";
            //Pull off the task.
            Task tsk = jobs.dequeue();
            //append the message
            msg += "\nTime " + time + ": ";
            // do some work on it if we can.
            if (tsk.start <= time){
                tsk.duration--;
                msg += tsk.toString();
                //ask if it's done
                if (tsk.duration == 0){
                    msg += " **";
                    //Is it late?
                    if (time > tsk.deadline){
                        msg += " Late " + (time-tsk.deadline);
                        latetasks += 1;
                        lateness += (time-tsk.deadline);
                    }
                    //msg += "\n";
                }
                else {
                    //msg += "\n";
                    jobs.enqueue(tsk);
                }
                System.out.print(msg);
                time ++;
            }
            else /*if (altTsk.start <= time)*/{
                Task altTsk = jobs.dequeue();
                altTsk.duration--;
                msg += altTsk.toString();
                //ask if it's done
                if (altTsk.duration == 0){
                    msg += " **";
                    //Is it late?
                    if (time > altTsk.deadline){
                        msg += " Late " + (time-altTsk.deadline);
                        latetasks += 1;
                        lateness += (time-altTsk.deadline);
                    }
                    //msg += "\n";
                    jobs.enqueue(tsk);
                }
                else {
                    //msg += "\n";
                    jobs.enqueue(altTsk);
                    jobs.enqueue(tsk);
                }
                System.out.print(msg);
                time ++;
            }
            /*else{
                //System.out.println(tsk.toString());
                //Is there anything else we can work on?
                boolean found = false;
                ArrayList<Task> bin = new ArrayList<>();
                while (!found || !jobs.isEmpty()){
                    Task tskAlt = jobs.dequeue();
                    //System.out.println(tskAlt.toString());
                    if (tskAlt.start <= time){
                        System.out.println("found one: " + tskAlt.toString());
                        found = true;
                        //do some work on it.
                        tskAlt.duration--;
                        msg += tskAlt.toString();
                        //ask if it's done
                        if (tskAlt.duration == 0){
                            msg += " **";
                            //Is it late?
                            if (time > tskAlt.deadline){
                                msg += " Late " + (time-tskAlt.deadline);
                            }
                            msg += "\n";
                        }
                        else {
                            msg += "\n";
                            jobs.enqueue(tskAlt);
                        }
                        System.out.print(msg);
                        //time ++;
                        //tsk = tskAlt;
                    }
                    else{
                        System.out.println("Didn't find one");
                        bin.add(tskAlt);
                        if (jobs.isEmpty()){
                            msg += "---";
                        }
                    }
                }
                //Now add the ones we couldn't work on back to the queue.
                for (Task thing: bin){
                    System.out.println("Adding things back into the bin...");
                    System.out.println(thing.toString());
                    jobs.enqueue(thing);
                }
                System.out.println("What's the value of tsk?");
                System.out.println(tsk.toString());
                jobs.enqueue(tsk);
            }*/
        }
        System.out.println();
        System.out.println("Tasks late " + latetasks + " Total Late " + lateness);
        System.out.println();
        System.out.println();
    }
}
