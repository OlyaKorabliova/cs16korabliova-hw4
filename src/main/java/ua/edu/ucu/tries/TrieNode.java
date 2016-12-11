package ua.edu.ucu.tries;

/**
 * Created by Olia on 10.12.2016.
 */
class TrieNode {
    private boolean value;
    public static final int R = 256;
    private TrieNode[] next = new TrieNode[R];

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight = 0;

    public TrieNode() {

    }

    public TrieNode[] getNext() {
        return next;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    public boolean getValue() {
        return value;
    }
}
