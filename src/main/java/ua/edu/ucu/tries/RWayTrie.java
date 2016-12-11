package ua.edu.ucu.tries;

public class RWayTrie implements Trie {
    private TrieNode root = new TrieNode();

    @Override
    public void add(Tuple t) {
        String word = t.term;
        root = insert(root, word, 0);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TrieNode insert(TrieNode node, String word, int d) {
        if (node == null) {
            node = new TrieNode();
        }
        if (d == word.length()) {
            node.setValue(true);
            return node;
        }
        char c = word.charAt(d);
        node.getNext()[c] = insert(node.getNext()[c], word, d + 1);
        return node;
    }

    @Override
    public boolean contains(String word) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> words() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
