package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.max;

public class TaskC {
    static class Variant {
        long aFrom;
        long aTo;
        long bFrom;
        long bTo;

        public Variant(long aFrom, long aTo, long bFrom, long bTo) {
            this.aFrom = aFrom;
            this.aTo = aTo;
            this.bFrom = bFrom;
            this.bTo = bTo;
        }

        double score() {
            return (double)(aTo - aFrom + 1) * (bTo - bFrom + 1);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        List<Variant> variants = new ArrayList<>();
        variants.add(new Variant(1, n, 1, n));
        while (true) {
            double best = POSITIVE_INFINITY;
            Variant variant = null;
            for (Variant v : variants) {
                long x = v.aFrom + ((v.aTo - v.aFrom) / 3);
                long y = v.bFrom + ((v.bTo - v.bFrom) / 3);
                double total1 = 2 * v.score() / 3;
                double total2 = 2 * v.score() / 3;
                double total3 = 2 * v.score() / 3;
                for (Variant vv : variants) {
                    if (v == vv) {
                        continue;
                    }
                    if (vv.aTo > x) {
                        if (vv.aFrom > x) {
                            total1 += vv.score();
                        } else {
                            total1 += (double)(vv.aTo - x) * (vv.bTo - vv.bFrom + 1);
                        }
                    }
                    if (vv.bTo > y) {
                        if (vv.bFrom > y) {
                            total2 += vv.score();
                        } else {
                            total2 += (double)(vv.bTo - y) * (vv.aTo - vv.aFrom + 1);
                        }
                    }
                    if (x > vv.aTo || y > vv.bTo) {
                        total3 += vv.score();
                    } else if (x > v.aFrom) {
                        total3 += (double)(x - vv.aFrom) * (vv.bTo - vv.bFrom + 1);
                    } else if (y > v.bFrom) {
                        total3 += (double)(y - vv.bFrom) * (vv.aTo - vv.aFrom + 1);
                    }
                }
                double value = max(total1, max(total2, total3));
                if (value < best) {
                    variant = v;
                    best = value;
                }
            }
            long x = variant.aFrom + ((variant.aTo - variant.aFrom) / 3);
            long y = variant.bFrom + ((variant.bTo - variant.bFrom) / 3);
            out.printLine(x, y);
            out.flush();
            int answer = in.readInt();
            if (answer == 0) {
                return;
            }
            List<Variant> next = new ArrayList<>();
            int num = 0;
            for (Variant v : variants) {
                if (answer == 1) {
                    if (v.aTo <= x) {
                        continue;
                    }
                    v.aFrom = max(v.aFrom, x + 1);
                    next.add(v);
                } else if (answer == 2) {
                    if (v.bTo <= y) {
                        continue;
                    }
                    v.bFrom = max(v.bFrom, y + 1);
                    next.add(v);
                } else {
                    if (x <= v.aFrom && y <= v.bFrom) {
                        continue;
                    }
                    if (x > v.aTo || y > v.bTo) {
                        next.add(v);
                        continue;
                    }
                    int all = 0;
                    if (x > v.aFrom) {
                        next.add(new Variant(v.aFrom, x - 1, max(y, v.bFrom), v.bTo));
                        all++;
                    }
                    if (y > v.bFrom) {
                        next.add(new Variant(max(x, v.aFrom), v.aTo, v.bFrom, y - 1));
                        all++;
                    }
                    if (x > v.aFrom && y > v.bFrom) {
                        next.add(new Variant(v.aFrom, x - 1, v.bFrom, y - 1));
                        all++;
                    }
                    if (all == 3) {
                        num++;
                    }
                }
            }
            variants = next;
        }
    }
}
