package on2013_07.on2013_07_05_Yandex_Algorithm_Qualification_Round.TaskE;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int lidaPerDay = in.readInt();
		int itmoStudents = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		long total = ArrayUtils.sumArray(qty);
		if (lidaPerDay == 0) {
			out.printLine((total - 1) / itmoStudents + 1);
			return;
		}
		if (itmoStudents == 0) {
			long answer = 0;
			for (int i : qty)
				answer += 1 + (i - 1) / lidaPerDay;
			out.printLine(answer);
			return;
		}
		long left = 1;
		long right = (total - 1) / itmoStudents + 1;
		int[] qRemaining = new int[count];
		while (left < right) {
			long middle = (left + right) >> 1;
			long lidaDays = middle;
			for (int i = 0; i < count; i++) {
				long current = Math.min(lidaDays, qty[i] / lidaPerDay);
				lidaDays -= current;
				qRemaining[i] = (int) (qty[i] - lidaPerDay * current);
			}
			Arrays.sort(qRemaining);
			long itmoToSolve = 0;
			for (int i = 0; i < count - lidaDays; i++)
				itmoToSolve += qRemaining[i];
			if (itmoToSolve <= middle * itmoStudents)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }
}
