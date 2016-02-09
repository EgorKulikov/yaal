package on2016_02.on2016_02_04_AIM_Tech_Round__Div__1_.D___Birthday;



import net.egork.collections.intcollection.Heap;
import net.egork.collections.intcollection.Range;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = readIntArray(in, n);
        double[] prob = new double[n];
        for (int i = 0; i < n; i++) {
            prob[i] = 1 - p[i] / 100d;
        }
        double[] notCaught = createArray(n, 1d);
        double[] weight = createArray(n, Double.POSITIVE_INFINITY);
        Heap heap = new Heap((x, y) -> Double.compare(weight[y], weight[x]), n);
        heap.addAll(Range.range(n));
        double alreadyCaught = 0;
        double answer = 0;
        for (int i = 1; i <= 2000000; i++) {
            int current = heap.poll();
            notCaught[current] *= prob[current];
            weight[current] = (1 - prob[current] * notCaught[current]) / (1 - notCaught[current]);
            heap.add(current);
            double nowCaught = 1;
            for (int j = 0; j < n; j++) {
                nowCaught *= 1 - notCaught[j];
            }
            answer += (nowCaught - alreadyCaught) * i;
            alreadyCaught = nowCaught;
        }
        System.err.println(alreadyCaught);
        out.printLine(answer);
    }
}
