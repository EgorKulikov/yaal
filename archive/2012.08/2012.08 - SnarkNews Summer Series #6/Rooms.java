package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<String> map = new ArrayList<String>();
		while (!in.isExhausted())
			map.add(in.readLine());
		int answer = 0;
		for (int i = 1; i < map.size(); i++) {
			for (int j = 1; j < map.get(i).length(); j++) {
				char current = map.get(i).charAt(j);
				char up = map.get(i - 1).charAt(j);
				char left = map.get(i).charAt(j - 1);
				if (current != '|' && current != '-' && current != '+' && (up == '-' || up == '+') && (left == '|' || left == '+'))
					answer++;
			}
		}
		out.printLine(answer);
	}
}
