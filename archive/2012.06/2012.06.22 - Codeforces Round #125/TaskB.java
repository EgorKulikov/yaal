package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int height = in.readInt();
        int jump = in.readInt();
        char[] left = in.readString().toCharArray();
        char[] right = in.readString().toCharArray();
        int[] answer = new int[2 * height];
        int[] queue = new int[2 * height];
        int size = 1;
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[0] = 0;
        for (int i = 0; i < size; i++) {
            int current = queue[i];
            boolean isLeft = current < height;
            if (isLeft && left[current] == 'X' || !isLeft && right[current - height] == 'X' || answer[current] > current % height)
                continue;
            if (current % height + jump >= height) {
                out.printLine("YES");
                return;
            }
            if (answer[current + 1] == Integer.MAX_VALUE) {
                answer[current + 1] = answer[current] + 1;
                queue[size++] = current + 1;
            }
            if (current % height != 0 && answer[current - 1] == Integer.MAX_VALUE) {
                answer[current - 1] = answer[current] + 1;
                queue[size++] = current - 1;
            }
            int jumpTo;
            if (isLeft)
                jumpTo = current + height + jump;
            else
                jumpTo = current - height + jump;
            if (answer[jumpTo] == Integer.MAX_VALUE) {
                answer[jumpTo] = answer[current] + 1;
                queue[size++] = jumpTo;
            }
        }
        out.printLine("NO");
	}
}
