package on2013_09.on2013_09_25_Codeforces_Trainings_Season_1_Episode_3.TaskD;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int countA = in.readInt();
		int countB = in.readInt();
		int[] required = new int[count];
		int[] distanceA = new int[count];
		int[] distanceB = new int[count];
		IOUtils.readIntArrays(in, required, distanceA, distanceB);
		int[] delta = new int[count];
		int answer = 0;
		for (int i = 0; i < count; i++) {
			delta[i] = Math.abs(distanceA[i] - distanceB[i]);
			if (distanceA[i] < distanceB[i]) {
				answer += distanceA[i] * required[i];
				countA -= required[i];
			} else {
				answer += distanceB[i] * required[i];
				countB -= required[i];
			}
		}
		int[] order = ArrayUtils.order(delta);
		for (int i : order) {
			if (distanceA[i] < distanceB[i]) {
				if (countA < 0) {
					int current = Math.min(required[i], -countA);
					answer += current * delta[i];
					countA += current;
				}
			} else {
				if (countB < 0) {
					int current = Math.min(required[i], -countB);
					answer += current * delta[i];
					countB += current;
				}
			}
		}
		out.printLine(answer);
    }
}
