import java.util.LinkedList;

public class Queue<E>{
    LinkedList<E> data = new LinkedList<>();

    public void enqueue(E value){
        data.add(value);
        //System.out.println("enqueue.");
    }

    public E dequeue(){
        return data.pop();
        //System.out.println("dequeue");
    }

    /* the following method is for debugging only. */

    public int theSize(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.size()==0;
    }
}