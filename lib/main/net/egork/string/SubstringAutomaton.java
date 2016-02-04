package net.egork.string;

import net.egork.collections.map.Counter;
import net.egork.collections.map.Indexer;
import net.egork.graph.Graph;

import java.util.Arrays;

/**
 * @author egor@egork.net
 */
public class SubstringAutomaton {
    public int[][] edges;
    public int[] ends;

    public SubstringAutomaton(String[] words) {
        this(words, 'a', 'z');
    }

    public SubstringAutomaton(String[] words, char first, char last) {
        Indexer<Long> indexer = new Indexer<>();
        Counter<Long> fullWords = new Counter<>();
        StringHash[] hashes = new StringHash[words.length];
        int totalLength = 0;
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            hashes[i] = new SimpleStringHash(s);
            for (int j = 0; j <= s.length(); j++) {
                indexer.get(hashes[i].hash(0, j));
            }
            fullWords.add(hashes[i].hash(0));
            totalLength += s.length();
        }
        int size = indexer.size();
        edges = new int[size][last - first + 1];
        ends = new int[size];
        int[] link = new int[size];
        Arrays.fill(link, -1);
        int[] start = new int[size];
        Arrays.fill(start, -1);
        int[] next = new int[totalLength];
        int[] to = new int[totalLength];
        int index = 0;
        Graph graph = new Graph(size);
        int[] length = new int[size];
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            for (int j = 0; j <= s.length(); j++) {
                int at = indexer.get(hashes[i].hash(0, j));
                if (j != s.length()) {
                    to[index] = i;
                    next[index] = start[at];
                    start[at] = index++;
                }
                if (link[at] == -1) {
                    length[at] = j;
                    for (int k = 1; k <= j; k++) {
                        long key = hashes[i].hash(k, j);
                        if (indexer.containsKey(key)) {
                            link[at] = indexer.get(key);
                            graph.addSimpleEdge(link[at], at);
                            break;
                        }
                    }
                    for (int k = 0; k <= j; k++) {
                        ends[at] += fullWords.get(hashes[i].hash(k, j));
                    }
                }
            }
        }
        int[] queue = new int[size];
        int count = 1;
        for (int i = 0; i < count; i++) {
            int current = queue[i];
            for (int j = start[current]; j != -1; j = next[j]) {
                int at = to[j];
                edges[current][words[at].charAt(length[current]) - first] =
                        indexer.get(hashes[at].hash(0, length[current] + 1));
            }
            for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
                int at = graph.destination(j);
                queue[count++] = at;
                System.arraycopy(edges[current], 0, edges[at], 0, last - first + 1);
            }
        }
    }
}
