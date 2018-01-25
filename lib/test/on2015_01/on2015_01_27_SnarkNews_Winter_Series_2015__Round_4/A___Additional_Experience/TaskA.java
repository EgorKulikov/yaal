package on2015_01.on2015_01_27_SnarkNews_Winter_Series_2015__Round_4.A___Additional_Experience;



import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] score = in.readIntArray(count);
		long answer = Math.max((ArrayUtils.sumArray(score) + 1) / 2, ArrayUtils.maxElement(score));
		out.printLine(answer);
    }
}
