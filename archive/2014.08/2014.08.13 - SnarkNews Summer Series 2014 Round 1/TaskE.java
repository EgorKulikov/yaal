package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	double[][][] probability = new double[5][5][5];
	int[] first = new int[4];
	int[] second = new int[4];
	int[] sortedFirst = new int[4];
	int[] sortedSecond = new int[4];
	double quant;
	double[][] answer = new double[4001][501];
	int firstDies;
	int secondDies;

	{
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				quant = 1;
				for (int k = 0; k < i + j; k++) {
					quant /= 6;
				}
				go(0, 0, i, j);
			}
		}
	}

	private void go(int firstRolled, int secondRolled, int firstTotal, int secondTotal) {
		if (secondRolled == secondTotal) {
			System.arraycopy(second, 0, sortedSecond, 0, secondTotal);
			Arrays.sort(sortedSecond, 0, secondTotal);
			int attacker = 0;
			for (int i = 0; i < Math.min(firstTotal, secondTotal); i++) {
				if (sortedFirst[firstTotal - i - 1] > sortedSecond[secondTotal - i - 1]) {
					attacker++;
				}
			}
			probability[firstTotal][secondTotal][attacker] += quant;
			return;
		}
		if (firstRolled == firstTotal) {
			if (secondRolled == 0) {
				System.arraycopy(first, 0, sortedFirst, 0, firstTotal);
				Arrays.sort(sortedFirst, 0, firstTotal);
			}
			for (int i = 1; i <= 6; i++) {
				second[secondRolled] = i;
				go(firstRolled, secondRolled + 1, firstTotal, secondTotal);
			}
			return;
		}
		for (int i = 1; i <= 6; i++) {
			first[firstRolled] = i;
			go(firstRolled + 1, secondRolled, firstTotal, secondTotal);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		firstDies = in.readInt();
		secondDies = in.readInt();
		int attacker = in.readInt();
		int defender = in.readInt();
		int left = attacker;
		int right = answer.length - 1;
		ArrayUtils.fill(answer, -1);
		while (left < right) {
			int middle = (left + right) >> 1;
			if (go(middle, defender) > .75 - 1e-13) {
				right = middle;
			} else {
				left = middle + 1;
			}
		}
		out.printLine(left - attacker);
    }

	private double go(int attacker, int defender) {
		if (answer[attacker][defender] != -1) {
			return answer[attacker][defender];
		}
		if (attacker == 0) {
			return answer[attacker][defender] = 0;
		}
		if (defender == 0) {
			return answer[attacker][defender] = 1;
		}
		answer[attacker][defender] = 0;
		int attackerDie = Math.min(attacker, firstDies);
		int defenderDie = Math.min(defender, secondDies);
		int atStake = Math.min(attackerDie, defenderDie);
		for (int i = 0; i <= atStake; i++) {
			answer[attacker][defender] += probability[attackerDie][defenderDie][i] * go(attacker - atStake + i, defender - i);
		}
		return answer[attacker][defender];
	}
}
