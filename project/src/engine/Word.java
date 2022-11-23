package engine;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    public static Set<String> stopWords = new LinkedHashSet<>();
    private String w;

    private Word(String rawText) {
        this.w = rawText;
    }

    public static Word createWord(String rawText) {
        return new Word(rawText);
    }

    public boolean isValidWord() {
//        String t = this.w;
//        if (t.length() <= 0) {
//            return false;
//        }
//        int i = 0;
//        while (i < t.length()) {
//
//            if (Character.isDigit(t.charAt(i))) {
//                return false;
//            }
//            if (String.valueOf(t.charAt(i)).matches(".*[\'\"].*")) {
//                continue;
//            }
//            if (Character.isAlphabetic(t.charAt(i))) {
//                continue;
//            }
//            if (t.charAt(i) == '-') {
//                if (i - 1 < 0 || !Character.isAlphabetic(t.charAt(i - 1)) ||
//                        i + 1 >= t.length() || !Character.isAlphabetic(t.charAt(i + 1))) {
//                    return false;
//                }
//            } else if (i != t.length() - 1
//                    && ((t.charAt(i) == '!'
//                    || t.charAt(i) == ','
//                    || t.charAt(i) == ';'
//                    || t.charAt(i) == '.'
//                    || t.charAt(i) == '?'
//                    || t.charAt(i) == '-'
//                    || t.charAt(i) == '\''
//                    || t.charAt(i) == '\"'
//                    || t.charAt(i) == ':'
//                    || t.charAt(i) == ' '
//            ))) {
//                return false;
//            }
//            i++;
//        }
//        return true;

        if (this.w.length() <= 0) {
            return false;
        }
        int hyphen = 0;
        for (int i = 0; i < this.w.length(); i++) {
            if (Character.isDigit(this.w.charAt(i))) {
                return false;
            }
            if (String.valueOf(this.w.charAt(i)).matches(".*[\'\"].*")) {
                continue;
            }
            if (Character.isAlphabetic(this.w.charAt(i))) {
                continue;
            }

            if (this.w.charAt(i) == '-') {
                // hyphen should be surrounded
                // by letters
                if (i - 1 < 0 || !Character.isAlphabetic(this.w.charAt(i - 1)) ||
                        i + 1 >= this.w.length() || !Character.isAlphabetic(this.w.charAt(i + 1))) {
                    return false;
                }
            }

            // Punctuation must be at the
            // end of the word
            else if (i != this.w.length()- 1
                    && ((this.w.charAt(i) == '!'
                    || this.w.charAt(i) == ','
                    || this.w.charAt(i) == ';'
                    || this.w.charAt(i) == '.'
                    || this.w.charAt(i) == '?'
                    || this.w.charAt(i) == '-'
                    || this.w.charAt(i) == '\''
                    || this.w.charAt(i) == '\"'
                    || this.w.charAt(i) == ':'
                    || this.w.charAt(i) == ' '
            ))) {
                return false;
            }
        }
        return true;

    }
    public boolean isKeyword(){
        String t = this.w;
        if (stopWords.contains(t.toLowerCase())) {
            return false;
        } else {
            if (isValidWord()) {
                return true;
            }
            return false;
        }
    }


    public String getSuffix() {
        String t = this.w;
        if (isValidWord()) {
            int h = getPrefix().length();
            for(int i = h; i< t.length();i++){
                if(!Character.isLetterOrDigit(t.charAt(i))){
                    if(t.charAt(i) == '-'){
                        continue;
                    }
                    String temp = t.substring(i);
                    return temp;
                }
            }
//            int j = h;
//            while(j < t.length()){
//                if(!Character.isLetterOrDigit(t.charAt(j))){
//                    if(t.charAt(j) == '-'){
//                        continue;
//                    }
//                    return t.substring(j);
//                }
//                j++;
//            }
        }
        return "";
    }

    public String getPrefix() {
        String t = this.w;
        int s = t.indexOf(getText());
        if (isValidWord()) {
            return t.substring(0, s);
        }
        return "";
    }

    public String getText() {
        String s = this.w;
        String n = s.replaceAll("('s)[\\W]*$", "");
        Pattern pattern = Pattern.compile("([-|']*[a-zA-Z]+[-|']*)");
        Matcher matcher = pattern.matcher(n);

        String t = "";
        while (matcher.find()) {
            t += matcher.group();
        }
        int length = t.length();
        return isValidWord() && length != 0 ? t : s;
    }

    public static boolean loadStopWords(String fileName)  {
        String s = fileName;
        File file = new File(s);


        try {
            Scanner c = new Scanner(file);
            while (c.hasNextLine()) {
                String el = c.nextLine();
                stopWords.add(el);
            }
            c.close();
        } catch (Exception e) {
            return false;
        }
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()) {
//            stopWords.add(scanner.nextLine());
//        }
//        String st;
//        // Condition holds true till
//        // there is character in a string
//        while ((st = br.readLine()) != null){
////            String s = br.readLine();
//            stopWords.add(st);
//        }

            // Print the string
        return true;
    }

    public String toString() {
        return this.w;
    }
    public boolean equals(Object o) {
        Word word = (Word)o;
        String s1 = word.getText();
        String s2 = getText();
        return s1.toLowerCase().equals(s2.toLowerCase());
    }

}
