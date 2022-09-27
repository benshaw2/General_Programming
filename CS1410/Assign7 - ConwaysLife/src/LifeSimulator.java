import java.util.ArrayList;
import java.util.Collections;

public class LifeSimulator {
    public int SizeX;
    public int SizeY;
    public boolean[][] TFArray; //= new boolean[SizeY][SizeX];


    public LifeSimulator(int sizeX, int sizeY) {
        this.SizeX = sizeX;
        this.SizeY = sizeY;
        this.TFArray = new boolean[SizeY][SizeX];

    }

    public int getSizeX() { return this.SizeX; }
    public int getSizeY() { return this.SizeY; }
    public boolean getCell(int x, int y) { return this.TFArray[x][y]; }

    public void insertPattern(Pattern pattern, int startX, int startY) {
        //System.out.println("Stage 1");
        for (int row = 0; row < pattern.getSizeY(); row++){
            //System.out.println("Stage 2");
            for (int col = 0; col < pattern.getSizeX(); col++){
                //System.out.println("Stage 3");
                this.TFArray[startY + row][startX + col] = pattern.getCell(col,row);
                //System.out.println("Stage 4");
            }
        }
    }
    public void update() {
        //Here's the "extended array."
        boolean[][] tempArray = new boolean[this.getSizeY()+2][this.getSizeX()+2];
        //System.out.print("Step 1");
        //java.util.Arrays.fill(tempArray,false);
        //System.out.print("Step 2");
        //Now here's the array we're going to copy onto.
        boolean[][] newArray = new boolean[this.getSizeY()][this.getSizeX()];
        //Copy onto tempArray
        for (int i = 0; i < this.getSizeX(); i++) {
            for (int j = 0; j < this.getSizeY(); j++) {
                tempArray[j+1][i+1] = this.TFArray[j][i];
            }
        }
        //Now we're going to make the determinations.
        for (int i = 0; i< this.getSizeX(); i++) {
            for (int j = 0; j < this.getSizeY(); j++) {
                //We'll count up the number of 'true' neighbors.
                ArrayList<Boolean> counter = new ArrayList<>();
                counter.add(tempArray[j][i]); //1
                counter.add(tempArray[j][i+1]); //2
                counter.add(tempArray[j][i+2]); //3
                counter.add(tempArray[j+1][i]); //4
                counter.add(tempArray[j+1][i+2]); //5
                counter.add(tempArray[j+2][i]); //6
                counter.add(tempArray[j+2][i+1]); //7
                counter.add(tempArray[j+2][i+2]); //8
                int trues = Collections.frequency(counter, true);
                if (this.TFArray[j][i]) {
                    newArray[j][i] = (2 <= trues) && (trues <= 3);
                }
                else {
                    newArray[j][i] = trues == 3;
                }
            }
        }
        this.TFArray = newArray;
    }
}