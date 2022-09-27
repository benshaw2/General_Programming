import java.util.Scanner;

public class Pyramid2 {
    public static void main(String[] args) {
        Scanner lines = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int Lines = lines.nextInt();
        int Lines2 = (int) Math.round(Math.pow(2,Lines-1));
        String linesString = Lines2 + "";
        int maxCharLength = linesString.length();
        String numFormat = "%" + (maxCharLength+1) + "d";
        for (int i=0; i < Lines; i++){
            String preSpace = "%" + ((maxCharLength+1)*((Lines-i))+1) + "s";
            System.out.printf(preSpace, "");
            for (int j=0; j<i; j++){
                System.out.printf(numFormat,Math.round(Math.pow(2,j)));
            }
            for (int j=i; j>=0; j--){
                System.out.printf(numFormat,Math.round(Math.pow(2,j)));
            }
            System.out.println();

        }
    }
}
