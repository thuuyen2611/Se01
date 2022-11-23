package engine;

import java.util.ArrayList;
import java.util.List;

public class Result implements Comparable<Result> {
    private Doc d;
    private List<Match> m;

    public Result(Doc d, List<Match> matches) {
        this.d = d;
        this.m = matches;
    }

    public int getTotalFrequency() {
        int count = 0;
        for (int i = 0; i < m.size(); i++) {
            count+=m.get(i).getFreq();
        }
        return count;
    }

    public int getAverageFirstIndex() {
        int count = 0;
        for (int i = 0; i < m.size(); i++) {
            count+=m.get(i).getFirstIndex();
        }
        return count / m.size();
    }

    public String htmlHighlight() {
        List<Word> titles = new ArrayList<>(d.getTitle());
        List<Word> bodys = new ArrayList<>(d.getBody());
        String tl = "";
        String bl = "";
        boolean p = false;
        for (int i = 0; i< titles.size(); i++) {
            for (int j = 0; j < m.size(); j++) {
                if (m.get(j).getWord().equals(titles.get(i))) {
                    String pre = titles.get(i).getPrefix();
                    String wt = titles.get(i).getText();
                    String suff = titles.get(i).getSuffix();
                    tl += pre + "<u>" + wt + "</u>" + suff + " ";
                    p = true;
                }
            }
            if (p) {
                p = false;
                continue;
            }
            tl += titles.get(i) + " ";
        }
        tl = "<h3>" + tl.trim() + "</h3>";


        // body part
        int e = 0;
        boolean q = false;
        for (int i = 0; i< bodys.size(); i++) {
            for (int j = 0; j < m.size(); j++) {
                if (m.get(j).getWord().equals(bodys.get(i))) {
                    String pre = bodys.get(i).getPrefix();
                    String wt = bodys.get(i).getText();
                    String suff = bodys.get(i).getSuffix();
                    bl += pre + "<b>" + wt + "</b>" + suff + " ";
                    q = true;
                }
            }

            if (q) {
                q = false;
                continue;
            }
            bl += bodys.get(i) + " ";
        }
        bl = "<p>" + bl.trim() + "</p>";
        String temp = tl + bl;
        return temp;
     }

     public Doc getDoc(){
        return this.d;
     }



     public int compareTo(Result o){
        if (this.getMatches().size() > o.getMatches().size()) {
            return -1;
        } else if(this.getMatches().size() == o.getMatches().size()) {
            if (this.getTotalFrequency() > o.getTotalFrequency()) {
                return -1;
            } else if (this.getTotalFrequency() == o.getTotalFrequency()) {
                if (this.getAverageFirstIndex() > o.getAverageFirstIndex()) {
                    return -1;
                } else if (this.getAverageFirstIndex() == o.getAverageFirstIndex()) {
                    return 0;
                }
            }
        }
        return 1;
     }

    public List<Match> getMatches() {
        return this.m;
    }

}
