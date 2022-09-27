import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WritePoetry {

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println("This is main");
        writePoem("green.txt", "I", 7,true);
    }

    public static String writePoem(String file, String startWord, int length, boolean printHashtable) throws FileNotFoundException {
        String msg = "Is this working?";
        // This part is mostly ripped from the testHashTable method.
        File poemFile = new File(file);
        HashTable<String, WordFreqInfo> allWords = new HashTable<>();
        Scanner input2 = new Scanner(poemFile);
        String follow1 = input2.next();

        try (Scanner input = new Scanner(poemFile)) {
            while (input2.hasNext()) {
                String word = cleanWord(input.next());
                String follow = cleanWord(input2.next());
                /*String word1 = input.next();
                String follow = "";
                if ( !Character.isAlphabetic(word1.charAt(word1.length()-1)) && word1.charAt(word1.length()-1) != '\'' ){
                    follow += String.valueOf(word1.charAt(word1.length()-1));
                }
                else{
                    follow += cleanWord(input2.next());
                }
                String word = cleanWord(word1);*/
                if (word.length() > 0) {
                    WordFreqInfo wrd = allWords.find(word);
                    if (wrd != null){
                        wrd.updateFollows(follow);
                    }
                    else{
                        WordFreqInfo thing = new WordFreqInfo(word, 1);
                        allWords.insert(word, thing);
                    }
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
        if (printHashtable) {
            System.out.printf("HashTable Info:\n");
            System.out.printf("\tSize: %d\n", allWords.size());
            System.out.println(allWords.toString(allWords.size()));
        }

        //Now we're going to generate the poem.
        String Msg = startWord + " ";
        ArrayList<WordFreqInfo> wordList = new ArrayList<>();
        //System.out.println(allWords.find(startWord));
        wordList.add(allWords.find(startWord.toLowerCase()));
        //System.out.println(wordList.size());
        //System.out.println(wordList.get(0));
        for (int i=0; i<length; i++){
            int randBound = wordList.get(i).getOccurCount() - 1;
            int ranNum = (int)(Math.random() * randBound);
            String nextWord = wordList.get(i).getFollowWord(ranNum);
            wordList.add(allWords.find(nextWord));
            Msg += wordList.get(wordList.size()-1).getWord() + " ";
        }
        System.out.println(Msg);

        return msg;
    }

    private static String cleanWord(String word) {
        word = word.toLowerCase();
        //
        // Remove any punctuation
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (Character.isAlphabetic(word.charAt(i)) || word.charAt(i) == '\'') {
                newWord += word.charAt(i);
            }
        }

        if (newWord.length() > 0 && !Character.isAlphabetic(newWord.charAt(0))) {
            newWord = newWord.substring(1);
        }

        return newWord;
    }

/*    private static int myHash(String x) {
        int hashVal = x.hashCode();

        hashVal %= 101;
        if (hashVal < 0) {
            hashVal += 101;
        }

        return hashVal;
    }*/
}