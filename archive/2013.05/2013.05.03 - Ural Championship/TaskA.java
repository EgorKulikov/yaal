package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Map<String, Integer> optionId = new HashMap<String, Integer>();
		int[] slowdown = new int[n];
		for (int i = 0; i < n; ++i) {
			optionId.put(in.readString(), i);
			slowdown[i] = in.readInt();
		}
		int size = in.readInt() * in.readInt();
		int p = in.readInt();
		int[] next = new int[n + 1];
		int[] prev = new int[n + 1];
		boolean[] on = new boolean[n];
		Arrays.fill(on, true);
		int cntOn = n;
		next[n] = n;
		prev[n] = n;
		for (int i = 0; i < n; ++i) {
			next[i] = next[n];
			prev[i] = n;
			next[prev[i]] = i;
			prev[next[i]] = i;
		}
		int m = in.readInt();
		final long TOO_MUCH = (long) 1.1e9;
		for (int step = 0; step <= m; ++step) {
			long perFrame;
			if (cntOn > 40)
				perFrame = TOO_MUCH;
			else {
				int cur = next[n];
				perFrame = 1;
				while (cur != n) {
					perFrame *= slowdown[cur];
					if (perFrame > TOO_MUCH) perFrame = TOO_MUCH;
					cur = next[cur];
				}
			}
			if (perFrame * size * 60 <= p) {
				out.printLine("Perfect");
			} else if (perFrame * size * 10 > p) {
				out.printLine("Slideshow");
			} else {
				out.printLine("So-so");
			}
			if (step == m) break;
			String action = in.readString();
			if (action.equals("Resolution")) {
				size = in.readInt() * in.readInt();
			} else {
				int what = optionId.get(in.readString());
				boolean acBool = action.equals("On");
				if (acBool == on[what]) throw new RuntimeException();
				on[what] = acBool;
				if (on[what]) {
					next[what] = next[n];
					prev[what] = n;
					next[prev[what]] = what;
					prev[next[what]] = what;
					++cntOn;
				} else {
					prev[next[what]] = prev[what];
					next[prev[what]] = next[what];
					--cntOn;
				}
			}
		}
    }
}
