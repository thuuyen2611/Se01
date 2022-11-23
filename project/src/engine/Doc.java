package engine;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> titles = new ArrayList<>();
    private List<Word> bodys = new ArrayList<>();

    public Doc(String content) {
        String[] h = content.split("\n");
        String[] e = h[0].split(" ");
        int p = 0;
        while (p<e.length) {
            titles.add(
                    Word.createWord(e[p])
            );
            p++;
        }

        int q = 0;
        String[] f = h[1].split(" ");
        while(q<f.length){
            bodys.add( Word.createWord(f[q])
            );
            q++;
        }
    }


    public List<Word> getTitle() {
        return this.titles;
    }

    public boolean equals(Object o) {
        boolean t1 = true, t2 = true;
        Doc d = (Doc) o;
        int i = 0;
        while (i < titles.size()) {
            if (this.titles.get(i).equals(d.getTitle().get(i))){
                t1 = true;
            } else {
                t2 = false;
            }
            i++;
        }

        int j = 0;
        while (j < bodys.size()) {
            if (this.bodys.get(j).equals(d.getTitle().get(j))){
                t2 = true;
            } else {
                t2 = false;
            }
            j++;
        }


        if (!(t1&&t2)) {
            return false;
        }
        return true;
    }

    public List<Word> getBody() {
        return this.bodys;
    }


}
