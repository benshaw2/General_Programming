public class TaskByStart extends Task {

    public TaskByStart(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    public int compareTo(Task t){
        int s1 = this.start;
        int s2 = t.start;
        return Integer.compare(s1, s2);
    }

}