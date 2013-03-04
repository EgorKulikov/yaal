package net.egork;

public class Rumor {
	public int getMinimum(String knowledge, String[] graph) {
		int answer = Integer.MAX_VALUE;
		int count = knowledge.length();
		int[] spread = new int[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (graph[i].charAt(j) == 'Y')
					spread[i] += 1 << j;
			}
		}
		int start = 0;
		for (int i = 0; i < count; i++) {
			if (knowledge.charAt(i) == 'Y')
				start += 1 << i;
		}
		for (int i = 0; i < (1 << count); i++) {
			int knowFirst = start;
			int knowSecond = start;
			int spreaded = 0;
			int steps = 0;
			while (true) {
				int newKnowFirst = knowFirst;
				int newKnowSecond = knowSecond;
				for (int j = 0; j < count; j++) {
					boolean first = (knowFirst >> j & 1) == 1;
					boolean second = (knowSecond >> j & 1) == 1;
					if (first && second) {
						if ((i >> j & 1) == 0 ^ (spreaded >> j & 1) == 0)
							newKnowFirst |= spread[j];
						else
							newKnowSecond |= spread[j];
						spreaded |= 1 << j;
					} else if (first)
						newKnowFirst |= spread[j];
					else if (second)
						newKnowSecond |= spread[j];
				}
				if (newKnowSecond == knowFirst && newKnowSecond == knowSecond)
					break;
				knowFirst = newKnowFirst;
				knowSecond = newKnowSecond;
				steps++;
			}
			if (knowFirst == (1 << count) - 1 && knowFirst == knowSecond)
				answer = Math.min(answer, steps);
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		return answer;
	}


}

