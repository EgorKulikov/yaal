package net.egork;

public class LittleElephantAndBalls {
	char[] colors = "RGB".toCharArray();

    public int getNumber(String S) {
		int answer = 0;
		for (int i = 0; i < S.length(); i++) {
			for (char c : colors) {
				int current = 0;
				for (int j = 0; j < i; j++) {
					if (S.charAt(j) == c)
						current++;
				}
				answer += Math.min(current, 2);
			}
		}
		return answer;
    }
}
