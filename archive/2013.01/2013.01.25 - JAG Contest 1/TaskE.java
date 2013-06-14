package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	int[] fullHand = new int[7];
	int[] hand = new int[5];
	int[] fullValue = new int[6];
	int[] value = new int[6];
	int[] suit = new int[5];
	int[] denomination = new int[5];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String values = "23456789TJQKA";
		String suits = "SHDC";
		int[] first = new int[2];
		int[] second = new int[2];
		int[] board = new int[3];
		for (int i = 0; i < 2; i++)
			first[i] = read(in, values, suits);
		for (int i = 0; i < 2; i++)
			second[i] = read(in, values, suits);
		for (int i = 0; i < 3; i++)
			board[i] = read(in, values, suits);
		System.arraycopy(board, 0, fullHand, 2, 3);
		int[] firstValue = new int[6];
		int[] secondValue = new int[6];
		int win = 0;
		int total = 0;
		for (int turn = 0; turn < 52; turn++) {
			boolean good = true;
			for (int i : first)
				good &= i != turn;
			for (int i : second)
				good &= i != turn;
			for (int i : board)
				good &= i != turn;
			if (!good)
				continue;
			for (int river = turn + 1; river < 52; river++) {
				good = turn != river;
				for (int i : first)
					good &= i != river;
				for (int i : second)
					good &= i != river;
				for (int i : board)
					good &= i != river;
				if (!good)
					continue;
				fullHand[5] = turn;
				fullHand[6] = river;
				System.arraycopy(first, 0, fullHand, 0, 2);
				evaluateFull();
				System.arraycopy(fullValue, 0, firstValue, 0, 6);
				System.arraycopy(second, 0, fullHand, 0, 2);
				evaluateFull();
				System.arraycopy(fullValue, 0, secondValue, 0, 6);
				total++;
				for (int i = 0; i < 6; i++) {
					if (firstValue[i] > secondValue[i]) {
						win++;
						break;
					} else if (firstValue[i] < secondValue[i])
						break;
				}
			}
		}
		out.printLine((double)win / total);
    }

	private void evaluateFull() {
		Arrays.fill(fullValue, 0);
		for (int i = 0; i < 128; i++) {
			if (Integer.bitCount(i) != 5)
				continue;
			int j = 0;
			for (int k = 0; k < 7; k++) {
				if ((i >> k & 1) == 1)
					hand[j++] = fullHand[k];
			}
			evaluate();
		}
	}

	private void evaluate() {
		for (int i = 0; i < 5; i++) {
			suit[i] = hand[i] & 3;
			denomination[i] = hand[i] >> 2;
		}
		Arrays.sort(suit);
		Arrays.sort(denomination);
		boolean isFlush = suit[0] == suit[4];
		boolean isStraight = true;
		for (int i = 1; i < 5; i++) {
			if (denomination[i] - denomination[i - 1] != 1)
				isStraight = false;
		}
		if (!isStraight) {
			isStraight = true;
			for (int i = 0; i < 4; i++) {
				if (denomination[i] != i)
					isStraight = false;
			}
			if (denomination[4] != 12)
				isStraight = false;
			if (isStraight) {
				denomination[4] = -1;
				Arrays.sort(denomination);
			}
		}
		boolean four = denomination[0] == denomination[3] || denomination[1] == denomination[4];
		boolean triple = (denomination[0] == denomination[2] || denomination[1] == denomination[3] || denomination[2] == denomination[4]) && !four;
		int pairCount = 0;
		for (int i = 1; i < 5; i++) {
			if (denomination[i] == denomination[i - 1] && (i == 1 || denomination[i - 2] != denomination[i - 1]) && (i == 4 || denomination[i] != denomination[i + 1]))
				pairCount++;
		}
		ArrayUtils.reverse(denomination);
		if (isFlush && isStraight) {
			value[0] = 9;
			System.arraycopy(denomination, 0, value, 1, 5);
		} else if (four) {
			value[0] = 8;
			int valueFour = denomination[1];
			for (int i = 1; i < 5; i++)
				value[i] = valueFour;
			for (int i = 0; i < 5; i++) {
				if (denomination[i] != valueFour)
					value[5] = denomination[i];
			}
		} else if (triple && pairCount == 1) {
			value[0] = 7;
			int valueTriple = denomination[2];
			for (int i = 1; i < 4; i++)
				value[i] = valueTriple;
			for (int i = 0; i < 5; i++) {
				if (denomination[i] != valueTriple)
					value[4] = value[5] = denomination[i];
			}
		} else if (isFlush) {
			value[0] = 6;
			System.arraycopy(denomination, 0, value, 1, 5);
		} else if (isStraight) {
			value[0] = 5;
			System.arraycopy(denomination, 0, value, 1, 5);
		} else if (triple) {
			value[0] = 4;
			int valueTriple = denomination[2];
			for (int i = 1; i < 4; i++)
				value[i] = valueTriple;
			int j = 4;
			for (int i = 0; i < 5; i++) {
				if (denomination[i] != valueTriple)
					value[j++] = denomination[i];
			}
		} else if (pairCount == 2) {
			value[0] = 3;
			value[1] = value[2] = denomination[1];
			value[3] = value[4] = denomination[3];
			for (int i = 0; i < 5; i += 2) {
				if (denomination[i] != denomination[1] && denomination[i] != denomination[3])
					value[5] = denomination[i];
			}
		} else if (pairCount == 1) {
			value[0] = 2;
			int valuePair = -1;
			for (int i = 1; i < 5; i++) {
				if (denomination[i] == denomination[i - 1])
					valuePair = denomination[i];
			}
			value[1] = value[2] = valuePair;
			int j = 3;
			for (int i = 0; i < 5; i++) {
				if (denomination[i] != valuePair)
					value[j++] = denomination[i];
			}
		} else {
			value[0] = 1;
			System.arraycopy(denomination, 0, value, 1, 5);
		}
		boolean more = false;
		for (int i = 0; i < 6; i++) {
			if (fullValue[i] > value[i] && !more)
				return;
			if (fullValue[i] < value[i])
				more = true;
			fullValue[i] = value[i];
		}
	}

	private int read(InputReader in, String values, String suits) {
		char suit = in.readCharacter();
		char value = in.readCharacter();
		return values.indexOf(value) * 4 + suits.indexOf(suit);
	}
}
