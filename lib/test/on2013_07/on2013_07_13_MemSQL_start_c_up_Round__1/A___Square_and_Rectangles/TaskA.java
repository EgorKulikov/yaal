package on2013_07.on2013_07_13_MemSQL_start_c_up_Round__1.A___Square_and_Rectangles;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x0 = new int[count];
		int[] y0 = new int[count];
		int[] x1 = new int[count];
		int[] y1 = new int[count];
		IOUtils.readIntArrays(in, x0, y0, x1, y1);
		int height = ArrayUtils.maxElement(x1) - ArrayUtils.minElement(x0);
		int width = ArrayUtils.maxElement(y1) - ArrayUtils.minElement(y0);
		int totalSquare = height * width;
		for (int i = 0; i < count; i++) {
			totalSquare -= (x1[i] - x0[i]) * (y1[i] - y0[i]);
		}
		if (totalSquare == 0 && height == width)
			out.printLine("YES");
		else
			out.printLine("NO");
    }
}
