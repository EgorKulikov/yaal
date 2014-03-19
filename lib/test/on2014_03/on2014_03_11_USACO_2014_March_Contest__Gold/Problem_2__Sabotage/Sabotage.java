package on2014_03.on2014_03_11_USACO_2014_March_Contest__Gold.Problem_2__Sabotage;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Sabotage {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] production = IOUtils.readIntArray(in, count);
		double[] best = new double[count];
		double left = 0;
		double right = ArrayUtils.maxElement(production);
		for (int i = 0; i < 50; i++) {
			double middle = (left + right) / 2;
			best[count - 1] = production[count - 1] - middle;
			double sum = best[count - 1];
			for (int j = count - 2; j > 1; j--) {
				sum += production[j] - middle;
				best[j] = Math.min(best[j + 1], sum);
			}
			sum = 0;
			boolean found = false;
			for (int j = 0; j < count - 2; j++) {
				sum += production[j] - middle;
				if (sum + best[j + 2] < 0) {
					found = true;
					break;
				}
			}
			if (found)
				right = middle;
			else
				left = middle;
		}
		double answer = (left + right) / 2;
		if (Math.abs(answer * 2000 - Math.round(answer * 2000)) < 1e-5)
			answer = Math.round(answer * 2000) / 2000d;
		out.printFormat("%.3f\n", answer);
    }
}
