public class Recursion {


    public static boolean isWordSymmetric(String[] words, int start, int end){
        if(start == end){
            return true;
        }
        else if (start + 1 == end){
            return (words[start]).equalsIgnoreCase(words[end]);
        }
        else{
            return (words[start].equalsIgnoreCase(words[end])) && isWordSymmetric(words, start + 1, start + 1);
        }
    }

    public static long arraySum(int[] data, int position){
        if (data.length == 0){
            return 0;
        }
        else{
            long sum = data[position];
            if (position == data.length - 1){
                return sum;
            }
            else{
                sum += arraySum(data, position + 1);
            }
            return sum;
        }

    }

    public static int arrayMin(int[] data, int position){
        int min = data[position];
        if (position == data.length - 1){
            return min;
        }
        else{
            return Math.min(min, arrayMin(data, position + 1));
        }
    }

    public static double computePyramidWeights(double[][] masses, int row, int column){
        if (row == -1 || column == -1){
            return 0;
        }
        else if (masses[row].length-1 < column){
            return 0;
        }
        else{
            double selfW = masses[row][column];
            return (selfW + 0.5 * computePyramidWeights(masses, row-1, column-1)
                    + 0.5 * computePyramidWeights(masses, row-1, column) );
        }

    }

    public static void magicRecursive(char[][] Image, int Row, int column, int character){
        char[] charList = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};
        //char [] nexRow = Image[];
        if (Row == -1 || column == -1){
            return;
        }
        else if (Image[Row].length-1 < column){
            return;
        }
        else{
            if (Image[Row][column] == '*'){
                Image[Row][column] = charList[character];
                magicRecursive(Image, Row, column-1, character);
                magicRecursive(Image, Row, column+1, character);
                magicRecursive(Image, Row-1, column, character);
                if (Row != Image.length - 1) {
                    magicRecursive(Image, Row+1, column, character);
                }
            }
            else{
                return;
            }
        }
    }

    public static int howManyOrganisms(char[][] image){
        int count = 0;
        int Row = 0;
        for (char[] y: image){
            for (int i = 0; i < y.length; i++){
                if (y[i] == '*'){
                    magicRecursive(image, Row, i, count);
                    count += 1;
                }
            }
            Row += 1;
        }
        return count;
    }




    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double masses[][] = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < masses.length; row++) {
            for (int column = 0; column < masses[row].length; column++) {
                double weight = computePyramidWeights(masses, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char image[][] = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }
}
