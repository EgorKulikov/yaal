package on2013_07.on2013_07_11_Yandex_Algorithm_Online_Round__1.TaskE;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

public class TaskE {
	int[] good;
	int[] visited;
	int marker;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		good = new int[count];
		visited = new int[count];
		int queryCount = in.readInt();
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		int[] rand = new int[queryCount];
		Random random = new Random(239);
		int[] hash = new int[count];
		int firstCount = 0;
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				from[firstCount] = in.readInt() - 1;
				to[firstCount] = in.readInt() - 1;
				rand[firstCount] = random.nextInt();
				hash[from[firstCount]] ^= rand[firstCount];
				hash[to[firstCount]] ^= rand[firstCount];
				firstCount++;
			} else if (type == 2) {
				int index = in.readInt() - 1;
				hash[from[index]] ^= rand[index];
				hash[to[index]] ^= rand[index];
			} else {
				int curCount = in.readInt();
				int[] current = IOUtils.readIntArray(in, curCount);
				MiscUtils.decreaseByOne(current);
				marker++;
				for (int j : current)
					good[j] = marker;
				int answer = 0;
				for (int j : current)
					answer ^= hash[j];
				out.printLine(answer == 0 ? "YES" : "NO");
			}
		}
    }
}
