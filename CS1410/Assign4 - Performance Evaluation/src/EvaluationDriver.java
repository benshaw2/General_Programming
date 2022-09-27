/**
 * Assignment 4 for CS 1410
 * This program evaluates the linear and binary searching, along
 * with comparing performance difference between the selection sort
 * and the built-in java.util.Arrays.sort.
 *
 * @author James Dean Mathias
 */
public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_SIZE_START = 20_000;
    static final int ARRAY_SIZE_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static int[] generateNumbers(int howMany, int maxValue) {
        if (1 <=howMany) {

            int[] arr = new int[howMany];
            for (int i = 0; i < howMany; i++) {
                arr[i] = (int)(Math.random() * maxValue);
            }
            return arr;
        }
        else {
            return null;
        }
    }

    public static boolean linearSearch(int[] data, int search) {
        int[] found = new int[1];
        for (int datum : data) {
            if (datum == search) {
                found[0] = datum;
                break;
            }

        }
        return found[0] == search;
    }

    public static boolean binarySearch(int[] data, int search) {
        int lower = 0;
        int upper = data.length - 1;
        boolean found = false;
        while (!found && lower <= upper) {
            int middle = (lower + upper) / 2;
            if (data[middle] == search) {
                found = true;
            }
            if (data[middle] < search) {
                lower = middle + 1;
            }
            else {
                upper = middle - 1;
            }
        }
        return found;

    }

    public static void selectionSort(int[] data) {
        for (int start = 0; start < data.length - 1; start++) {
            int minPosition = start;
            for (int scan = start + 1; scan < data.length; scan++) {
                if (data[scan] < data[minPosition]) {
                    minPosition = scan;
                }
            }
            int temporary = data[start];
            data[start] = data[minPosition];
            data[minPosition] = temporary;
        }
    }

    public static void demoLinearSearchUnsorted() {
        System.out.println("--- Linear Search Timing (unsorted) ---");
        long totalTime = 0;
        long wasFound = 0;
        for (int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i += ARRAY_SIZE_INCREMENT) {
            int[] randArray = generateNumbers(i, MAX_VALUE);
            for (int j = 0; j < NUMBER_SEARCHES; j++) {
                int randSearch = (int) (Math.random() * MAX_VALUE);
                long initialTime = System.currentTimeMillis();
                boolean found = linearSearch(randArray, randSearch);
                long finalTime = System.currentTimeMillis();
                totalTime += (finalTime - initialTime);
                if (found) {
                    wasFound += 1;
                }

            }
            System.out.println("Number of items: " + i);
            System.out.println("Times value was found: " + wasFound);
            System.out.println("Total search time: " + totalTime + "ms");
            System.out.println("");
        }
    }

    public static void demoLinearSearchSorted() {
        /* For now, this is the code for the linearsearchunsorted method.*/
        System.out.println("--- Linear Search Timing (Selection Sort) ---");
        long totalTime = 0;
        long wasFound = 0;
        for (int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i += ARRAY_SIZE_INCREMENT) {
            int[] randArray = generateNumbers(i, MAX_VALUE);
            long initialTime1 = System.currentTimeMillis();
            selectionSort(randArray);
            long finalTime1 = System.currentTimeMillis();
            totalTime += (finalTime1 - initialTime1);
            for (int j = 0; j < NUMBER_SEARCHES; j++) {
                int randSearch = (int) (Math.random() * MAX_VALUE);
                long initialTime = System.currentTimeMillis();
                boolean found = linearSearch(randArray, randSearch);
                long finalTime = System.currentTimeMillis();
                totalTime += (finalTime - initialTime);
                if (found) {
                    wasFound += 1;
                }

            }
            System.out.println("Number of items: " + i);
            System.out.println("Times value was found: " + wasFound);
            System.out.println("Total search time: " + totalTime + "ms");
            System.out.println("");
        }
    }

    public static void demoBinarySearchSelectionSort() {
        System.out.println("--- Binary Search Timing (Selection Sort) ---");
        long totalTime = 0;
        long wasFound = 0;
        for (int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i += ARRAY_SIZE_INCREMENT) {
            int[] randArray = generateNumbers(i, MAX_VALUE);
            long initialTime1 = System.currentTimeMillis();
            selectionSort(randArray);
            long finalTime1 = System.currentTimeMillis();
            totalTime += (finalTime1 - initialTime1);
            for (int j = 0; j < NUMBER_SEARCHES; j++) {
                int randSearch = (int) (Math.random() * MAX_VALUE);
                long initialTime = System.currentTimeMillis();
                boolean found = binarySearch(randArray, randSearch);
                long finalTime = System.currentTimeMillis();
                totalTime += (finalTime - initialTime);
                if (found) {
                    wasFound += 1;
                }

            }
            System.out.println("Number of items: " + i);
            System.out.println("Times value was found: " + wasFound);
            System.out.println("Total search time: " + totalTime + "ms");
            System.out.println("");
        }
    }

    public static void demoBinarySearchFastSort() {
        /* For now, this is the code for the linearsearchunsorted method.*/
        System.out.println("--- Binary Search Timing (Arrays.sort) ---");
        long totalTime = 0;
        long wasFound = 0;
        for (int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i += ARRAY_SIZE_INCREMENT) {
            int[] randArray = generateNumbers(i, MAX_VALUE);
            long initialTime1 = System.currentTimeMillis();
            java.util.Arrays.sort(randArray);
            long finalTime1 = System.currentTimeMillis();
            totalTime += (finalTime1 - initialTime1);
            for (int j = 0; j < NUMBER_SEARCHES; j++) {
                int randSearch = (int) (Math.random() * MAX_VALUE);
                long initialTime = System.currentTimeMillis();
                boolean found = binarySearch(randArray, randSearch);
                long finalTime = System.currentTimeMillis();
                totalTime += (finalTime - initialTime);
                if (found) {
                    wasFound += 1;
                }

            }
            System.out.println("Number of items: " + i);
            System.out.println("Times value was found: " + wasFound);
            System.out.println("Total search time: " + totalTime + "ms");
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        demoLinearSearchUnsorted();
        demoLinearSearchSorted();
        demoBinarySearchSelectionSort();
        demoBinarySearchFastSort();
    }

}
