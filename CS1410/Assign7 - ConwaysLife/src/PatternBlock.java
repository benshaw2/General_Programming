public class PatternBlock extends Pattern{
    public int SizeX;
    public int SizeY;
    public boolean[][] patternBool;

    public PatternBlock(){
        this.SizeX = 2;
        this.SizeY = 2;
        this.patternBool = new boolean[2][2]; //{{true, true}, {true, true}};
        this.patternBool[0][0] = true;
        this.patternBool[0][1] = true;
        this.patternBool[1][0] = true;
        this.patternBool[1][1] = true;
    }
    public int getSizeX(){ return this.SizeX; }
    public int getSizeY(){ return this.SizeY; }
    public boolean getCell(int x, int y){ return this.patternBool[x][y]; }
}