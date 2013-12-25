package on2013_09.on2013_09_23_Russian_CodeCup_2013_Final_Round.C________________________________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(array);
		int[] at = ArrayUtils.reversePermutation(array);
		int last = -1;
		int answer = 0;
		for (int i : at) {
			if (i <= last) {
				answer++;
				last = -1;
			} else
				last = i;
		}
		out.printLine(answer);
    }
}
