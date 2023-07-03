import java.util.HashMap;

public class Scrabble {

    private String word;
    private static final HashMap<Character, Integer> scoreHash;

    static {
        scoreHash = new HashMap<>();
        scoreHash.put('A', 1);
        scoreHash.put('E', 1);
        scoreHash.put('I', 1);
        scoreHash.put('O', 1);
        scoreHash.put('U', 1);
        scoreHash.put('L', 1);
        scoreHash.put('N', 1);
        scoreHash.put('R', 1);
        scoreHash.put('S', 1);
        scoreHash.put('T', 1);
        scoreHash.put('D', 2);
        scoreHash.put('G', 2);
        scoreHash.put('B', 3);
        scoreHash.put('C', 3);
        scoreHash.put('M', 3);
        scoreHash.put('P', 3);
        scoreHash.put('F', 4);
        scoreHash.put('H', 4);
        scoreHash.put('V', 4);
        scoreHash.put('W', 4);
        scoreHash.put('Y', 4);
        scoreHash.put('K', 5);
        scoreHash.put('J', 8);
        scoreHash.put('X', 8);
        scoreHash.put('Q', 10);
        scoreHash.put('Z', 10);



    }

    public Scrabble(String userWord) {
        this.word = userWord;

    }


    public int score() {
        if(this.validEntry(this.word)) { return 0; }
        return wordToScoreCalc(this.word);


    }

    public int wordToScoreCalc(String word) {
        int totalScore = 0;
        char[] charArray = word.toUpperCase().toCharArray();
        for (Character ch : charArray) {
            totalScore += scoreHash.get(ch);
        }
        return totalScore;
    }

    private boolean validEntry(String word) {
       return (word == null || word.isEmpty());
    }

}
