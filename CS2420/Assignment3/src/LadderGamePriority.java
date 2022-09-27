import java.io.File;
//import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
//check the estimated work parameter.
public class LadderGamePriority extends LadderGame {
    ArrayList<ArrayList<String>> AllWords = new ArrayList<>();

    public LadderGamePriority(String dictionaryFile) { readDictionary(dictionaryFile);}

    public void play(String start, String end) {
        // TODO: Write some good stuff here
        //String msg = start + " " + "-> " + end + " : ";
        String msg = "Seeking A* solution from " + start + " -> " + end + "\n";

        if(start.length() != end.length()){
            System.out.println(msg + "The starting and ending words must have the same length.");
            return;
        }

        ArrayList<String> backup = new ArrayList<>();
        for(String Wrd: AllWords.get(start.length()-1)){
            backup.add(Wrd);
        }

        if(!backup.contains(start) || !backup.contains(end)){
            System.out.println(msg + "The starting and ending words must be in the dictionary.");
            return;
        }

        AVLTree<WordInfoPriority> parQ = new AVLTree<>();
        WordInfoPriority Start = new WordInfoPriority(start,0,estimateWork(start,end,0));
        parQ.insert(Start);

        boolean complete = false;
        int EQ = 0;

        while(!parQ.isEmpty() && !complete){
            WordInfoPriority shortest = parQ.deleteMin();
            ArrayList<String> OneAway = oneAway(shortest.getWord(), true);
            for(String wrd: OneAway){
                int Moves = 1;
                for(char t: shortest.getHistory().toCharArray()){
                    if(t == ' '){
                        Moves += 1;
                    }
                }
                if(Objects.equals(wrd, end)){
                    complete = true;
                    WordInfoPriority doneLad = new WordInfoPriority(wrd, Moves, estimateWork(wrd,end,Moves), shortest.getHistory()+ " " + wrd);
                    System.out.println(msg + " [" + doneLad.getHistory() + "] total enqueues " + EQ);
                }
                else{
                    WordInfoPriority newLad = new WordInfoPriority(wrd,Moves, estimateWork(wrd,end,Moves), shortest.getHistory()+ " " + wrd); //Changed formal parameter moves from 0 to Moves.
                    parQ.insert(newLad);
                    EQ += 1;
                }
            }
        }
        if(!complete){
            System.out.println(msg + "No ladder was found");
        }
        AllWords.get(start.length()-1).clear();
        for(String Wrd: backup){
            AllWords.get(start.length()-1).add(Wrd);
        }

    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();

        // TODO: Write some good stuff here
        int wordLength = word.length();
        ArrayList<String> sameLen = AllWords.get(wordLength-1);
        int numWords = sameLen.size();

        ArrayList<String> letters = new ArrayList<>();
        for(int i=0; i< wordLength; i++){
            letters.add(Character.toString(word.charAt(i)));
        }

        for(int i=0; i<numWords; i++){
            String currWord = sameLen.get(i);
            ArrayList<String> currLetters = new ArrayList<>();
            for(int j=0; j< currWord.length(); j++){
                currLetters.add(Character.toString(currWord.charAt(j)));
            }
            int matchCount = 0;
            for(int j=0; j< currLetters.size(); j++){
                if(Objects.equals(currLetters.get(j), letters.get(j))){
                    matchCount += 1;
                }
            }
            if(matchCount == wordLength - 1){
                words.add(currWord);
            }

        }

        if(withRemoval){
            for(String Word: words){
                AllWords.get(wordLength-1).remove(Word);
            }
        }

        return words;
    }

    public void listWords(int length, int howMany) {
        // TODO: Write some good stuff here
        for(int i=0; i< howMany; i++){
            System.out.println(AllWords.get(length-1).get(i));
        }
    }

    public int estimateWork(String word, String finalWord, int ladderLength){
        char[] input = word.toCharArray();
        char[] end = finalWord.toCharArray();
        int numWrong = 0;
        for (int i=0; i < input.length; i++){
            if (input[i] != end[i]){
                    numWrong++;
            }
        }
        return numWrong + ladderLength;
    }

    /*
        Reads a list of words from a file, putting all words of the same length into the same array.
     */
    private void readDictionary(String dictionaryFile) { //This was changed from void: we're constructing an arraylist and need to return it.
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

        //
        // Track the longest word, because that tells us how big to make the array.
        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
            //
            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }

            // TODO: You need to do something here to organize the words into groups/arrays of words with the same size

            for(int i = 1; i<=longestWord; i++){
                ArrayList<String> sublist = new ArrayList<>();
                AllWords.add(sublist);
            }
            for(int j = 0; j< allWords.size(); j++){
                String word = allWords.get(j);
                int len = word.length();
                (AllWords.get(len - 1)).add(word);
            }

        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
    }
}