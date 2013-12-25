package net.egork.string;

import net.egork.collections.intcollection.IntList;

import java.util.Arrays;

/**
 * @author egorku@yandex-team.ru
 */
public class SuffixAutomaton {
    public final int[] length;
    public final int[] link;
    public final int[] first;
    public final int[] next;
    public final int[] to;
    public final int[] label;
    public int size;
    public int last;
	public int edgeSize;

	public SuffixAutomaton(final CharSequence s) {
        this(new IntList() {
            @Override
            public int get(int index) {
                return s.charAt(index);
            }

            public void set(int index, int value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int size() {
                return s.length();
            }

            public void add(int value) {
                throw new UnsupportedOperationException();
            }
        });
    }

    public SuffixAutomaton(IntList s) {
        int count = s.size();
        length = new int[2 * count + 1];
        link = new int[2 * count + 1];
        first = new int[2 * count + 1];
        next = new int[4 * count];
        label = new int[4 * count];
        to = new int[4 * count];
        Arrays.fill(first, -1);
        link[0] = -1;
        size = 1;
        edgeSize = 0;
        last = 0;
        for (int i = 0; i < s.size(); i++) {
            int c = s.get(i);
            int current = size++;
            length[current] = length[last] + 1;
            for (int previous = last; ; previous = link[previous]) {
                if (previous == -1) {
                    link[current] = 0;
                    break;
                }
                int index = findEdge(previous, c);
                if (index != -1) {
                    int curLink = to[index];
                    if (length[previous] + 1 == length[curLink])
                        link[current] = curLink;
                    else {
                        int clone = size++;
                        length[clone] = length[previous] + 1;
                        link[clone] = link[curLink];
                        int linkEdge = first[curLink];
                        while (linkEdge != -1) {
                            next[edgeSize] = first[clone];
                            first[clone] = edgeSize;
                            label[edgeSize] = label[linkEdge];
                            to[edgeSize++] = to[linkEdge];
                            linkEdge = next[linkEdge];
                        }
                        for (; previous != -1; previous = link[previous]) {
                            int edge = findEdge(previous, c);
                            if (edge == -1 || to[edge] != curLink)
                                break;
                            to[edge] = clone;
                        }
                        link[current] = link[curLink] = clone;
                    }
                    break;
                }
                next[edgeSize] = first[previous];
                first[previous] = edgeSize;
                label[edgeSize] = c;
                to[edgeSize++] = current;
            }
            last = current;
        }
    }

    public int findEdge(int vertex, int label) {
        int edge = first[vertex];
        while (edge != -1) {
            if (this.label[edge] == label)
                return edge;
            edge = next[edge];
        }
        return -1;
    }
}
