package on2013_09.on2013_09_01_Andrew_Stankevich_Contest__44.Braess;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Braess {
	long[] sumA, sumB, sumC, sumD;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] a = new int[count];
		int[] b = new int[count];
		int[] c = new int[count];
		int[] d = new int[count];
		IOUtils.readIntArrays(in, a, b, c, d);
		sumA = ArrayUtils.partialSums(a);
		sumB = ArrayUtils.partialSums(b);
		sumC = ArrayUtils.partialSums(c);
		sumD = ArrayUtils.partialSums(d);
		out.printLine(solve(0, count));
		double sum = 0;
		for (int i = 1; i <= count; i++)
			sum += solve(i - 1, i);
		out.printLine(sum);
		double[] answer = new double[count + 1];
		for (int i = 1; i <= count; i++) {
			answer[i] = Double.POSITIVE_INFINITY;
			for (int j = 0; j < i; j++)
				answer[i] = Math.min(answer[i], answer[j] + solve(j, i));
		}
		out.printLine(answer[count]);
		for (int i = 1; i <= count; i++) {
			answer[i] = Double.NEGATIVE_INFINITY;
			for (int j = 0; j < i; j++)
				answer[i] = Math.max(answer[i], answer[j] + solve(j, i));
		}
		out.printLine(answer[count]);
    }

	private double solve(int from, int to) {
		long dA = sumA[to] - sumA[from];
		long dB = sumB[to] - sumB[from];
		long dC = sumC[to] - sumC[from];
		long dD = sumD[to] - sumD[from];
		if (dB >= dC + dD)
			return dC + dD;
		if (dD >= dA + dB)
			return dA + dB;
		return (double)(dA * dC + dA * dD + dB * dC) / (dA + dC);
	}
}
