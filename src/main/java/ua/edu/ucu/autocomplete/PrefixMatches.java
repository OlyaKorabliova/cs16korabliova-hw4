package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author andrii
 */
public class PrefixMatches {
    public HashSet<String> dictionary = new HashSet<>();
    private Trie trie;
    public HashMap<Integer, HashSet<String>> dictOfSearchedWords = new HashMap<>();

    public PrefixMatches(Trie trie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int load(String... strings) {
        int counter = 0;
        for (String s : strings) {
            String[] str = s.split(" ");
            for (String j : str) {
                if (j.length() > 2) {
                    dictionary.add(j);
                    counter++;
                }
            }
        }
        System.out.println(dictionary.toString());

        return counter;
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
            System.out.println(this);
            return true;
        }
        return false;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        dictOfSearchedWords.clear();
        if (pref.length() >= 2) {
            for (String s : dictionary) {
                if (s.toLowerCase().startsWith(pref.toLowerCase())) {
                    int key = s.length();
                    if (!dictOfSearchedWords.containsKey(key)) {
                        HashSet<String> arr = new HashSet<>();
                        arr.add(s);
                        dictOfSearchedWords.put(key, arr);
                    }
                    else dictOfSearchedWords.get(key).add(s);
                }
            }
        }
        else throw new UnsupportedOperationException("Prefix length should be bigger than 1!");
        if (dictOfSearchedWords.isEmpty()) throw new UnsupportedOperationException("No word with prefix '" + pref + "'");
        ArrayList arr = new ArrayList();
        for (int i : dictOfSearchedWords.keySet()) {
            arr.addAll(dictOfSearchedWords.get(i));
        }

        return arr;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        HashMap<Integer, ArrayList<String>> result = new HashMap<>();
        wordsWithPrefix(pref);
        if (!dictOfSearchedWords.isEmpty()) {
            if (pref.length() > 2) {
                for (int i = pref.length(); i <= Collections.max(dictOfSearchedWords.keySet()); i++) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (dictOfSearchedWords.containsKey(i) && result.size() < k) {
                        arrayList.addAll(dictOfSearchedWords.get(i));
                        result.put(i, arrayList);
                    }
                }

            } else if (pref.length() == 2) {
                for (int i = 3; i <= Collections.max(dictOfSearchedWords.keySet()); i++) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (dictOfSearchedWords.containsKey(i) && result.size() < k) {
                        arrayList.addAll(dictOfSearchedWords.get(i));
                        result.put(i, arrayList);
                    }
                }
            }
            else throw new UnsupportedOperationException("Prefix length should be bigger than 1!");
        }
        else throw new UnsupportedOperationException("No word with prefix '" + pref + "'");

        ArrayList arr = new ArrayList();
        for (int i : result.keySet()) {
            arr.addAll(result.get(i));
        }

        return arr;
    }

    public String toString() {
        return dictionary.toString();
    }

    public int size() {
        return dictionary.size();
    }
}
