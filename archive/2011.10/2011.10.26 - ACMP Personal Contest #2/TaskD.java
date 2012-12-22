package net.egork;

import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskD {
	private Map<String,Integer> answer, next;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String sequence = in.readString();
		answer = new HashMap<String, Integer>();
		answer.put("", 0);
		next = new HashMap<String, Integer>();
		go(sequence);
		String[] moves = new String[answer.get(sequence)];
		int index = 0;
		while (sequence.length() != 0) {
			int move = next.get(sequence);
			moves[index++] = "" + sequence.charAt(move) + move;
			sequence = process(sequence.substring(0, move) + sequence.charAt(move) + sequence.substring(move), move);
		}
		out.print(moves.length);
		for (String move : moves)
			out.print(" " + move);
		out.println();
	}

	private int go(String sequence) {
		if (answer.containsKey(sequence))
			return answer.get(sequence);
		int result = Integer.MAX_VALUE;
		int move = -1;
		for (int i = 0; i < sequence.length(); i++) {
			if (i == 0 || sequence.charAt(i) != sequence.charAt(i - 1)) {
				int current = go(process(sequence.substring(0, i) + sequence.charAt(i) + sequence.substring(i), i)) + 1;
				if (current < result) {
					result = current;
					move = i;
				}
			}
		}
		answer.put(sequence, result);
		next.put(sequence, move);
		return result;
	}

	private String process(String sequence, int index) {
		int from = index;
		while (from > 0 && sequence.charAt(from - 1) == sequence.charAt(from))
			from--;
		int to = index;
		while (to < sequence.length() - 1 && sequence.charAt(to + 1) == sequence.charAt(to))
			to++;
		if (to - from <= 1)
			return sequence;
		sequence = sequence.substring(0, from) + sequence.substring(to + 1);
		if (from != 0 && from != sequence.length() && sequence.charAt(from) == sequence.charAt(from - 1))
			return process(sequence, from);
		return sequence;
	}
}
