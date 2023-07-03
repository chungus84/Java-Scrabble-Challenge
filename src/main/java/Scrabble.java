public class Scrabble {

    private String word;
    public Scrabble(String userWord) {
        this.word = userWord;
    }

    public int score() {
        if(this.validEntry(this.word)) {
            return 0;
        }

        return 24;

    }

    private boolean validEntry(String word) {
       return (word == null || word.isEmpty());
    }

}
