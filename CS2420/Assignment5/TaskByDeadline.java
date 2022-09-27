public class TaskByDeadline extends Task {

    public TaskByDeadline(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    public int compareTo(Task t){
        int s1 = this.deadline;
        int s2 = t.deadline;
        return Integer.compare(s1, s2);
    }

}