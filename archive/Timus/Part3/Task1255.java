package Timus.Part3;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1255 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long cemeterySize = in.readInt();
		int coffinSize = in.readInt();
		long result = (cemeterySize * cemeterySize - (cemeterySize % coffinSize) * (cemeterySize % coffinSize)) / coffinSize;
		if (result != 0) {
			for (long i = 0; i < cemeterySize % coffinSize; i++) {
				if (i + cemeterySize % coffinSize >= coffinSize)
					result++;
			}
		}
		out.println(result);
	}
}

