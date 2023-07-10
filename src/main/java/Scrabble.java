import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class Scrabble {

    private ArrayList<String> word;
    private final Character[] doubleLetter;
    private final Character[] tripleLetter;
    private final boolean doubleWord;
    private final boolean tripleWord;
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
    }

    public Scrabble(String userWord) {
        this.word = this.convertWordToArrayList(userWord);
        this.doubleLetter = null;
        this.tripleLetter = null;
        this.doubleWord = false;
        this.tripleWord = false;
    }

    public Scrabble(String userWord, Character[] doubleLetter, Character[] tripleLetter, boolean doubleWord, boolean tripleWord) {
        this.word = this.convertWordToArrayList(userWord) ;
        this.doubleLetter = doubleLetter;
        this.tripleLetter = tripleLetter;
        this.doubleWord = doubleWord;
        this.tripleWord = tripleWord;
    }

    private ArrayList<String> convertWordToArrayList(String word) {
        if (word == null || word == "") { return null;}
        String[] wordArr = word.toUpperCase().split("");
        return new ArrayList<>(Arrays.asList(wordArr));
    }

    public int score() {
        if(this.invalidEntry()) { return totalScore; }
        wordToScoreCalc();
        checkScoreModifyers();
        return totalScore;
    }

    private void wordToScoreCalc() {;
        for (String letter : this.word) {
            totalScore += scoreHash.get(letter.charAt(0));
        }
    }

    private void doubleAndTripleWordCalc() {
        if(this.doubleWord) { this.totalScore *= 2;}
        if(this.tripleWord) { this.totalScore *= 3;}
    }

    private int addScoreForDoubleAndTripleLetters(Character[] letterArray) {
        int modifyScore = 0;
        for (Object letter : letterArray) {
            modifyScore += checkLetterInWordAndReturnScore(letter);
            this.word.remove(letter);
            }
        return modifyScore;
    }

    private int checkLetterInWordAndReturnScore(Object letter) {
        if(this.word.contains(letter.toString())) {
            return scoreHash.get(letter);
        }
        return 0;
    }

    private void doubleAndTripleLetterScore() {
        if (this.doubleLetter.length > 0) {
            totalScore += this.addScoreForDoubleAndTripleLetters(this.doubleLetter);
        }
        if (this.tripleLetter.length > 0) {
            totalScore += ((this.addScoreForDoubleAndTripleLetters(this.tripleLetter)) * 2 );
        }
    }

    private void checkScoreModifyers() {
        if (this.doubleWord || this.tripleWord) { doubleAndTripleWordCalc();}
        if (this.doubleLetter != null || this.tripleLetter != null) { doubleAndTripleLetterScore();}
    }

    private boolean invalidEntry() {
       return (this.word == null || this.word.size() == 0);
    }

    public static void main(String[] args) {

        Scrabble test2DoubleLettersShouldReturnScoreOf8 = new Scrabble("mean", new Character[]{'E','A'}, new Character[]{}, false, false);
        System.out.println("word mean with 'e' and 'a' being double letter scores returns a score of: " + test2DoubleLettersShouldReturnScoreOf8.score());

        Scrabble test2TripleLettersShouldReturnScoreOf10 = new Scrabble("mean", new Character[]{}, new Character[]{'E','A'}, false, false);
        System.out.println("word mean with 'e' and 'a' being triple letter scores returns a score of: " + test2TripleLettersShouldReturnScoreOf10.score());
    }

}
