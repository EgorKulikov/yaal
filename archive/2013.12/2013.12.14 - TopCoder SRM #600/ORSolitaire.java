package net.egork;

public class ORSolitaire {
    public int getMinimum(int[] numbers, int goal) {
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 30; i++) {
			if ((goal >> i & 1) == 0)
				continue;
			int current = 0;
			for (int j : numbers) {
				if ((j >> i & 1) == 1 && (j | goal) == goal)
					current++;
			}
			answer = Math.min(answer, current);
		}
		return answer;
    }
}
