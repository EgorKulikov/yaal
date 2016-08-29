package on2016_08.on2016_08_11_SNSS_2016_R2.D___Zones;



import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskD {
    Graph graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        decreaseByOne(a);
        boolean[] was = new boolean[n];
        int at;
        for (at = 0; !was[at]; at = a[at]) {
            was[at] = true;
        }
        boolean[] onCycle = new boolean[n];
        int current = at;
        do {
            onCycle[current] = true;
            current = a[current];
        } while (current != at);
        graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            if (!onCycle[i]) {
                graph.addSimpleEdge(a[i], i);
            }
        }
        int qOn = count(onCycle, true);
        int[] data = new int[qOn];
        int answer = 2;
        for (int i = 2; i < n; i++) {
            if (n % i != 0) {
                continue;
            }
            current = at;
            boolean ok = true;
            int j = 0;
            do {
                int check = check(current, i);
                if (check == 0) {
                    data[j++] = i;
                } else if (check == -1) {
                    ok = false;
                    break;
                } else {
                    data[j++] = check;
                }
                current = a[current];
            } while (current != at);
            if (!ok) {
                continue;
            }
            int total = 0;
            int[] q = new int[i];
            int shift = 0;
            for (int k : data) {
                total += k;
                shift += k;
                shift %= i;
                q[shift]++;
            }
            total /= i;
            if (ArrayUtils.count(q, total) != 0) {
                answer++;
            }
        }
        out.printLine(answer);
    }

    private int check(int current, int period) {
        int total = 1;
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            int result = check(graph.destination(i), period);
            if (result == -1) {
                return -1;
            }
            total += result;
        }
        if (total < period) {
            return total;
        } else if (total == period) {
            return 0;
        } else {
            return -1;
        }
    }
}
