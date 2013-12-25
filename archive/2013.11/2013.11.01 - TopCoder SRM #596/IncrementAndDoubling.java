package net.egork;

public class IncrementAndDoubling {
    public int getMin(int[] desiredArray) {
		int answer = 0;
		int max = 0;
		for (int i : desiredArray) {
			answer += Integer.bitCount(i);
			if (i != 0)
				max = Math.max(max, Integer.bitCount(Integer.highestOneBit(i) - 1));
		}
		return answer + max;
    }
}
