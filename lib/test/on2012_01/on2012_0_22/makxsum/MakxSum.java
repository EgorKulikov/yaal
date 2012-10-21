package on2012_01.on2012_0_22.makxsum;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MakxSum {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] indices = IOUtils.readIntArray(in, 3);
		MiscUtils.decreaseByOne(indices);
		int[] numbers = IOUtils.readIntArray(in, count);
		int[] sums = new int[2013];
		int[] answer = new int[2012];
		int[] next = new int[2012];
		int answerLength = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 2012 && j < i; j++)
				sums[j] += numbers[i];
			int index = Math.min(i, 2012);
			int sumsLength = index + 1;
			while (index > 0 && numbers[i] > sums[index - 1]) {
				sums[index] = sums[index - 1];
				index--;
			}
			sums[index] = numbers[i];
			index = 0;
			int answerIndex = 0;
			while (index + answerIndex < 2012 && index < sumsLength && answerIndex < answerLength) {
				if (sums[index] > answer[answerIndex]) {
					next[index + answerIndex] = sums[index];
					index++;
				} else {
					next[index + answerIndex] = answer[answerIndex];
					answerIndex++;
				}
			}
			while (index + answerIndex < 2012 && index < sumsLength) {
				next[index + answerIndex] = sums[index];
				index++;
			}
			while (index + answerIndex < 2012 && answerIndex < answerLength) {
				next[index + answerIndex] = answer[answerIndex];
				answerIndex++;
			}
			answerLength = index + answerIndex;
			int[] temp = next;
			next = answer;
			answer = temp;
		}
		out.printLine(answer[indices[0]], answer[indices[1]], answer[indices[2]]);
	}
}
