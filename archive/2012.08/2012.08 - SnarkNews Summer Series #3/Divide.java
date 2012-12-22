package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Divide {
    double[][][] answer;
    int[] x, y;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        x = new int[count];
        y = new int[count];
        IOUtils.readIntArrays(in, x, y);
        answer = new double[count][count][count];
        ArrayUtils.fill(answer, -1);
        double result = Double.POSITIVE_INFINITY;
        for (int i = 2; i < count - 1; i++)
            result = Math.min(result, go(0, 1, i) + go(0, i, count - 1) + GeometryUtils.fastHypot(x[i] - x[0], y[i] - y[0]));
        result = Math.min(result, go(1, 2, count - 1) + GeometryUtils.fastHypot(x[count - 1] - x[1], y[count - 1] - y[1]));
        out.printLine(result);
	}

    private double go(int base, int from, int to) {
        if (answer[base][from][to] != -1)
            return answer[base][from][to];
        if (to - from == 1)
            return answer[base][from][to] = 0;
        answer[base][from][to] = go(from, from + 1, to) + GeometryUtils.fastHypot(x[from] - x[to], y[from] - y[to]);
        for (int i = from + 1; i < to; i++)
            answer[base][from][to] = Math.min(answer[base][from][to], go(base, from, i) + go(base, i, to) + GeometryUtils.fastHypot(x[i] - x[base], y[i] - y[base]));
        return answer[base][from][to];
    }
}
