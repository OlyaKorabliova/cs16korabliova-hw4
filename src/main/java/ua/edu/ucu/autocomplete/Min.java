package ua.edu.ucu.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Olia on 09.12.2016.
 */
public class Min {
    public HashSet<String> dictionary = new HashSet<>();
    public HashMap<Integer,HashSet<String>> len = new HashMap<>();
    public void load(String... strings) {
        for (String s : strings) {
            String[] str = s.split(" ");
            for (String j : str) {
                if (j.length() > 2) {
                    dictionary.add(j);
                }
            }
        }
//        System.out.println(dictionary.toString());

    }

    public boolean contains(String word) {
        if (dictionary.contains(word)) {
            return true;
        }
        return false;
    }

    public boolean delete(String word) {
        if (contains(word)) {
            dictionary.remove(word);
//            System.out.println(this);
            return true;
        }
        return false;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        len.clear();
        if (pref.length() >= 2) {
            for (String s : dictionary) {
                if (s.toLowerCase().startsWith(pref.toLowerCase())) {
                    int key = s.length();
                    if (!len.containsKey(key)) {
                        HashSet<String> arr = new HashSet<>();
                        arr.add(s);
                        len.put(key, arr);
                    }
                    else {
                        len.get(key).add(s);
                    }
                }
            }
        }
        ArrayList arr = new ArrayList();
        for (int i : len.keySet()) {
            arr.addAll(len.get(i));
        }

        return arr;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        HashMap<Integer, ArrayList<String>> result = new HashMap<>();
        wordsWithPrefix(pref);
        if (!len.isEmpty()) {
            if (pref.length() > 2) {
                for (int i = pref.length(); i <= Collections.max(len.keySet()); i++) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (len.containsKey(i) && result.size() < k) {
                        arrayList.addAll(len.get(i));
                        result.put(i, arrayList);
                    }
                }

            } else if (pref.length() == 2) {
                for (int i = 3; i <= Collections.max(len.keySet()); i++) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (len.containsKey(i) && result.size() < k) {
                        arrayList.addAll(len.get(i));
                        result.put(i, arrayList);
                    }
                }
            } else {
                throw new UnsupportedOperationException("Prefix length should be more than 1");
            }
        }
        else {
            throw new UnsupportedOperationException("No word with prefix '" + pref + "'");
        }
        ArrayList arr = new ArrayList();
        for (int i : result.keySet()) {
            arr.addAll(result.get(i));
        }

        return arr;
    }

    public String toString() {
        return dictionary.toString();
    }

    public static void main(String args[]) {
//        Min pr = new Min();
//        pr.load(new String[]{"kaka", "sadf", "d ds", ""});
//        pr.load("kaka jdfa kfas");
//        pr.load("FDsdfs");
//        pr.load("kaka");
//        pr.load(new String[]{"Kadada", "jsa", "kak", "kare"});
//        pr.wordsWithPrefix("kak");
//        pr.wordsWithPrefix("ka");
//        pr.wordsWithPrefix("ka", 1);



        Min m = new Min();
        m.load(new String[]{"kak", "ka", "kakadu", "Kanada", "kappa", "karp", "Kalin", "kal", "kabak", "Karpinskiy", "kanapka"});
        m.wordsWithPrefix("ka", 1);
        m.wordsWithPrefix("ka", 4);
        m.wordsWithPrefix("ka", 2);
        m.wordsWithPrefix("ka", 0);
        m.wordsWithPrefix("karpi", 1);
        System.out.println(m.wordsWithPrefix("kak", 2));


    }
}
