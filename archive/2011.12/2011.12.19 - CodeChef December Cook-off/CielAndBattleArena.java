package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CielAndBattleArena {
	private double[][][] win, sum;
	private int ourStrength;
	private int hisStrength;
	private double quant;
	private double answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int ourHealth = in.readInt();
		int hisHealth = in.readInt();
		ourStrength = in.readInt();
		hisStrength = in.readInt();
		int mana = in.readInt();
		win = new double[ourHealth + 1][hisHealth + 1][mana + 1];
		sum = new double[ourHealth + 1][hisHealth + 1][mana + 1];
		quant = 1d / ((ourStrength + 1) * (hisStrength + 1));
		double left = 0;
		double right = 1;
		while (right - left > 1e-7) {
			answer = (left + right) / 2;
			if (go(ourHealth, hisHealth, mana) > answer)
				left = answer;
			else
				right = answer;
		}
		out.printLine((left + right) / 2);
	}

	private double go(int ourHealth, int hisHealth, int mana) {
		if (mana != 0)
			go(ourHealth, hisHealth, mana - 1);
		for (int i = 1; i <= ourHealth; i++) {
			for (int j = 1; j <= hisHealth; j++) {
				sum[i][j][mana] = getSum(i - 1, j, mana) + getSum(i, j - 1, mana) - getSum(i - 1, j - 1, mana);
				win[i][j][mana] = (getSum(i, j, mana) + getSum(i - hisStrength - 1, j - ourStrength - 1, mana) -
					getSum(i, j - ourStrength - 1, mana) - getSum(i - hisStrength - 1, j, mana)) / (1 - quant) * quant;
				if (mana != 0)
					win[i][j][mana] = Math.max(win[i][j][mana], win[(i + 1) / 2][(j + 1) / 2][mana - 1]);
				sum[i][j][mana] += win[i][j][mana];
			}
		}
		return win[ourHealth][hisHealth][mana];
	}

	private double getSum(int ourHealth, int hisHealth, int mana) {
		double result = 0;
		if (ourHealth > 0 && hisHealth > 0)
			return sum[ourHealth][hisHealth][mana];
		if (ourHealth > 0)
			result += Math.min(hisStrength, hisStrength + hisHealth) * ourHealth;
		result += Math.min(hisStrength, hisStrength + hisHealth) * Math.min(ourStrength, ourHealth + ourStrength) * answer;
		return result;
	}
}
