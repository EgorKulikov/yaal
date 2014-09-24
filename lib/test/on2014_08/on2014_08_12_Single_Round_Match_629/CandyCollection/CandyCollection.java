package on2014_08.on2014_08_12_Single_Round_Match_629.CandyCollection;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;

public class CandyCollection {
    public int solve(int[] type1, int[] number1, int[] type2, int[] number2) {
		int count = type1.length;
		boolean[] processed = new boolean[count];
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (processed[i]) {
				continue;
			}
			int currentFlavor = type1[i];
			IntList currentQty = new IntArrayList();
			IntList nextQty = new IntArrayList();
			currentQty.add(number2[i]);
			nextQty.add(number1[i]);
			processed[i] = true;
			while (currentFlavor != type2[i]) {
				for (int j = 0; j < count; j++) {
					if (!processed[j] && type1[j] == currentFlavor) {
						currentQty.add(number1[j]);
						nextQty.add(number2[j]);
						currentFlavor = type2[j];
						processed[j] = true;
						break;
					}
					if (!processed[j] && type2[j] == currentFlavor) {
						currentQty.add(number2[j]);
						nextQty.add(number1[j]);
						currentFlavor = type1[j];
						processed[j] = true;
						break;
					}
				}
			}
			int result = solve(currentQty.toArray(), nextQty.toArray());
			answer += result;
		}
		return answer;
    }

	private int solve(int[] current, int[] next) {
		int[] answer = ArrayUtils.createArray(current.length, Integer.MAX_VALUE);
		answer[0] = Math.min(next[0], current[current.length - 1]) + 1;
		answer[1] = Math.max(current[0], next[0]) + 1;
		for (int i = 0; i < answer.length; i++) {
			if (i + 1 < answer.length) {
				answer[i + 1] = Math.min(answer[i + 1], answer[i] + Math.min(next[i + 1], current[i]) + 1);
			}
			if (i + 2 < answer.length) {
				answer[i + 2] = Math.min(answer[i + 2], answer[i] + Math.max(current[i + 1], next[i + 1]) + 1);
			}
		}
		int result = answer[current.length - 1];
		answer = ArrayUtils.createArray(current.length + 1, Integer.MAX_VALUE);
		answer[0] = 0;
		answer[1] = current[0] + 1;
		for (int i = 0; i < answer.length; i++) {
			if (i + 1 < current.length) {
				answer[i + 1] = Math.min(answer[i + 1], answer[i] + Math.min(next[i + 1], current[i]) + 1);
			}
			if (i + 2 <= current.length) {
				answer[i + 2] = Math.min(answer[i + 2], answer[i] + Math.max(current[i + 1], next[i + 1]) + 1);
			}
		}
		return Math.min(result, answer[current.length]);
	}
}
