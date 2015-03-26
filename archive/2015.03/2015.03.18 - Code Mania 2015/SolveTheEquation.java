package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SolveTheEquation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String equation = in.readLine().replaceAll(" ", "");
		String s = equation.replace('=', ' ');
		boolean wasDigit = false;
		char operation = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c) || c == 'x') {
				wasDigit = true;
			} else if (c == '+' || c == '*' || c == '/' || c == '-' && wasDigit) {
				operation = c;
				s = s.substring(0, i) + " " + s.substring(i + 1);
				break;
			}
		}
		String[] operands = s.split(" ");
		if (operands.length != 3) {
			while (true);
		}
		double multiplier = 1;
		for (int i = 0; i < 3; i++) {
			if (operands[i].equals("-x")) {
				operands[i] = "x";
				multiplier = -1;
			}
		}
		double result;
		if (operation == '+') {
			if (operands[0].equals("x")) {
				result = Integer.parseInt(operands[2]) - Integer.parseInt(operands[1]);
			} else if (operands[1].equals("x")) {
				result = Integer.parseInt(operands[2]) - Integer.parseInt(operands[0]);
			} else {
				result = Integer.parseInt(operands[1]) + Integer.parseInt(operands[0]);
			}
		} else if (operation == '-') {
			if (operands[0].equals("x")) {
				result = Integer.parseInt(operands[2]) + Integer.parseInt(operands[1]);
			} else if (operands[1].equals("x")) {
				result = Integer.parseInt(operands[0]) - Integer.parseInt(operands[2]);
			} else {
				result = Integer.parseInt(operands[0]) - Integer.parseInt(operands[1]);
			}
		} else if (operation == '*') {
			if (operands[0].equals("x")) {
				result = (double)Integer.parseInt(operands[2]) / Integer.parseInt(operands[1]);
			} else if (operands[1].equals("x")) {
				result = (double)Integer.parseInt(operands[2]) / Integer.parseInt(operands[0]);
			} else {
				result = (double)Integer.parseInt(operands[0]) * Integer.parseInt(operands[1]);
			}
		} else {
			if (operands[0].equals("x")) {
				result = (double)Integer.parseInt(operands[2]) * Integer.parseInt(operands[1]);
			} else if (operands[1].equals("x")) {
				result = (double)Integer.parseInt(operands[0]) / Integer.parseInt(operands[2]);
			} else {
				result = (double)Integer.parseInt(operands[0]) / Integer.parseInt(operands[1]);
			}
		}
		out.printFormat("%.6f\n", result * multiplier);
	}
}
