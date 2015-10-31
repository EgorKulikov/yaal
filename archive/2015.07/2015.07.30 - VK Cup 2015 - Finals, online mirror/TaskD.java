package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskD {
    static class Segment implements Comparable<Segment> {
        int from;
        int to;
        Segment parent;

        public Segment(int from, int to) {
            this.from = from;
            this.to = to;
            parent = this;
        }

        Segment get() {
            if (parent == this) {
                return this;
            }
            return parent = parent.get();
        }

        static void join(Segment a, Segment b) {
            a = a.get();
            b = b.get();
            a.parent = b;
        }

        @Override
        public int compareTo(Segment o) {
            if (from != o.from) {
                return Integer.compare(from, o.from);
            }
            return Integer.compare(to, o.to);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        NavigableSet<Segment> segments = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            segments.add(new Segment(i, i));
        }
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            int first = in.readInt() - 1;
            int second = in.readInt() - 1;
            if (type == 1) {
                Segment.join(segments.lower(new Segment(first, Integer.MAX_VALUE)), segments.lower(new Segment(second, Integer.MAX_VALUE)));
            } else if (type == 2) {
                Segment from = segments.lower(new Segment(first, Integer.MAX_VALUE));
                Segment to = segments.lower(new Segment(second, Integer.MAX_VALUE));
                if (from == to) {
                    continue;
                }
                Segment segment = new Segment(from.from, to.to);
                NavigableSet<Segment> subSet = segments.subSet(from, true, to, true);
                for (Iterator<Segment> iterator = subSet.iterator(); iterator.hasNext();) {
                    Segment subSegment = iterator.next();
                    Segment.join(subSegment, segment);
                    iterator.remove();
                }
                segments.add(segment);
            } else if (type == 3) {
                Segment from = segments.lower(new Segment(first, Integer.MAX_VALUE));
                Segment to = segments.lower(new Segment(second, Integer.MAX_VALUE));
                if (from.get() == to.get()) {
                    out.printLine("YES");
                } else {
                    out.printLine("NO");
                }
            }
        }
    }
}
