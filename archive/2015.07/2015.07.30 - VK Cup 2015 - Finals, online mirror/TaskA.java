package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    int answer;
    int[] corresponding;
    boolean[] usedFirst;
    boolean[] usedSecond;

    class Node {
        Node[] children;
        int depth;
        IntList first = new IntArrayList(1);
        IntList second = new IntArrayList(1);

        void add(String s, int index, boolean isFirst) {
            if (isFirst) {
                first.add(index);
            } else {
                second.add(index);
            }
            if (depth < s.length()) {
                int cur = s.charAt(depth) - 'a';
                if (children == null) {
                    children = new Node[26];
                }
                if (children[cur] == null) {
                    children[cur] = new Node();
                    children[cur].depth = depth + 1;
                }
                children[cur].add(s, index, isFirst);
            }
        }

        void process() {
            if (first.size() == 0 || second.size() == 0) {
                return;
            }
            if (children != null) {
                for (Node child : children) {
                    if (child != null) {
                        child.process();
                    }
                }
            }
            int i = 0;
            int j = 0;
            while (i < first.size() && j < second.size()) {
                while (i < first.size() && usedFirst[first.get(i)]) {
                    i++;
                }
                if (i == first.size()) {
                    break;
                }
                while (j < second.size() && usedSecond[second.get(j)]) {
                    j++;
                }
                if (j == second.size()) {
                    break;
                }
                corresponding[first.get(i)] = second.get(j);
                usedFirst[first.get(i)] = true;
                usedSecond[second.get(j)] = true;
                i++;
                j++;
                answer += depth;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] names = IOUtils.readStringArray(in, n);
        String[] pseudos = IOUtils.readStringArray(in, n);
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            root.add(names[i], i, true);
            root.add(pseudos[i], i, false);
        }
        usedFirst = new boolean[n];
        usedSecond = new boolean[n];
        corresponding = new int[n];
        root.process();
        out.printLine(answer);
        for (int i = 0; i < n; i++) {
            out.printLine(i + 1, corresponding[i] + 1);
        }
    }
}
