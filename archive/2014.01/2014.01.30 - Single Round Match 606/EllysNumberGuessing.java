package net.egork;

public class EllysNumberGuessing {
    public int getNumber(int[] guesses, int[] answers) {
		int answer = -2;
		for (int i = guesses[0] - answers[0]; i <= guesses[0] + answers[0]; i += 2 * answers[0]) {
			if (i <= 0 || i > 1000000000)
				continue;
			boolean good = true;
			for (int j = 0; j < guesses.length; j++) {
				if ((Math.abs(i - guesses[j])) != answers[j])
					good = false;
			}
			if (good) {
				if (answer == -2)
					answer = i;
				else
					answer = -1;
			}
		}
		return answer;
    }
}
