import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisjointSet{
    private int size;
    private int[] arr;

    public DisjointSet(int size){
        this.size = size;
        this.arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = i+1;
        }
    }

    public int getSetSize(int root){
        int ans = 0;
        for (int i: this.arr){
            if (i == root){
                ans ++;
            }
        }
        return ans;
    }

    public void union(int root1, int root2){
        //System.out.println(root1 + ", " + root2);

        int s1 = getSetSize(root1);
        int s2 = getSetSize(root2);
        if (s2 > s1) {
            //System.out.println("cond 1");
            //make root2 the new root.
            /*for (int i: this.arr){
                if (i == root1){
                    arr[i-1] = root2;
                }
            }*/
            for (int i=0; i<this.arr.length; i++){
                if (this.arr[i] == root1){
                    arr[i] = root2;
                }
            }
        }
        else {
            //System.out.println("cond 2");
            //System.out.println(root1);
            //System.out.println(root2);
            //make root1 the new root.
            for (int i=0; i < this.arr.length; i++){
                if (this.arr[i] == root2){
                    //System.out.println(i-1);
                    arr[i] = root1;
                    //i = root1;
                }
            }
        }
    }

    public int find(int node){
        //return node;
        return this.arr[node-1];
    }

    public int getSize(){
        return this.size;
    }

    public int[] getArr(){
        return this.arr;
    }

    public String toString(){
        String msg = "[";
        for(int i: this.arr){
            msg += i + ", ";
        }
        msg=msg.substring(0,msg.length()-1);
        msg=msg.substring(0,msg.length()-1);
        msg+="]";
        return msg;
    }

}