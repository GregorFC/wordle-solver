public class Trie {
    public Trie[] children;
    public boolean isWord;

    public Trie() {
        children = new Trie[26];
        isWord = false;
    }


    public void insert(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) curr.children[c - 'a'] = new Trie();
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr.isWord;
    }
}
