package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Engine {
    List<Doc> d = new ArrayList<>();
    public int loadDocs(String dirname) {
        File di = new File(dirname);
        File[] files =di.listFiles();

        int dem = 0;
        for (int i = 0 ; i < files.length;i++) {
            String q = "";
            try {
                Scanner s = new Scanner(files[i]);
                while (s.hasNextLine()) {
                    q+= s.nextLine() + "\n";
                }
            } catch (Exception e ){
                e.printStackTrace();
            }

            Doc doc = new Doc(q);
            d.add(doc);
            dem++;
        }
        return dem;
    }

    public Doc[] getDocs() {
        int i = this.d.size();
//        Doc[] docs = new Doc[i];
        return this.d.toArray(new Doc[i]);
    }

    public String htmlResult(List<Result> results) {
        int i = 0;
        int r = results.size();
        String s = "";
        while (i < r) {
            s+= results.get(i).htmlHighlight();
            i++;
        }
        return s;
    }

    public List<Result> search(Query q) {
        List<Result> n = new ArrayList<Result>();
        Doc[] docs = getDocs();
        for (Doc dc : docs) {
            List<Match> m = q.matchAgainst(dc);
            int size = m.size();
            if (size <=0) {
                break;
            }
            Result r = new Result(dc, m);
            n.add(r);
        }
        Collections.sort(n);
        return n;
    }


}
