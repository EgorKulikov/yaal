package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	int target;
	int[][] departure;
	int[][] arrival;
	int[][] destination;
	int[] current;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		target = count - 1;
		departure = new int[count][];
		arrival = new int[count][];
		destination = new int[count][];
		current = new int[count];
		for (int i = 0; i < count; i++) {
			int size = in.readInt();
			current[i] = size - 1;
			departure[i] = new int[size];
			arrival[i] = new int[size];
			destination[i] = new int[size];
			for (int j = 0; j < size; j++) {
				int first = in.readCharacter() - '0';
				int second = in.readCharacter() - '0';
				in.readCharacter();
				int third = in.readCharacter() - '0';
				int fourth = in.readCharacter() - '0';
				departure[i][j] = first * 600 + second * 60 + third * 10 + fourth;
				first = in.readCharacter() - '0';
				second = in.readCharacter() - '0';
				in.readCharacter();
				third = in.readCharacter() - '0';
				fourth = in.readCharacter() - '0';
				arrival[i][j] = first * 600 + second * 60 + third * 10 + fourth;
				destination[i][j] = in.readInt() - 1;
			}
		}
		IntList answerDeparture = new IntArrayList();
		IntList answerArrival = new IntArrayList();
		int lastArrival = Integer.MAX_VALUE;
		for (int i = 24 * 60 - 1; i >= 0; i--) {
			int currentArrival = go(0, i);
			if (currentArrival < lastArrival) {
				lastArrival = currentArrival;
				answerDeparture.add(i);
				answerArrival.add(currentArrival);
			}
		}
		out.printLine(answerDeparture.size());
		answerDeparture.inPlaceReverse();
		answerArrival.inPlaceReverse();
		for (int i = 0; i < answerDeparture.size(); i++) {
			int curDeparture = answerDeparture.get(i);
			int curArrival = answerArrival.get(i);
			out.printFormat("%02d:%02d %02d:%02d\n", curDeparture / 60, curDeparture % 60, curArrival / 60, curArrival % 60);
		}
    }

	int go(int at, int time) {
		if (at == target)
			return time;
		int result = Integer.MAX_VALUE;
		while (current[at] != -1 && departure[at][current[at]] >= time) {
			result = Math.min(result, go(destination[at][current[at]], arrival[at][current[at]]));
			current[at]--;
		}
		return result;
	}
}
