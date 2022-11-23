package engine;

public class Match implements Comparable<Match>{
    private Doc doc;
    private Word word;
    private int fq;
    public int fi;

    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.doc =  d;
        this.word = w;
        this.fq = freq;
        this.fi = firstIndex;
    }
    public int compareTo(Match o) {
        if (this.getFirstIndex() < o.getFirstIndex()) {
            return -1;
        } else if (this.getFirstIndex() > o.getFirstIndex()) {
            return 1;
        }
        return 0;
    }


    public Word getWord() {
        return this.word;
    }
    public int getFirstIndex()  {
        return this.fi;
    }
    public int getFreq() {
        return this.fq;
    }


}