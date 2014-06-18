package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndDigitJumps {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		int count = number.length;
		int[] distance = new int[count];
		Arrays.fill(distance, Integer.MAX_VALUE);
		int[] queue = new int[count];
		int size = 1;
		distance[0] = 0;
		boolean[] processed = new boolean[10];
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			if (current > 0 && distance[current - 1] == Integer.MAX_VALUE) {
				distance[current - 1] = distance[current] + 1;
				queue[size++] = current - 1;
			}
			if (current < count - 1 && distance[current + 1] == Integer.MAX_VALUE) {
				distance[current + 1] = distance[current] + 1;
				queue[size++] = current + 1;
			}
			if (!processed[number[current] - '0']) {
				processed[number[current] - '0'] = true;
				for (int j = 0; j < count; j++) {
					if (number[current] == number[j] && distance[j] == Integer.MAX_VALUE) {
						distance[j] = distance[current] + 1;
						queue[size++] = j;
					}
				}
			}
		}
		out.printLine(distance[count - 1]);
    }
}
