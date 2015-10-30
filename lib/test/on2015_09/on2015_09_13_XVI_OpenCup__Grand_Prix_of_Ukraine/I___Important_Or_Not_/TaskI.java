package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.I___Important_Or_Not_;



import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TaskI {
    private int[][] up;
    int[] height;

    int goUp(int vertex, int to) {
        for (int k = 19; k >= 0; k--) {
            if (height[up[k][vertex]] >= to) {
                vertex = up[k][vertex];
            }
        }
        return vertex;
    }

    NavigableMap<Integer, Integer>[] sets;

    void init(int root, int left, int right) {
        sets[root] = new TreeMap<>();
        if (left != right) {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
        }
    }

    void add(NavigableMap<Integer, Integer> map, int value) {
        Integer x = map.get(value);
        if (x != null) {
            map.put(value, x + 1);
        } else {
            map.put(value, 1);
        }
    }

    void remove(NavigableMap<Integer, Integer> map, int value) {
        Integer x = map.get(value);
        if (x == 1) {
            map.remove(value);
        } else {
            map.put(value, x - 1);
        }
    }

    boolean add(int root, int left, int right, int at, int what) {
        if (left > at || right < at) {
            return false;
        }
        if (left == right) {
            if (sets[root].containsKey(what)) {
                return true;
            } else {
                add(sets[root], what);
                return false;
            }
        }
        int middle = (left + right) >> 1;
        if (!add(2 * root + 1, left, middle, at, what) && !add(2 * root + 2, middle + 1, right, at, what)) {
            add(sets[root], what);
            return false;
        }
        return true;
    }

    boolean remove(int root, int left, int right, int at, int what) {
        if (left > at || right < at) {
            return false;
        }
        if (left == right) {
            if (sets[root].containsKey(what)) {
                remove(sets[root], what);
                return true;
            } else {
                return false;
            }
        }
        int middle = (left + right) >> 1;
        if (remove(2 * root + 1, left, middle, at, what) || remove(2 * root + 2, middle + 1, right, at, what)) {
            remove(sets[root], what);
            return true;
        }
        return false;
    }

    int[] candidates = new int[50];
    int[] next = new int[50];

    void get(int root, int left, int right, int from, int to, int start, int qty) {
        if (left > to || right < from) {
            return;
        }
        if (left >= from && right <= to) {
            merge(sets[root].tailMap(start, true), qty);
            return;
        }
        int middle = (left + right) >> 1;
        get(2 * root + 1, left, middle, from, to, start, qty);
        get(2 * root + 2, middle + 1, right, from, to, start, qty);
    }

    private void merge(NavigableMap<Integer, Integer> map, int qty) {
        int at = 0;
        int i = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (at < qty) {
            Map.Entry<Integer, Integer> entry = iterator.hasNext() ? iterator.next() : null;
            if (entry == null) {
                System.arraycopy(candidates, i, next, at, qty - at);
                at = qty;
            } else {
                while (at < qty && candidates[i] <= entry.getKey()) {
                    next[at++] = candidates[i++];
                }
                for (int j = 0; j < entry.getValue() && at < qty; j++) {
                    next[at++] = entry.getKey();
                }
            }
        }
        int[] temp = candidates;
        candidates = next;
        next = temp;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        SuffixAutomaton automaton = new SuffixAutomaton(s);
        Graph graph = new Graph(automaton.size, automaton.size - 1);
        for (int i = 1; i < automaton.size; i++) {
            graph.addWeightedEdge(automaton.link[i], i, automaton.length[i] - automaton.length[automaton.link[i]]);
        }
        DFSOrder order = new DFSOrder(graph);
        height = automaton.length.clone();
        int[] ord = ArrayUtils.reversePermutation(order.position);
        int[] prefix = new int[s.length()];
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            current = automaton.to[automaton.findEdge(current, s.charAt(i))];
            prefix[i] = current;
        }
        up = new int[20][automaton.size];
        for (int i = 0; i < automaton.size; i++) {
            if (i != 0) {
                up[0][i] = automaton.link[i];
            }
        }
        for (int i = 1; i < 20; i++) {
            for (int j = 0; j < automaton.size; j++) {
                up[i][j] = up[i - 1][up[i - 1][j]];
            }
        }
        sets = new NavigableMap[4 * automaton.size];
        init(0, 0, automaton.size - 1);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            if (type <= 2) {
                int x = in.readInt();
                int p = in.readInt();
                int where = goUp(prefix[p], x);
                if (type == 1) {
                    add(0, 0, automaton.size - 1, order.position[where], x);
                } else {
                    remove(0, 0, automaton.size - 1, order.position[where], x);
                }
            } else {
                int x = in.readInt();
                int p = in.readInt();
                int qty = in.readInt();
                Arrays.fill(candidates, 0, qty, Integer.MAX_VALUE);
                int where = goUp(prefix[p], x);
                get(0, 0, automaton.size - 1, order.position[where], order.end[where], x, qty);
                if (candidates[qty - 1] == Integer.MAX_VALUE) {
                    out.printLine(-1);
                } else {
                    out.printLine(candidates[qty - 1]);
                }
            }
        }
    }
}
