package net.egork;

public class FoxAndMp3 {
    public String[] playList(int n) {
		String[] answer = new String[Math.min(n, 50)];
		int current = 0;
		for (int i = 1; i < 10; i++)
			current = go(answer, current, i, n);
		return answer;
    }

	private int go(String[] answer, int current, int prefix, int n) {
		if (current == answer.length || prefix > n)
			return current;
		answer[current++] = prefix + ".mp3";
		for (int i = 0; i < 10; i++)
			current = go(answer, current, prefix * 10 + i, n);
		return current;
	}
}
