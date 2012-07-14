package net.egork;

public class MagicCandy {
	public int whichOne(int n) {
		int answer = 0;
		for (int i = 0; i * i < n; i++) {
			answer = i * i + 1;
			if (i * (i + 1) < n)
				answer = i * (i + 1) + 1;
		}
		return answer;
	}
}

