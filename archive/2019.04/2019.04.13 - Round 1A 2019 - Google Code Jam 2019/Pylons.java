package net.egork;

import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Random;

import static net.egork.misc.ArrayUtils.createOrder;

public class Pylons {
    Graph graph;
    boolean[] done;
    int[] order;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int c = in.readInt();
        if (r == 2 && c <= 4) {
            out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
            return;
        }
        if (c == 2 && r <= 4) {
            out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
            return;
        }
        if (r == 3 && c == 3) {
            out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
            return;
        }
        Random random = new Random(239);
        int[] o = createOrder(r * c);
        for (int i = 0; i < r * c; i++) {
            int at = random.nextInt(i + 1);
            int temp = o[at];
            o[at] = o[i];
            o[i] = temp;
        }
        graph = new Graph(r * c);
        for (int i = 0; i < r * c; i++) {
            int a = i / c;
            int b = i % c;
            for (int jj = 0; jj < r * c; jj++) {
                int j = o[jj];
                int x = j / c;
                int y = j % c;
                if (a == x || b == y || a + b == x + y || a - b == x - y) {
                    continue;
                }
                graph.addSimpleEdge(i, j);
            }
        }
        done = new boolean[r * c];
        order = new int[r * c];
        done[c + 1] = true;
        order[0] = c + 1;
//        System.err.println(r + " " + c);
        if (!go(1, c + 1)) {
            throw new RuntimeException();
        }
        out.printLine("Case #" + testNumber + ": POSSIBLE");
        for (int i = 0; i < r * c; i++) {
            out.printLine(order[i] / c + 1, order[i] % c + 1);
        }
    }

    private boolean go(int step, int last) {
        if (step == order.length) {
            return true;
        }
        for (int i = graph.firstOutbound(last); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (!done[next]) {
                order[step] = next;
                done[next] = true;
                if (go(step + 1, next)) {
                    return true;
                }
                done[next] = false;
            }
        }
        return false;
    }
}
