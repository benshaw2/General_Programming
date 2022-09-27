import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment6Driver {
    public static final String ANSI_RESET = "\u001B[0m";
    //public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    //public static final String ANSI_GREEN = "\u001B[32m";
    //public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    //public static final String ANSI_PURPLE = "\u001B[35m";
    //public static final String ANSI_CYAN = "\u001B[36m";
    //public static final String ANSI_WHITE = "\u001B[37m";

    public static String red(String msg){
        return ANSI_RED + msg + ANSI_RESET;
    }

    public static String blue(String msg){
        return ANSI_BLUE + msg + ANSI_RESET;
    }

    public static void main(String[] args) {
        //testDS(13);
        //testGame();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }

    private static void testDS(int x){
        DisjointSet ds = new DisjointSet(x);
        System.out.println(ds.getSize());
        System.out.println(ds.find(5));
        ds.union(ds.find(5),ds.find(7));
        ds.union(ds.find(5),ds.find(1));
        ds.union(ds.find(7),ds.find(9));
        System.out.println(ds.find(5));
        System.out.println(ds.find(7));
        int[] dsarr = ds.getArr();
        String msg = "[";
        for(int i: dsarr){
            msg += i + ", ";
        }
        msg=msg.substring(0,msg.length()-1);
        msg=msg.substring(0,msg.length()-1);
        msg+="]";
        System.out.println(msg);
    }

    private static void playGame(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            // TODO: Write some good stuff here
            ArrayList<Integer> blueMoves = new ArrayList<>();
            ArrayList<Integer> redMoves = new ArrayList<>();
            int p = 1;
            while(input.hasNextInt()){ //Get the numbers into lists for blue and red turns.
                int turn = (int)Math.pow(-1,p);
                int num = input.nextInt();
                if(turn == -1){
                    blueMoves.add(num);
                }
                if(turn == 1){
                    redMoves.add(num);
                }
                p++;
            }
            //Now that the turns are read in, we will play the game.
            HexGame Game = new HexGame(11);
            boolean gameover = false;
            int t = 0;
            while(!gameover){
                /*int bMove = 10;
                int rMove = 110;
                if (t < blueMoves.size()){
                    bMove = blueMoves.get(t);
                }
                else{
                    printGrid(Game);
                    System.out.println(gameover);
                    System.out.println(Game.getBlueHexs());
                    System.out.println(Game.getRedHexs());
                    System.out.println(Game.getBoard().find(122));
                    System.out.println(Game.getBoard().find(123));
                    System.out.println(Game.getBoard().find(50));
                    System.out.println(Game.getBoard().getSetSize(7));
                    System.out.println(Game.getBoard().getSetSize(114));
                    System.out.println(Game.getBoard().getSetSize(123));
                    Game.getBoard().union(114,7);
                    System.out.println(Game.getBoard());
                    break;
                }
                if (t < redMoves.size()){
                    rMove = redMoves.get(t);
                }
                else{
                    printGrid(Game);
                    System.out.println(gameover);
                    System.out.println(Game.getBlueHexs());
                    System.out.println(Game.getRedHexs());
                    //break;
                }*/
                int bMove = blueMoves.get(t);
                gameover = Game.playBlue(bMove,false);
                if (gameover){
                    System.out.println("Blue wins with move at position " + bMove + "!!");
                    System.out.println();
                    printGrid(Game);
                    //break;
                }
                else{
                    int rMove = redMoves.get(t);
                    gameover = Game.playRed(rMove,false);
                    if (gameover){
                        System.out.println("Red wins with move at position " + rMove + "!!");
                        System.out.println();
                        printGrid(Game);
                        //break;
                    }
                }
                t++;
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    //
    // TODO: You can use this to compare with the output show in the assignment while working on your code
//    private static void testGame() {
//        HexGame game = new HexGame(11);
//
//        System.out.println("--- red ---");
//        game.playRed(1, true);
//        game.playRed(11, true);
//        game.playRed(122 - 12, true);
//        game.playRed(122 - 11, true);
//        game.playRed(122 - 10, true);
//        game.playRed(121, true);
//        game.playRed(61, true);
//
//        System.out.println("--- blue ---");
//        game.playBlue(1, true);
//        game.playBlue(2, true);
//        game.playBlue(11, true);
//        game.playBlue(12, true);
//        game.playBlue(121, true);
//        game.playBlue(122 - 11, true);
//        game.playBlue(62, true);
//
//        System.out.println(game.getRedHexs());
//        System.out.println(game.getBlueHexs());
//
//        printGrid(game);
//    }

    // TODO: Complete this method
    private static void printGrid(HexGame game) {
        String msg = "";
        for (int i=0; i<game.getSize(); i++){
            msg += " ".repeat(i);
            for (int j=0; j < game.getSize(); j++){
                int val = i*game.getSize()+j+1;
                if(game.getBlueHexs().contains(val)){
                    msg += blue("B ");
                }
                else if (game.getRedHexs().contains(val)){
                    msg += red("R ");
                }
                else{
                    msg += "0 ";
                }
            }
            msg += "\n";
        }
        System.out.println(msg);
        System.out.println();
    }

}
