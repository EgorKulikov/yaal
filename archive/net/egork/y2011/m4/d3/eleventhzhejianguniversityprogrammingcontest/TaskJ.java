package net.egork.y2011.m4.d3.eleventhzhejianguniversityprogrammingcontest;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskJ implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] ids = in.readIntArray(n);
		int[] count = new int[10000];
		for (int id : ids)
			count[id]++;
		int maxFrequency = 0;
		int index = -1;
		for (int i = 1001; i < 10000; i++) {
			if (count[i] >= maxFrequency) {
				maxFrequency = count[i];
				index = i;
			}
		}
		out.println(index);
	}
}

