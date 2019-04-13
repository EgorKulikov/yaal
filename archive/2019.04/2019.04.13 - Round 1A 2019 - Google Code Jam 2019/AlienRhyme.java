package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.string.Trie;

import static net.egork.string.StringUtils.reverse;

public class AlienRhyme {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] w = in.readStringArray(n);
        for (int i = 0; i < n; i++) {
            w[i] = reverse(w[i]);
        }
        Trie trie = new Trie();
        for (String s : w) {
            trie.add(s);
        }
        Trie.Node root = trie.root;
        int answer = n;
        for (int i = 0; i < root.links.length; i++) {
            answer -= go(root.links[i]);
        }
        out.printLine("Case #" + testNumber + ":", answer);
    }

    private int go(Trie.Node node) {
        if (node == null) {
            return 0;
        }
        int result = 0;
        if (node.leaf) {
            result++;
        }
        for (int i = 0; i < node.links.length; i++) {
            result += go(node.links[i]);
        }
        if (result >= 2) {
            result -= 2;
        }
        return result;
    }
}
