public class PatternGlider extends Pattern{
    public int SizeX;
    public int SizeY;
    public boolean[][] patternBool = {{false, true, false}, {false, false, true}, {true, true, true}};

    public PatternGlider(){
        this.SizeX = 3;
        this.SizeY = 3;
        //this.patternBool = patternBool;
    }
    public int getSizeX(){ return this.SizeX; }
    public int getSizeY(){ return this.SizeY; }
    public boolean getCell(int x, int y){ return this.patternBool[x][y]; }
}