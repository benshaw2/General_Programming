import java.lang.reflect.Array;
import java.util.ArrayList;

public class HexGame{
    private int size;
    private int bSize;
    private DisjointSet board;
    private ArrayList<Integer> blueHexs; //This will be used to keep track of which hexes are blue.
    private ArrayList<Integer> redHexs; //This will be used to keep track of which hexes are red.
    private int TOP_EDGE;
    private int BOTTOM_EDGE;
    private int LEFT_EDGE;
    private int RIGHT_EDGE;

    public HexGame(int size){
        this.size = size;
        this.bSize = (int)Math.pow(size,2);
        this.board = new DisjointSet(bSize+4); //the last four will be the edges.
        this.blueHexs = new ArrayList<>();
        this.redHexs = new ArrayList<>();
        this.TOP_EDGE = bSize + 1;
        this.BOTTOM_EDGE = bSize + 2;
        this.LEFT_EDGE = bSize + 3;
        this.RIGHT_EDGE = bSize + 4;
    }

    public ArrayList<Integer> getNeighbors(int node){
        ArrayList<Integer> neighbors = new ArrayList<>();
        if(node == 1){
            //top left corner case.
            neighbors.add(2);
            neighbors.add(this.size + 1);
            neighbors.add(TOP_EDGE);
            neighbors.add(LEFT_EDGE);
        }
        else if(node == this.size){
            //Top right corner case.
            neighbors.add(size-1);
            neighbors.add(2*this.size);
            neighbors.add(2*this.size - 1);
            neighbors.add(TOP_EDGE);
            neighbors.add(RIGHT_EDGE);
        }
        else if(node == this.bSize){
            //bottom right corner case.
            neighbors.add(this.bSize-1);
            neighbors.add(this.bSize -size);
            neighbors.add(BOTTOM_EDGE);
            neighbors.add(RIGHT_EDGE);
        }
        else if(node == this.bSize - this.size+1){
            //bottom left corner case.
            neighbors.add(node+1);
            neighbors.add(node-this.size);
            neighbors.add(node - this.size + 1);
            neighbors.add(BOTTOM_EDGE);
            neighbors.add(LEFT_EDGE);
        }
        else if(node <= this.size){
            //top edge case.
            neighbors.add(node-1);
            neighbors.add(node+1);
            neighbors.add(node + this.size);
            neighbors.add(node + this.size - 1);
            neighbors.add(TOP_EDGE);
        }
        else if (this.bSize - node < this.size){
            //bottom edge case
            neighbors.add(node-1);
            neighbors.add(node+1);
            neighbors.add(node - this.size + 1);
            neighbors.add(node - this.size);
            neighbors.add(BOTTOM_EDGE);
        }
        else if(node % size == 0){
            //right edge case.
            neighbors.add(node-1);
            neighbors.add(node + this.size);
            neighbors.add(node - this.size);
            neighbors.add(node + this.size - 1);
            neighbors.add(RIGHT_EDGE);
        }
        else if(node % size == 1){
            //left edge case.
            neighbors.add(node+1);
            neighbors.add(node + this.size);
            neighbors.add(node - this.size + 1);
            neighbors.add(node - this.size);
            neighbors.add(LEFT_EDGE);
        }
        else{
            neighbors.add(node-1);
            neighbors.add(node+1);
            neighbors.add(node + this.size);
            neighbors.add(node - this.size + 1);
            neighbors.add(node - this.size);
            neighbors.add(node + this.size - 1);
        }
        return neighbors;
    }

    public ArrayList<Integer> getNeighborsRed(int position){
        ArrayList<Integer> neighbors = getNeighbors(position);
        ArrayList<Integer> redNeighbors = new ArrayList<>();
        for (int i: neighbors){
            if (redHexs.contains(i)){
                redNeighbors.add(i);
            }
        }
        if (neighbors.contains(TOP_EDGE)){
            redNeighbors.add(TOP_EDGE);
        }
        if (neighbors.contains(BOTTOM_EDGE)){
            redNeighbors.add(BOTTOM_EDGE);
        }
        return redNeighbors;
    }

    public ArrayList<Integer> getNeighborsBlue(int position){
        ArrayList<Integer> neighbors = getNeighbors(position);
        ArrayList<Integer> blueNeighbors = new ArrayList<>();
        for (int i: neighbors){
            if (blueHexs.contains(i)){
                blueNeighbors.add(i);
            }
        }
        if (neighbors.contains(RIGHT_EDGE)){
            blueNeighbors.add(RIGHT_EDGE);
        }
        if (neighbors.contains(LEFT_EDGE)){
            blueNeighbors.add(LEFT_EDGE);
        }
        return blueNeighbors;
    }

    public boolean playBlue(int position, boolean displayNeighbors){
        boolean win = false;
        if((blueHexs.contains(position) || redHexs.contains(position) || position < 0 || position > Math.pow(this.size,2))){
            System.out.println("Blue can't play " + position + ".");
            return win;
        }
        else{
            if (blueHexs.size() > 0){
                ArrayList<Integer> blueN = getNeighborsBlue(position);
                for (int i: blueN){
                    //board.union(position, i);
                    board.union(board.find(position), board.find(i));
                }
            }
            blueHexs.add(0,position);
            if(board.find(LEFT_EDGE) == board.find(RIGHT_EDGE)){
                win = true;
            }
        }
        if (displayNeighbors){
            System.out.println("Cell " + position + ": " + getNeighborsBlue(position));
        }
        return win;
    }

    public boolean playRed(int position, boolean displayNeighbors){
        boolean win = false;
        if((blueHexs.contains(position) || redHexs.contains(position) || position < 0 || position > Math.pow(this.size,2))){
            System.out.println("Red can't play " + position + ".");
            return win;
        }
        else{
            if (redHexs.size() > 0){
                ArrayList<Integer> redN = getNeighborsRed(position);
                for (int i: redN){
                    //board.union(position, i);
                    board.union(board.find(position), board.find(i));
                }
            }
            redHexs.add(0,position);
            if(board.find(TOP_EDGE) == board.find(BOTTOM_EDGE)){
                win = true;
            }
        }
        if (displayNeighbors){
            System.out.println("Cell " + position + ": " + getNeighborsRed(position));
        }
        return win;
    }

    public int getbSize(){
        return this.bSize;
    }

    public int getSize() {
        return size;
    }

    public DisjointSet getBoard() {
        return board;
    }

    public ArrayList<Integer> getBlueHexs() {
        return blueHexs;
    }

    public ArrayList<Integer> getRedHexs() {
        return redHexs;
    }
}