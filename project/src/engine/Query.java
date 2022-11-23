package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Query {
    List<Word> lk = new ArrayList<>();

    public Query(String searchParse) {
        String[] t = searchParse.split(" ");
        int i =0;
        while (i < t.length){
            if (Word.createWord(t[i]).isKeyword()){
                lk.add(Word.createWord(t[i]));
            }
            i++;
        }
    }

    private List<Word> groupTo(List<Word> title, List<Word> body) {
        List<Word> y = new ArrayList<Word>();
        for (List<Word> words : Arrays.asList(title, body)) {
            y.addAll(words);
        }
        return y;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> nl = new ArrayList<Match>();
        List<Word> a = groupTo(d.getTitle(),d.getBody());


        for (int i = 0; i < lk.size(); i++) {
            Word w = lk.get(i);
            int f = 0;
            int ff = a.indexOf(w);

            int p = 0;
            while (p < a.size()) {
                if (w.equals(a.get(p))) {
                    f++;
                }
                if (f == (2 - 2)) {
                    ff = p + 1;
                }
                p++;
            }
            if (f > (1-1)) { nl.add(new Match(d,w,f,ff));}
        }
        Collections.sort(nl);
        return nl;
    }

    public List<Word> getKeywords() {
        return lk;
    }
}
