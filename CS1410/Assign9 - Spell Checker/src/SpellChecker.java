import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
    public static void main(String[] args) {

        // Step 1: Demonstrate tree capabilities
        testTree();

        // Step 2: Read the dictionary and report the tree statistics
        BinarySearchTree<String> dictionary = readDictionary();
        reportTreeStats(dictionary);

        // Step 3: Perform spell checking
        // First we'll read in the letter.txt file.
        File file = new File("/home/ben/IdeaProjects/Assign9 - Spell Checker/Letter.txt");
        try (Scanner input = new Scanner(file)) {
            ArrayList<String> words = new ArrayList<>();
            String[] noNos = {"(", ")", "?", "!", ".", ",", ":", ";", "\"", "'"};
            while (input.hasNextLine()) {
                String word = (input.next()).toLowerCase(); // we'll just make it lower case right off the bat.
                // Now we're going to parse each word: get rid of the noNos symbols.
                for (String bad: noNos){
                    int index = -1;
                    do {
                        index = word.indexOf(bad); // where's the first occurence of the bad symbol?
                        if (index != -1) {
                            if (index == word.length() -1) { // This case applies if the bad symbol occurs at the end.
                                word = word.substring(0, index);
                            }
                            else if (index == 0) { // Maybe it occurred at the start.
                                word = word.substring(index + 1, word.length());
                            }
                            else { // Otherwise the symbol occurs not at the end nor the beginning of the word.
                                word = word.substring(0, index) + word.substring(index + 1, word.length());
                            }
                        }
                    } while (index != -1);
                }

                words.add(word);
            }
            // Now the words are in the "words" ArrayList.
            for (String word: words) {
                // System.out.printf("%s ", word); // Used for testing.
                boolean isInDict = dictionary.search(word);
                if (!isInDict) {
                    System.out.println(word);
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("A very unfortunate error occurred in trying to read the file: " + ex);
        }


    }

    /**
     * This method is used to help develop the BST, also for the grader to
     * evaluate if the BST is performing correctly.
     */
    public static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");

        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);

        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);

        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);

        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
    }

    /**
     * This method is used to report on some stats about the BST
     */
    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }

    /**
     * This method reads the dictionary and constructs the BST to be
     * used for the spell checking.
     */
    public static BinarySearchTree<String> readDictionary() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        File file = new File("/home/ben/IdeaProjects/Assign9 - Spell Checker/Dictionary.txt");
        try (Scanner input = new Scanner(file)) {
            ArrayList<String> words = new ArrayList<>();
            while (input.hasNextLine()) {
                String word = input.nextLine();
                words.add(word);
                //tree.insert(word);
            }
            java.util.Collections.shuffle(words, new java.util.Random(System.currentTimeMillis()));
            //Now put the words into the tree.
            for (String word: words) {
                tree.insert(word);
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An unfortunate error occurred in trying to read the file: " + ex);
        }


        return tree;
    }
}
