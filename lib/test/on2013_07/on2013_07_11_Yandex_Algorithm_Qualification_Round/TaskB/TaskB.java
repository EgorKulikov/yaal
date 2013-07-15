package on2013_07.on2013_07_11_Yandex_Algorithm_Qualification_Round.TaskB;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int max = in.readInt();
		boolean[] isPrime = IntegerUtils.generatePrimalityTable(max + 2);
		for (int i = 3; i <= max; i++) {
			if (isPrime[i - 1] && isPrime[i + 1])
				out.printLine(i);
		}
    }
}
