import java.util.Scanner;

public class Pyramid1 {
    public static void main(String[] args) {
        Scanner lines = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int Lines = lines.nextInt();
        String linesString = Lines + "";
        int maxCharLength = linesString.length();
        String numFormat = "%" + (maxCharLength+1) + "d";
        for (int i=0; i <= Lines; i++){
            String preSpace = "%" + ((maxCharLength+1)*((Lines-i))+1) + "s";
            System.out.printf(preSpace, "");
            for (int j=0; j<i; j++){
                System.out.printf(numFormat,i-j);
            }
            for (int j=2; j<=i; j++){
                System.out.printf(numFormat,j);
            }
            System.out.println();

        }
    }
}
