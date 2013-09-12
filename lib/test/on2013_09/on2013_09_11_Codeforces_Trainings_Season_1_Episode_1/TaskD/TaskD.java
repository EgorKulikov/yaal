package on2013_09.on2013_09_11_Codeforces_Trainings_Season_1_Episode_1.TaskD;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		if (testNumber != 1) {
			out.printLine();
			out.printLine();
		}
		int[][] stacking = new int[count][];
		for (int i = 0; i < count; i++) {
			IntList current = new IntArrayList();
			while (true) {
				int next = in.readInt();
				if (next == 0)
					break;
				current.add(next);
			}
			stacking[i] = current.toArray();
		}
		int[][] answer = new int[stacking[0][0]][];
		for (int i = 0; i < answer.length; i++) {
			int length = 1;
			for (int j = 1; j < stacking.length; j++) {
				if (stacking[j][0] > i)
					length = j + 1;
				else
					break;
			}
			answer[i] = new int[length];
			for (int j = 0; j < answer[i].length; j++) {
				for (int k = 0; k < stacking[j].length; k++) {
					if (stacking[j][k] > i)
						answer[i][j]++;
				}
			}
		}
		for (int[] row : answer)
			out.printLine(row);
		out.printLine();
		answer = new int[stacking[0].length][];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = new int[stacking[0][i]];
			for (int j = 0; j < answer[i].length; j++) {
				for (int k = 0; k < stacking.length; k++) {
					if (stacking[k].length > i && stacking[k][i] > j)
						answer[i][j]++;
				}
			}
		}
		for (int[] row : answer)
			out.printLine(row);
    }
}
