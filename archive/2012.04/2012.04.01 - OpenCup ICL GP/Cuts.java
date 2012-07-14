package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Cuts {
	short[][] table;
	short[][][][][] answer;
	short[][][][][] order;
	
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		table = new short[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				table[i][j] = (short) in.readInt();
		}
		answer = new short[rowCount][rowCount][columnCount][columnCount][];
		order = new short[rowCount][rowCount][columnCount][columnCount][];
		go(0, rowCount - 1, 0, columnCount - 1);
		int sum = 0;
		boolean first = true;
		for (int i : answer[0][rowCount - 1][0][columnCount - 1]) {
			sum += i;
			if (first)
				first = false;
			else
				out.print(" ");
			out.print(sum);
		}
	}

	private void go(int rowFrom, int rowTo, int columnFrom, int columnTo) {
		if (answer[rowFrom][rowTo][columnFrom][columnTo] != null)
			return;
		short[] curAnswer = answer[rowFrom][rowTo][columnFrom][columnTo] = new short[(rowTo - rowFrom + 1) * (columnTo - columnFrom + 1)];
		short[] curOrder = order[rowFrom][rowTo][columnFrom][columnTo] = new short[(rowTo - rowFrom + 1) * (columnTo - columnFrom + 1)];
		if (rowFrom == rowTo && columnFrom == columnTo) {
			curOrder[0] = table[rowFrom][columnFrom];
			return;
		}
		int minRow = -1;
		int minColumn = -1;
		int min = Integer.MAX_VALUE;
		Arrays.fill(curAnswer, Short.MAX_VALUE);
		for (int i = rowFrom; i <= rowTo; i++) {
			for (int j = columnFrom; j <= columnTo; j++) {
				if (min > table[i][j]) {
					min = table[i][j];
					minRow = i;
					minColumn = j;
				}
			}
		}
		if (minRow != rowFrom) {
			go(rowFrom, minRow - 1, columnFrom, columnTo);
			go(minRow, rowTo, columnFrom, columnTo);
			join(curAnswer, curOrder, rowFrom, minRow - 1, columnFrom, columnTo, minRow, rowTo, columnFrom, columnTo);
		}
		if (minRow != rowTo) {
			go(rowFrom, minRow, columnFrom, columnTo);
			go(minRow + 1, rowTo, columnFrom, columnTo);
			join(curAnswer, curOrder, rowFrom, minRow, columnFrom, columnTo, minRow + 1, rowTo, columnFrom, columnTo);
		}
		if (minColumn != columnFrom) {
			go(rowFrom, rowTo, columnFrom, minColumn - 1);
			go(rowFrom, rowTo, minColumn, columnTo);
			join(curAnswer, curOrder, rowFrom, rowTo, columnFrom, minColumn - 1, rowFrom, rowTo, minColumn, columnTo);
		}
		if (minColumn != columnTo) {
			go(rowFrom, rowTo, columnFrom, minColumn);
			go(rowFrom, rowTo, minColumn + 1, columnTo);
			join(curAnswer, curOrder, rowFrom, rowTo, columnFrom, minColumn, rowFrom, rowTo, minColumn + 1, columnTo);
		}
		curAnswer[0]++;
	}

	private void join(short[] answer, short[] order, int rowFromLeft, int rowToLeft, int columnFromLeft, int columnToLeft, int rowFromRight, int rowToRight, int columnFromRight, int columnToRight) {
		short[] answerLeft = this.answer[rowFromLeft][rowToLeft][columnFromLeft][columnToLeft];
		short[] orderLeft = this.order[rowFromLeft][rowToLeft][columnFromLeft][columnToLeft];
		short[] answerRight = this.answer[rowFromRight][rowToRight][columnFromRight][columnToRight];
		short[] orderRight = this.order[rowFromRight][rowToRight][columnFromRight][columnToRight];
		int indexLeft = 0;
		int indexRight = 0;
		int index = 0;
		boolean force = false;
		while (indexLeft != answerLeft.length || indexRight != answerRight.length) {
			short nextAnswer;
			short nextOrder;
			if (indexLeft < answerLeft.length && (indexRight == answerRight.length || orderLeft[indexLeft] < orderRight[indexRight])) {
				nextAnswer = answerLeft[indexLeft];
				nextOrder = orderLeft[indexLeft++];
			} else {
				nextAnswer = answerRight[indexRight];
				nextOrder = orderRight[indexRight++];
			}
			if (nextAnswer > answer[index] && !force)
				return;
			if (nextAnswer < answer[index])
				force = true;
			answer[index] = nextAnswer;
			order[index++] = nextOrder;
		}
	}
}
