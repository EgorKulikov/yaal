package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readIntArrays;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class PathInversions {
    static class Array {
        int head;
        int[] data;

        public Array() {
            data = new int[2];
            data[1] = 1;
            head = 1;
        }

        public void advanceUp() {
            if (head == 0) {
                int[] oldData = data;
                data = new int[2 * data.length];
                System.arraycopy(oldData, 0, data, oldData.length, oldData.length);
                head = oldData.length;
            }
            head--;
        }

        public int size() {
            return data.length - head;
        }

        public int get(int at) {
            if (at + head >= data.length) {
                return 0;
            }
            return data[at + head];
        }

        public void add(int at, int value) {
            data[at + head] += value;
        }
    }

    long qty;
    Graph graph;
    int k;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        k = in.readInt();
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        graph = BidirectionalGraph.createGraph(n, a, b);
        go(0, -1);
        qty %= MOD7;
        qty *= ((long)k) * (k + 1) / 2 % MOD7;
        qty %= MOD7;
        out.printLine(qty);
    }

    private Array go(int vertex, int last) {
        Array result = new Array();
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            Array call = go(next, vertex);
            call.advanceUp();
            if (call.size() > result.size()) {
                Array temp = call;
                call = result;
                result = temp;
            }
            for (int j = 0; j < call.size() && j <= k; j++) {
                qty += (long)call.get(j) * result.get(k - j);
            }
            for (int j = 0; j < call.size(); j++) {
                result.add(j, call.get(j));
            }
        }
        return result;
    }
}
