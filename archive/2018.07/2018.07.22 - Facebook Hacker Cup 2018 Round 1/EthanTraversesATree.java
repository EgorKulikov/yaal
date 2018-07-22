package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class EthanTraversesATree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int k;
            int[] a;
            int[] b;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                k = in.readInt();
                a = new int[n];
                b = new int[n];
                in.readIntArrays(a, b);
            }

            int[] answer;

            @Override
            public void solve() {
                decreaseByOne(a, b);
                int[] pre = new int[n];
                preOrder(0, 0, pre);
                int[] post = new int[n];
                postOrder(0, 0, post);
                IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
                for (int i = 0; i < n; i++) {
                    setSystem.join(pre[i], post[i]);
                }
                if (setSystem.getSetCount() < k) {
                    return;
                }
                answer = new int[n];
                int next = 1;
                for (int i = 0; i < n; i++) {
                    if (answer[i] == 0) {
                        for (int j = 0; j < n; j++) {
                            if (setSystem.get(j) == setSystem.get(i)) {
                                answer[j] = next;
                            }
                        }
                        next++;
                        next = Math.min(next, k);
                    }
                }
            }

            private int preOrder(int vertex, int at, int[] pre) {
                if (vertex == -1) {
                    return at;
                }
                pre[at++] = vertex;
                at = preOrder(a[vertex], at, pre);
                return preOrder(b[vertex], at, pre);
            }

            private int postOrder(int vertex, int at, int[] post) {
                if (vertex == -1) {
                    return at;
                }
                at = postOrder(a[vertex], at, post);
                at = postOrder(b[vertex], at, post);
                post[at++] = vertex;
                return at;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.print("Case #" + testNumber + ": ");
                if (answer == null) {
                    out.printLine("Impossible");
                } else {
                    out.printLine(answer);
                }
            }
        }, 4);
    }
}
