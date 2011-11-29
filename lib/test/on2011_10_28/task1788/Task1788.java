package on2011_10_28.task1788;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task1788 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int girlCount = in.readInt();
		int boyCount = in.readInt();
		int[] girlDisappointment = IOUtils.readIntArray(in, girlCount);
		int[] boyDisappointment = IOUtils.readIntArray(in, boyCount);
		Arrays.sort(girlDisappointment);
		Arrays.sort(boyDisappointment);
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= Math.min(boyCount, girlCount); i++) {
			int current = 0;
			for (int j = 0; j < girlCount - i; j++)
				current += girlDisappointment[j];
			for (int j = 0; j < boyCount - i; j++)
				current += boyDisappointment[j] * i;
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
