package net.egork.y2011.m4.d3.eleventhzhejianguniversityprogrammingcontest;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String zodiac = "Rat, Ox, Tiger, Rabbit, Dragon, Snake, Horse, Ram, Monkey, Rooster, Dog, Pig";
		int age = in.readInt();
		age = 4 - age;
		age %= 12;
		age += 12;
		age %= 12;
		out.println(zodiac.split(", ")[age]);
	}
}

