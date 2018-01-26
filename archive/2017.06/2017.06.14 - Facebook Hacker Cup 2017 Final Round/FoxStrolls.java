package net.egork;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.asLong;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.sort;

public class FoxStrolls {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[] h;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                h = readIntArray(in, n);
            }

            long sumLength;
            long numBridges;

            @Override
            public void solve() {
                for (int i = 0; i < n; i++) {
                    sumLength += (long)i * (i + 1) / 2;
                }
                int[] order = createOrder(n);
                sort(order, (a, b) -> h[a] != h[b] ? h[a] - h[b] : a - b);
                SumIntervalTree tree = new SumIntervalTree(asLong(h));
                NavigableSet<Integer> processed = new TreeSet<>();
                boolean[] bridgeToLeft = new boolean[n];
                boolean[] bridgeToRight = new boolean[n];
                for (int i : order) {
                    Integer left = processed.lower(i);
                    Integer right = processed.higher(i);
                    if (left != null) {
                        if (!bridgeToRight[left]) {
                            numBridges += i - left;
                            bridgeToRight[left] = true;
                            bridgeToLeft[i] = true;
                        } else if (h[i] == h[left]) {
                            bridgeToLeft[i] = true;
                            bridgeToRight[i] = true;
                        }
                    } else {
                        left = -1;
                    }
                    if (right != null) {
                        if (!bridgeToLeft[right]) {
                            bridgeToLeft[right] = true;
                            bridgeToRight[i] = true;
                            numBridges += right - i;
                        } else if (h[i] == h[right]) {
                            bridgeToLeft[i] = true;
                            bridgeToRight[i] = true;
                        }
                    } else {
                        right = n;
                    }
                    long toRight = right - i - 1L;
                    long sumRight = tree.query(i + 1, right - 1) - toRight * h[i];
                    long toLeft = i - left - 1L;
                    long sumLeft = tree.query(left + 1, i - 1) - toLeft * h[i];
                    sumLength += sumLeft * (toRight + 1) + sumRight * (toLeft + 1);
                    processed.add(i);
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", sumLength, numBridges);
            }
        }, 4);
    }
}
