package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] answer = new char[count];
		int[] grandi = new int[count + 1];
		int[] step = new int[count + 1];
		int key = 0;
		for (int i = 1; i <= count; i++) {
			if (i % 2 == count % 2) {
				answer[i - 1] = 'F';
				continue;
			}
			if (3 * i - 2 >= count) {
				answer[i - 1] = 'F';
				continue;
			}
			Arrays.fill(grandi, 0);
			for (int j = i; j <= count - i; j++) {
				key++;
				for (int k = 0; 2 * k + i <= j; k++)
					step[grandi[k] ^ grandi[j - k - i]] = key;
				for (int k = 0; ; k++) {
					if (step[k] != key) {
						grandi[j] = k;
						break;
					}
				}
			}
			grandi[count] = 0;
			for (int j = 0; 2 * j + i <= count; j++) {
				if (grandi[j] == grandi[count - j - i])
					grandi[count] = 1;
			}
			if (grandi[count] == 0)
				answer[i - 1] = 'S';
			else
				answer[i - 1] = 'F';
		}
		out.printLine(answer);
    }
}
