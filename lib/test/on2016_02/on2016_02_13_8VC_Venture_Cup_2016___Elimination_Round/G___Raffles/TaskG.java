package on2016_02.on2016_02_13_8VC_Venture_Cup_2016___Elimination_Round.G___Raffles;



import net.egork.collections.intcollection.Heap;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int t = in.readInt();
        int q = in.readInt();
        int[] p = readIntArray(in, n);
        int[] l = readIntArray(in, n);
        int[] bought = new int[n];
        double[] valueLeft = new double[n];
        double[] valueRight = new double[n];
        Heap left = new Heap((x, y) -> Double.compare(valueLeft[x], valueLeft[y]), n);
        Heap right = new Heap((x, y) -> Double.compare(valueRight[y], valueRight[x]), n);
        double answer = 0;
        for (int i = 0; i < n; i++) {
            valueRight[i] = value(l[i], 0) * p[i];
            right.add(i);
        }
        int rem = t;
        for (int i = 0; i < t && !right.isEmpty(); i++) {
            int current = right.poll();
            rem--;
            answer += valueRight[current];
            left.remove(current);
            valueLeft[current] = value(l[current], bought[current]) * p[current];
            left.add(current);
            bought[current]++;
            if (bought[current] < l[current]) {
                valueRight[current] = value(l[current], bought[current]) * p[current];
                right.add(current);
            }
        }
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            int id = in.readInt() - 1;
            answer -= (double)bought[id] / (bought[id] + l[id]) * p[id];
            if (type == 1) {
                l[id]++;
            } else {
                l[id]--;
            }
            while (bought[id] > l[id]) {
                bought[id]--;
                rem++;
            }
            answer += (double)bought[id] / (bought[id] + l[id]) * p[id];
            left.remove(id);
            if (bought[id] != 0) {
                valueLeft[id] = value(l[id], bought[id] - 1) * p[id];
                left.add(id);
            }
            right.remove(id);
            if (bought[id] != l[id]) {
                valueRight[id] = value(l[id], bought[id]) * p[id];
                right.add(id);
            }
            while (rem != 0 && !right.isEmpty()) {
                int current = right.poll();
                rem--;
                answer += valueRight[current];
                left.remove(current);
                valueLeft[current] = value(l[current], bought[current]) * p[current];
                left.add(current);
                bought[current]++;
                if (bought[current] < l[current]) {
                    valueRight[current] = value(l[current], bought[current]) * p[current];
                    right.add(current);
                }
            }
            while (!left.isEmpty() && !right.isEmpty() && valueLeft[left.peek()] < valueRight[right.peek()]) {
                int curRight = right.poll();
                int curLeft = left.poll();
                answer += valueRight[curRight];
                left.remove(curRight);
                valueLeft[curRight] = value(l[curRight], bought[curRight]) * p[curRight];
                left.add(curRight);
                bought[curRight]++;
                if (bought[curRight] < l[curRight]) {
                    valueRight[curRight] = value(l[curRight], bought[curRight]) * p[curRight];
                    right.add(curRight);
                }
                bought[curLeft]--;
                answer -= valueLeft[curLeft];
                right.remove(curLeft);
                valueRight[curLeft] = value(l[curLeft], bought[curLeft]) * p[curLeft];
                right.add(curLeft);
                if (bought[curLeft] > 0) {
                    valueLeft[curLeft] = value(l[curLeft], bought[curLeft] - 1) * p[curLeft];
                    left.add(curLeft);
                }
            }
            out.printLine(answer);
        }
    }

    double value(int a, int b) {
        return (double)a / (a + b) / (a + b + 1);
    }
}
