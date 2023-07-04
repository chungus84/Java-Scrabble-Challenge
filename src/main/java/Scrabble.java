import java.util.Arrays;
import java.util.HashMap;

public class Scrabble {

    private String word;
    private Character[] doubleLetter;
    private Character[] tripleLetter;
    private boolean doubleWord;
    private boolean tripleWord;
    private int totalScore = 0;
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
        scoreHash.put(null, 0);

    }

    public Scrabble(String userWord) {
        this.word = userWord;
        this.doubleLetter = null;
        this.tripleLetter = null;
        this.doubleWord = false;
        this.tripleWord = false;
    }

    // Scrabble (String, Character[], Character[], boolean (doubleWord), boolean(tripleWord);
    public Scrabble(String userWord, Character[] doubleLetter, Character[] tripleLetter, boolean doubleWord, boolean tripleWord) {
        this.word = userWord;
        this.doubleLetter = doubleLetter;
        this.tripleLetter = tripleLetter;
        this.doubleWord = doubleWord;
        this.tripleWord = tripleWord;
    }

    public int score() {
        if(this.validEntry(this.word)) { return totalScore; }
        wordToScoreCalc();
        checkScoreModifyers();

        return totalScore;
    }

    private int wordToScoreCalc() {
        char[] charArray = this.word.toUpperCase().toCharArray();
        for (Character ch : charArray) {
            totalScore += scoreHash.get(Character.toUpperCase(ch));
        }
        return totalScore;
    }

    private void doubleAndTripleWordCalc() {
        if(this.doubleWord) { this.totalScore *= 2;}
        if(this.tripleWord) { this.totalScore *= 3;}
    }

    private void doubleLetterScore() {
        String wordToCheck = this.word.toUpperCase();
        if (this.doubleLetter.length > 0 && wordToCheck.contains(Character.toString(this.doubleLetter[0]))) {
            totalScore += scoreHash.get(this.doubleLetter[0]);
        }



//        for(Character ch : this.doubleLetter) {
//            if (wordToCheck.contains(Character.toString(ch))) { totalScore += scoreHash.get(ch);}
//        }

    }

    private void checkScoreModifyers() {
        if (this.doubleWord || this.tripleWord) { doubleAndTripleWordCalc();}
        if (this.doubleLetter != null) { doubleLetterScore();}
    }

    private boolean validEntry(String word) {
       return (word == null || word.isEmpty());
    }

}
