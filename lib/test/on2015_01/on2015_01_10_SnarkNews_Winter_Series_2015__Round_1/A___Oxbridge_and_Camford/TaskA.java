package on2015_01.on2015_01_10_SnarkNews_Winter_Series_2015__Round_1.A___Oxbridge_and_Camford;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	double[][] min = new double[11][401];
	double[][] max = new double[11][401];

	{
		ArrayUtils.fill(min, Double.POSITIVE_INFINITY);
		min[0][0] = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <= i * 40; j++) {
				for (int k = 0; k <= 40; k++) {
					double current = k >= 25 ? 4 : k >= 20 ? 3.5 : k >= 15 ? 3 : k >= 10 ? 2.5 : 2;
					min[i + 1][j + k] = Math.min(min[i + 1][j + k], (min[i][j] * i + current) / (i + 1));
					max[i + 1][j + k] = Math.max(max[i + 1][j + k], (max[i][j] * i + current) / (i + 1));
				}
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int mark = in.readInt() - 60;
		int qty = in.readInt();
		out.printLine(min[qty][qty * mark], max[qty][qty * mark]);
    }
}
