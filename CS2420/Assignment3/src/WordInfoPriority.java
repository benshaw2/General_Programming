public class WordInfoPriority extends WordInfo implements Comparable<WordInfoPriority> {
    //private String word;
    //private int moves;
    //private String history;
    private int priority;

    public WordInfoPriority(String word, int moves, int estimatedWork) {
        this.word = word;
        this.moves = moves;
        this.history = word;
        this.priority = estimatedWork;
    }

    public WordInfoPriority(String word, int moves, int estimatedWork, String history) {
        this.word = word;
        this.moves = moves;
        this.history = history;
        this.priority = estimatedWork;
    }

    public int compareTo(WordInfoPriority thing){
        return Integer.compare(this.priority, thing.priority);
    }

    public String getWord() {
        return this.word;
    }

    public int getMoves() {
        return this.moves;
    }

    public String getHistory() {
        return this.history;
    }

    public int getPriority(){ return this.priority; }

    @Override
    public String toString() {
        return String.format("Word %s Moves %d : History[%s]",
                word, moves, history);
    }
}
