package on2011_11.on2011_10_29.task1864;



import net.egork.misc.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1864 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] bought = IOUtils.readIntArray(in, count);
		long totalBought = ArrayUtils.sumArray(bought);
		Rational[] excess = new Rational[count];
		Rational average = new Rational(totalBought, count + 1);
		for (int i = 0; i < count; i++) {
			excess[i] = MiscUtils.max(new Rational(bought[i], 1).subtract(average), Rational.ZERO);
		}
		Rational sumExcess = Rational.ZERO;
		for (Rational r : excess)
			sumExcess = sumExcess.add(r);
		long[] answer = new long[count];
		for (int i = 0; i < count; i++) {
			answer[i] = excess[i].divide(sumExcess).multiply(100).floor();
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
