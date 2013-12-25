package on2013_04.on2013_04_07_OpenCup_America_GP.TaskI;



import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		if ("*".equals(s))
			throw new UnknownError();
		StringHash hash = new SimpleStringHash(s);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int first = in.readInt();
			int second = in.readInt();
			int left = 0;
			int right = s.length() - second;
			while (left < right) {
				int middle = (left + right + 1) >> 1;
				if (hash.hash(first, first + middle) == hash.hash(second, second + middle))
					left = middle;
				else
					right = middle - 1;
			}
			out.printLine(left);
		}
    }
}
