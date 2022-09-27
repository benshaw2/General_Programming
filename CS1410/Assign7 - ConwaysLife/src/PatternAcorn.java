public class PatternAcorn extends Pattern{
    public int SizeX;
    public int SizeY;
    public boolean[][] patternBool = {{false, false, false, false, true, false, false},
            {false, false, true, false, true, false, false},
            {false, false, false, false, false, false, false},
            {false, false, false, true, false, false, false},
            {false, false, false, false, true, false, false},
            {false, false, false, false, true, false, false},
            {false, false, false, false, true, false, false}};

    public PatternAcorn(){
        this.SizeX = 7;
        this.SizeY = 7;
        //this.patternBool = patternBool;
    }
    public int getSizeX(){ return this.SizeX; }
    public int getSizeY(){ return this.SizeY; }
    public boolean getCell(int x, int y){ return this.patternBool[x][y]; }
}