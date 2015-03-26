package on2015_03.on2015_03_18_Code_Mania_2015.Galaxies_and_Beyond;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GalaxiesAndBeyond {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long distance = in.readInt();
		distance *= distance;
		long[] x = new long[count];
		long[] y = new long[count];
		long[] z = new long[count];
		IOUtils.readLongArrays(in, x, y, z);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				if ((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]) + (z[i] - z[j]) * (z[i] - z[j]) <= distance) {
					setSystem.join(i, j);
				}
			}
		}
		out.printLine(setSystem.getSetCount());
    }
}
