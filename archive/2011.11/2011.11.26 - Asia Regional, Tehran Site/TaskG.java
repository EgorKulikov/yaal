package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int diceMax = in.readInt();
		double[] firstAnswer = new double[size + 1];
		firstAnswer[0] = 1;
		double sum = 1;
		for (int i = 1; i <= size; i++) {
			firstAnswer[i] = 1 - sum / diceMax;
			sum += firstAnswer[i];
			if (i >= diceMax)
				sum -= firstAnswer[i - diceMax];
		}
		Deque<Integer> deque = new ArrayDeque<Integer>(diceMax + 1);
		double[] secondAnswer = new double[size + 1];
		deque.add(0);
		sum = 0;
		for (int i = 1; i <= size; i++) {
			secondAnswer[i] = 1 - sum / diceMax;
			sum -= secondAnswer[deque.peekLast()];
			if (i - deque.peekLast() == diceMax)
				deque.pollLast();
			int add = 1;
			while (!deque.isEmpty() && secondAnswer[deque.peekFirst()] > secondAnswer[i]) {
				int length;
				int current = deque.pollFirst();
				if (deque.isEmpty())
					length = current - (i - diceMax);
				else
					length = current - deque.peekFirst();
				sum -= length * secondAnswer[current];
				add += length;
			}
			deque.addFirst(i);
			sum += secondAnswer[i] * add;
		}
		out.printFormat("%.4f %.4f\n", 1 - firstAnswer[size], secondAnswer[size]);
	}
}
