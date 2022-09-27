public class TaskByDuration extends Task {

    public TaskByDuration(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    public int compareTo(Task t){
        int s1 = this.duration;
        int s2 = t.duration;
        return Integer.compare(s1, s2);
    }

}