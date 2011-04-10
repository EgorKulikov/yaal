package net.egork.y2011.m4.d9.coci;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Kolo implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int wedgeCount = in.readInt();
		int spinCount = in.readInt();
		char[] result = new char[wedgeCount];
		Arrays.fill(result, '?');
		int pointer = 0;
		for (int i = 0; i < spinCount; i++) {
			pointer -= in.readInt();
			pointer %= wedgeCount;
			pointer += wedgeCount;
			pointer %= wedgeCount;
			char letter = in.readCharacter();
			if (result[pointer] != '?' && result[pointer] != letter) {
				out.println("!");
				return;
			}
			result[pointer] = letter;
		}
		out.println(new String(Arrays.copyOfRange(result, pointer, wedgeCount)) + new String(Arrays.copyOfRange(result, 0, pointer)));
	}
}

