package on2012_05.on2012_4_5.taskd;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	long[][] c = IntegerUtils.generateBinomialCoefficients(51);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int winsRequired = in.readInt();
		double winProbability = in.readDouble();
		double answer = 0;
		double probability = Math.pow(winProbability, winsRequired);
		for (int i = 0; i < winsRequired; i++) {
			answer += probability * c[winsRequired - 1 + i][i];
			probability *= 1 - winProbability;
		}
		out.printFormat("%.2f\n", answer);
	}
}
